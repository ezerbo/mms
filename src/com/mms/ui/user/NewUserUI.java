package com.mms.ui.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;

import com.mms.event.UtilisateurEvent;
import com.mms.ui.common.NoBlankTextField;
import com.mms.listener.UtilisateurListener;
import com.mms.domain.Parameters;
import com.mms.service.UserService;
import com.mms.ui.util.UIUtil;

public class NewUserUI extends JDialog {

    protected JButton b_enregistrerUtilisateur;
    protected JPanel panelCentral;
    protected JPanel panelSud;
    private JPanel panelNord;
    private JPanel panelEst;
    private JPanel panelOuest;
    protected JPanel panelLabel;
    protected JPanel panelField;
    private NoBlankTextField f_login = new NoBlankTextField();
    private NoBlankTextField f_nom = new NoBlankTextField();
    private NoBlankTextField f_prenom = new NoBlankTextField();
    private JFormattedTextField f_telephone;
    private JPasswordField f_premierMotDePasse = new JPasswordField(20);
    private JPasswordField f_secondMotDePasse = new JPasswordField(20);
    private JButton b_annuler = new JButton("Annuler");
    private UserService userService = new UserService();
    private ArrayList<UtilisateurListener> utilisateurListeners = new ArrayList<>();

    public synchronized void addUtilisateurListener(UtilisateurListener listener) {
        if (utilisateurListeners.contains(listener)) {
            return;
        }
        utilisateurListeners.add(listener);
    }

    public synchronized void removeUtilisateurListener(UtilisateurListener listener) {
        utilisateurListeners.remove(listener);
    }

    public void fireNouvelUtilisateur() {
        if (utilisateurListeners.size() == 0) {
            return;
        }
        UtilisateurEvent event = new UtilisateurEvent(this);
        for (UtilisateurListener listener : utilisateurListeners) {
            listener.ajouterUilisateur(event);
        }

    }

    public NewUserUI() {
        getContentPane();
        setTitle("Nouvel utilisateur");
        panelCentral = new JPanel();
        panelSud = new JPanel();
        panelLabel = new JPanel();
        panelField = new JPanel();
        panelNord = new JPanel();
        panelOuest = new JPanel();
        panelEst = new JPanel();
        setLayout(new BorderLayout());
        panelLabel.setLayout(new GridLayout(6, 1, 10, 10));
        panelLabel.add(new JLabel("Nom : "));
        panelLabel.add(new JLabel("Prenom : "));
        panelLabel.add(new JLabel("Telephone : "));
        panelLabel.add(new JLabel("Login :"));
        panelLabel.add(new JLabel("Mot de passe :"));
        panelLabel.add(new JLabel("Retapez le mot de passe :"));
        b_enregistrerUtilisateur = new JButton("Enregistrer");
        panelCentral.setBorder(new TitledBorder(""));
        panelCentral.setBackground(Color.WHITE);
        panelField.setBackground(Color.WHITE);
        panelLabel.setBackground(Color.WHITE);
        panelCentral.add(panelLabel);
        panelField.setLayout(new GridLayout(6, 1, 6, 6));
        panelField.add(f_nom);
        panelField.add(f_prenom);
        f_telephone = new JFormattedTextField(UIUtil.getPhoneNumberFormatter());
        panelField.add(f_telephone);
        panelField.add(f_login);
        panelField.add(f_premierMotDePasse);
        panelField.add(f_secondMotDePasse);
        panelCentral.add(panelField);
        add(panelCentral, "Center");
        panelSud.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelSud.add(b_enregistrerUtilisateur);
        panelSud.add(b_annuler);
        add(panelSud, BorderLayout.SOUTH);
        add(panelNord, BorderLayout.NORTH);
        add(panelOuest, BorderLayout.WEST);
        add(panelEst, BorderLayout.EAST);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        b_annuler.addActionListener(e -> dispose());
        b_enregistrerUtilisateur.addActionListener(e -> {
            Parameters parameters = new UserService().getParameters();
            if (f_nom.getText().equals("") || f_prenom.getText().equals("") || f_login.getText().equals("") || f_premierMotDePasse.getText().equals("") || f_secondMotDePasse.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Tous les champs sont obligatoires !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                if (f_premierMotDePasse.getText().length() < parameters.getLongueurmdp()) {
                    JOptionPane.showMessageDialog(null, "Le mot de passe est trop court,entrez au moins " + parameters.getLongueurmdp() + " caracteres", "longueur de mot de passe", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (userService.exists(f_login.getText())) {
                        JOptionPane.showMessageDialog(null, "Ce login existe deja !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (userService.validatePhoneNumber(f_telephone.getText().substring(8))) {
                            if (userService.egaliteMotDePasse(f_premierMotDePasse.getText(), f_secondMotDePasse.getText())) {
                                userService.validatePhoneNumber(f_telephone.getText().substring(8));
                                userService.create(f_nom.getText(), f_prenom.getText(), f_telephone.getText().substring(8), f_login.getText(), f_premierMotDePasse.getText(), userService.TYPEUTILISATEUR);
                                fireNouvelUtilisateur();
                                JOptionPane.showMessageDialog(null, "Utilisateur enregistr� avec sucess !!!", "succes de l'enregistrement", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                                //JOptionPane.showMessageDialog(null,"L'utilisateur n'a pas pu etre enregistr� !!!","Erreur",JOptionPane.ERROR_MESSAGE);

                            } else {
                                JOptionPane.showMessageDialog(null, "Les mots de passe doivent etre identiques", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Le numero de telephone n'est pas valide", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        //
                    }
                }
            }
        });
        setModal(true);
    }

    public void reinitialiseChamps() {
        f_nom.setText("");
        f_prenom.setText("");
        f_premierMotDePasse.setText("");
        f_secondMotDePasse.setText("");
        f_telephone.setText("");
        f_login.setText("");
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new NewUserUI().setVisible(true);
    }
}
