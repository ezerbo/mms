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

public class ModifierClient extends EnregistrementActeurExterne {
	private final List<ClientListener> clientListeners = new ArrayList<>();
	private final ClientService clientService = new ClientService();
	private final String ancienNumeroDeTelephone;

	public ModifierClient(String ancienNumeroDeTelephone) {
		this.ancienNumeroDeTelephone = ancienNumeroDeTelephone;
		setTitle("Mis a jour de client");

		b_enregistrer.addActionListener(e -> mettreAJourClient());
		f_nom.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER)
					mettreAJourClient();
			}
		});
		f_prenom.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER)
					mettreAJourClient();
			}
		});
		f_telephone.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER)
					mettreAJourClient();
			}
		});
		setModal(true);

	}

	public void mettreAJourClient() {

		if (verifierSaisie()) {
			if (verifierNumeroTelephone(f_telephone.getText().substring(8))) {
				if (clientService.misAJourClient(
						f_nom.getText(),
						f_prenom.getText(),
						f_telephone.getText().substring(8),
						ancienNumeroDeTelephone) != 0) {
					fireNouveauClient();
					JOptionPane.showMessageDialog(null
							, "Client mis a jour avec success"
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

}
