package com.mms.interfaces.supplier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.mms.event.FournisseurEvent;
import com.mms.interfaces.user.EnregistrementActeurExterne;
import com.mms.listener.FournisseurListener;
import com.mms.service.FournisseurService;

public class NouveauFournisseur extends EnregistrementActeurExterne {

	private ArrayList<FournisseurListener> fournisseurListener = new ArrayList<FournisseurListener>();
	private FournisseurService fournisseurService = new FournisseurService();

	public synchronized void addFournisseurListener(FournisseurListener listener) {
		if (fournisseurListener.contains(listener)) {
			return;
		}
		fournisseurListener.add(listener);
	}

	public synchronized void removeFournisseurListener(
			FournisseurListener listener) {
		fournisseurListener.remove(listener);
	}

	public NouveauFournisseur() {
		setTitle("Nouveau fournisseur");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		b_enregistrer.addActionListener(arg0 -> enregistrerFournisseur());
		f_nom.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER)
					enregistrerFournisseur();
			}
		});
		f_prenom.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER)
					enregistrerFournisseur();
			}
		});
		f_telephone.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER)
					enregistrerFournisseur();
			}
		});
		setModal(true);
		// setVisible(true);
	}
	public void enregistrerFournisseur() {

		if (verifierSaisie()) {
			if (verifierNumeroTelephone(f_telephone.getText().substring(8,
					f_telephone.getText().length()))) {
				// enregistrement du fournisseur!!!
				if (fournisseurService.enregistrerFournisseur(
						f_nom.getText(),
						f_prenom.getText(),
						f_telephone.getText().substring(8,
								f_telephone.getText().length())) != null) {
					/***
					 * Si toutes les conditions sont reunies pour enregistrer le
					 * fournisseur
					 **/
					fireNouveauFournisseur();
					JOptionPane.showMessageDialog(null,
							"Fournisseur enregistr� avec success",
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
	public static void main(String[] args) {
		new NouveauFournisseur().setVisible(true);
	}
	public void fireNouveauFournisseur() {
		if (fournisseurListener.size() == 0) {
			return;
		}
		FournisseurEvent event = new FournisseurEvent(this);
		for (FournisseurListener listener : fournisseurListener) {
			listener.ajouterFournisseur(event);
		}

	}
}
