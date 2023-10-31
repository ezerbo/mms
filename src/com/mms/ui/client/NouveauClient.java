package com.mms.ui.client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mms.event.ClientEvent;
import com.mms.ui.user.EnregistrementActeurExterne;
import com.mms.listener.ClientListener;
import com.mms.service.ClientService;

public class NouveauClient extends EnregistrementActeurExterne {

    private final List<ClientListener> clientListeners = new ArrayList<>();
    private final ClientService clientService = new ClientService();

    public void addClientListener(ClientListener listener) {
        if (clientListeners.contains(listener)) {
            return;
        }
        clientListeners.add(listener);
    }

    public NouveauClient() {
        setTitle("Nouveau client");
        b_enregistrer.addActionListener(e -> enregistrerClient());
        f_nom.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER)
                    enregistrerClient();
            }
        });
        f_prenom.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER)
                    enregistrerClient();
            }
        });
        f_telephone.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER)
                    enregistrerClient();
            }
        });
        setModal(true);
    }

    public void enregistrerClient() {

        if (verifierSaisie()) {
            if (verifierNumeroTelephone(f_telephone.getText().substring(8))) {
                if (clientService.enregistrerClient(
                        f_nom.getText(),
                        f_prenom.getText(),
                        f_telephone.getText().substring(8)) != null) {
                    fireNouveauClient();
                    JOptionPane.showMessageDialog(null
                            , "Client enregistré avec success"
                            , "Succès de l'opération"
                            , JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null
                            , "Le numéro de telephone existe deja"
                            , "Erreur"
                            , JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null
                        , "Le numéro de telephone n'est pas valide!"
                        , "Erreur"
                        , JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null
                    , "Tous les champs sont obligatoires!"
                    , "Erreur"
                    , JOptionPane.ERROR_MESSAGE);
        }

    }

    public void fireNouveauClient() {
        if (clientListeners.size() == 0) {
            return;
        }
        ClientEvent event = new ClientEvent(this);
        for (ClientListener listener : clientListeners) {
            listener.ajouterClient(event);
        }
    }

    public static void main(String[] agrs) {
        new NouveauClient().setVisible(true);
    }
}
