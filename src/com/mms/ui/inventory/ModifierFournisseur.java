package com.mms.ui.inventory;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.mms.event.FournisseurEvent;
import com.mms.ui.user.EnregistrementActeurExterne;
import com.mms.listener.FournisseurListener;
import com.mms.domain.Fournisseur;
import com.mms.service.FournisseurService;

public class ModifierFournisseur extends EnregistrementActeurExterne {
	private ArrayList<FournisseurListener> fournisseurListener = new ArrayList<>();
	private FournisseurService fournisseurService = new FournisseurService();
	private String ancienNumeroDeTelephone;

	public ModifierFournisseur() {

		setTitle("Nouveau fournisseur");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		b_enregistrer.addActionListener(e -> mettreAJourFournisseur());
		f_nom.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER)
					mettreAJourFournisseur();
			}
		});
		f_prenom.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER)
					mettreAJourFournisseur();
			}
		});
		f_telephone.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER)
					mettreAJourFournisseur();
			}
		});
		setModal(true);
		// setVisible(true);
	}
	public void mettreAJourFournisseur() {

		if (verifierSaisie()) {
			if (verifierNumeroTelephone(f_telephone.getText().substring(8))) {
				// enregistrement du fournisseur!!!
				if (fournisseurService.misAJourFournisseur(
						f_nom.getText(),
						f_prenom.getText(), 
						f_telephone.getText().substring(8),ancienNumeroDeTelephone) != 0) {
					fireNouveauFournisseur();
					JOptionPane.showMessageDialog(null,
							"Fournisseur mis � jour avec success",
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
