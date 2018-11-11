package com.mms.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mms.event.ClientEvent;
import com.mms.listener.ClientListener;
import com.mms.pojos.Client;
import com.mms.service.ClientService;

@SuppressWarnings("serial")
public class ModifierClient extends EnregistrementActeurExterne {
	private ArrayList<ClientListener> clientListeners = new ArrayList<ClientListener>();
	private ClientService clientService = new ClientService();
	private String ancienNumeroDeTelephone;
	public void addClientListener(ClientListener listener) {
		if (clientListeners.contains(listener)) {
			return;
		}
		clientListeners.add(listener);
	}

	public void removeClientListener(ClientListener listener) {
		clientListeners.remove(listener);
	}

	public void renseigneChamps() {
		Client client = clientService.retourneClient(ancienNumeroDeTelephone);
		f_nom.setText(client.getNomClient());
		f_prenom.setText(client.getPrenomClient());
		f_telephone.setText(client.getTelephoneclient());
	}
	public ModifierClient() {
		setTitle("Mis a jour de client");

		b_enregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mettreAJourClient();
			}
		});
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
	/**
	 * @return the ancienNumeroDeTelephone
	 */
	public String getAncienNumeroDeTelephone() {
		return ancienNumeroDeTelephone;
	}

	/**
	 * @param ancienNumeroDeTelephone
	 *            the ancienNumeroDeTelephone to set
	 */
	public void setAncienNumeroDeTelephone(String ancienNumeroDeTelephone) {
		this.ancienNumeroDeTelephone = ancienNumeroDeTelephone;
	}

	public void mettreAJourClient() {

		if (verifierSaisie()) {
			if (verifierNumeroTelephone(f_telephone.getText().substring(8,
					f_telephone.getText().length()))) {
				// mis a jour du client!!!
				if (clientService.misAJourClient(
						f_nom.getText(),
						f_prenom.getText(),
						f_telephone.getText().substring(8,
								f_telephone.getText().length()),
						ancienNumeroDeTelephone) != 0) {
					/***
					 * Si toutes les conditions sont reunies pour enregistrer le
					 * fournisseur
					 **/
					fireNouveauClient();
					JOptionPane.showMessageDialog(null,
							"Client mis a jour avec success",
							"Succes de l'operation",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Le numero de telephone existe deja", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Le numero de telephone n'est pas valide !!!",
						"Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Tous les champs sont obligatoires !!!", "Erreur",
					JOptionPane.ERROR_MESSAGE);
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
