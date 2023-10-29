package com.mms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.mms.domain.Parameters;
import com.mms.domain.User;
import com.mms.error.ExpiredPasswordException;
import com.mms.error.InvalidCrdentialsException;
import com.mms.util.PasswordUtil;
import org.joda.time.DateTime;

import com.mms.dao.PasswordRepository;
import com.mms.dao.ParametresRepository;
import com.mms.dao.SessionRepository;
import com.mms.dao.UserRepository;
import com.mms.domain.Password;
import com.mms.domain.Session;
import com.mms.domain.UserType;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    private final PasswordRepository passwordRepository = new PasswordRepository();
    private final SessionRepository sessionRepository = new SessionRepository();

    private final ParametresRepository parametresRepository = new ParametresRepository();

    private static User loggedInUser;
    private User user;
    public String TYPEUTILISATEUR = "GESTIONNAIRE";

    // TODO Remove
    public boolean egaliteMotDePasse(String motDePasse, String confirmation) {
        return motDePasse.equals(confirmation);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public boolean exists(String login) {
        return Objects.nonNull(userRepository.findByLogin(login));
    }

    public void update(int id, String lastName, String firstName
            , String phoneNumber, String username) {
        User user = User.builder()
                .idutilisateur(id)
                .nomutilisateur(lastName)
                .prenomutilisateur(firstName)
                .telephoneutilisateur(phoneNumber)
                .loginutilisateur(username)
                .build();
        userRepository.update(user);
    }

    public boolean passwordExists(int id, String password) {
        return passwordRepository.passwordExists(id, password);
    }

    public void create(String lastName, String firstName, String phoneNumber
            , String username, String password, String userType) {
        int ADMINISTRATEUR = 1;
        int GESTIONNAIRE = 2;
        User user = User.builder()
                .nomutilisateur(lastName)
                .prenomutilisateur(firstName)
                .telephoneutilisateur(phoneNumber)
                .loginutilisateur(username)
                .userType(UserType.builder()
                        .idtypeutilisateur(userType.equals("GESTIONNAIRE") ? GESTIONNAIRE : ADMINISTRATEUR)
                        .build())
                .datedepartutilisateur(new Date())
                .build();
        createPassword(password, userRepository.create(user));
    }

    public Password createPassword(String password, User user) {
        Password motDePasse = Password.builder()
                .valeurmdp(PasswordUtil.encrypt(password))
                .datecreationmdp(new Date())
                .datevaliditemdp(DateTime.now().plusDays(getParameters().getDureeviemdp()).toDate())
                .user(user)
                .build();
        return passwordRepository.create(motDePasse);
    }


    public List<User> listeGestionnaire() {
        return  userRepository.findAll();
    }

    public boolean isAdminAccountCreated() {
        return userRepository.isAdminAccountCreated();
    }

    public int delete(String login) {
        deletePassword(login);
        return userRepository.delete(User.builder().loginutilisateur(login).build());
    }

    public boolean authentificationAdministrateur(String password) {
        User user = userRepository.findAdmin();
        if (user != null) {
            return PasswordUtil.compare(passwordRepository.getAdminPassword(user.getIdutilisateur()), password);
        }
        return false;
    }

    private void deletePassword(String login) {
        passwordRepository.delete(Password.builder().user(userRepository.findByLogin(login)).build());
    }

    public int updateParameters(int longueurMDP, int dureeVieMDP, int nombreTentative, int tempsInactivite) {
        Parameters parameters = Parameters.builder()
                .longueurmdp(longueurMDP)
                .dureeviemdp(dureeVieMDP)
                .tempsinactivitesmdp(tempsInactivite)
                .tentativemdp(nombreTentative)
                .build();
        return new ParametresRepository().update(parameters);
    }

    public void login(String username, String pwd) {
        User user = userRepository.findByLogin(username);
        if (user != null) {
            Password password = new PasswordRepository().findCurrentPwd(user.getIdutilisateur());
            if (!PasswordUtil.compare(password.getValeurmdp(), pwd)) {
                throw new InvalidCrdentialsException("Incorrect password for user: %s", username);
            }

            if (password.getDatevaliditemdp().getTime() < new Date().getTime()) {
                throw new ExpiredPasswordException("The password has expired for user: %s", username);
            }
            loggedInUser = user;
            startSession(user);
            return;
        }
        throw new InvalidCrdentialsException("No valid user");
    }

    public Session getLatestSession(int id) {
        return sessionRepository.getLatestSession(id);
    }

    public Parameters getParameters() {
        return parametresRepository.findAll().get(0);
    }

    private void startSession(User user) {
        sessionRepository.create(Session.builder()
                .user(user)
                .datedebutsession(dateCourante())
                .datefinsession("")
                .dureesession(0)
                .build());
    }

    public int updateSession(int id) {
        Session latestSession = sessionRepository.getLatestSession(id);
        latestSession.setDatefinsession(dateCourante());
        latestSession.setDureesession(calculeDureeSession(latestSession.getDatedebutsession()
                , latestSession.getDatefinsession()));
        return sessionRepository.update(latestSession);
    }

    private String dateCourante() {
        return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
    }

    // TODO Remove when updating data access logic
    private int calculeDureeSession(String dateDebutSession, String dateFinSession) {
        return (int) (Date.parse(dateFinSession) - Date.parse(dateDebutSession));
    }

    //TODO Revisit validation logic
    public boolean validatePhoneNumber(String telephone) {
        return false;
    }

    public static void main(String[] args) {
    }

    public static User getLoggedInUser() {
        if (loggedInUser != null) {
            return loggedInUser;
        }
        loggedInUser = new User();
        loggedInUser.setLoginutilisateur("ezerbo"); // TODO get from login context & fetch from db
        loggedInUser.setIdutilisateur(1);
        return loggedInUser;
    }

}
