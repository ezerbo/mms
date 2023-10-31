package com.mms.ui.inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mms.event.FournisseurEvent;
import com.mms.ui.user.ActeurExterne;
import com.mms.listener.FournisseurListener;
import com.mms.domain.Fournisseur;
import com.mms.service.FournisseurService;
import com.mms.ui.tablemodels.TableModelFournisseur;

public class ListeFournisseur extends ActeurExterne implements FournisseurListener {

	private JButton b_ajouter;
	private TableModelFournisseur tableModelFournisseur;
	private JTable tableFournisseur;
	private FournisseurService fournisseurService;

	public ListeFournisseur() {
		super("Fournisseurs enregistrés", "fenêtre donnant la liste des fournisseurs");
		setTitle("Liste des fournisseurs");
		b_ajouter = new JButton("Nouveau fournisseur");
		tableModelFournisseur = new TableModelFournisseur();
		tableFournisseur = new JTable(tableModelFournisseur);
		fournisseurService = new FournisseurService();
		panelSouth.add(b_ajouter);
		afficheListeFournisseur();
		add(new JScrollPane(tableFournisseur), "Center");
		deleteClientButton.setEnabled(false);
		updateClientButton.setEnabled(false);
		deleteClientButton.addActionListener(arg0 -> {
			int result = JOptionPane.showConfirmDialog(null
					, "Voulez-vous vraiment supprimer cet fournisseur ?"
					, "Confirmation"
					, JOptionPane.YES_NO_OPTION);
			if (result == 0) {
				String numeroDeTelephone = (String) tableFournisseur
						.getValueAt(tableFournisseur.getSelectedRow(),
								COLONNETELEPHONE);
				if (fournisseurService
						.supprimerFournisseur(numeroDeTelephone) != 0) {
					JOptionPane.showMessageDialog(null,
							"Fournisseur supprimer avec succès"
							, "Succès de l'opération"
							, JOptionPane.INFORMATION_MESSAGE);
					// afficheListeFournisseur();
					tableModelFournisseur.delete(tableFournisseur
							.getSelectedRow());
				} else {
					JOptionPane.showMessageDialog(null
							, "Le fournisseur n'a pas pu etre supprim�"
							, "Échec de l'opération"
							, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		updateClientButton.addActionListener(e -> tableModelFournisseur.fireTableDataChanged());
		b_ajouter.addActionListener(e -> {});

		tableFournisseur.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent event) {
				deleteClientButton.setEnabled(true);
				updateClientButton.setEnabled(true);
			}

		});
		afficheListeFournisseur();
		setLocationRelativeTo(null);
		setModal(true);
	}
	private void afficheListeFournisseur() {
		tableModelFournisseur.clear();
		LinkedList<Fournisseur> listeFournisseur = fournisseurService
				.listeFournisseur();
		for (Fournisseur fournisseur : listeFournisseur) {
			tableModelFournisseur.add(fournisseur);
		}
	}

	@Override
	public void ajouterFournisseur(FournisseurEvent event) {
		afficheListeFournisseur();
	}

}
