package com.mms.interfaces;

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
import com.mms.listener.FournisseurListener;
import com.mms.pojos.Fournisseur;
import com.mms.service.FournisseurService;
import com.mms.tablemodels.TableModelFournisseur;

// Referenced classes of package interfaces:
//            ActeurExterne, FournisseurTableModel, NouveauFournisseur

@SuppressWarnings("serial")
public class ListeFournisseur extends ActeurExterne
		implements
			FournisseurListener {

	private JButton b_ajouter;
	private TableModelFournisseur tableModelFournisseur;
	private JTable tableFournisseur;
	private FournisseurService fournisseurService;

	public ListeFournisseur(final NouveauFournisseur nouveauFournisseur,
			final ModifierFournisseur modifierFournisseur) {
		super("Fournisseurs enregistr\351s",
				"        fenetre donnant la liste des fournisseurs");
		setTitle("Liste des fournisseurs");
		b_ajouter = new JButton("Nouveau fournisseur");
		tableModelFournisseur = new TableModelFournisseur();
		tableFournisseur = new JTable(tableModelFournisseur);
		fournisseurService = new FournisseurService();
		panelSouth.add(b_ajouter);
		afficheListeFournisseur();
		add(new JScrollPane(tableFournisseur), "Center");
		b_supprimer.setEnabled(false);
		b_modifier.setEnabled(false);
		b_supprimer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null,
						"Voulez-vous vraiment supprimer cet fournisseur ?",
						"Confirmation", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					/** Verifier si l'on ne doit pas au fournisseur */
					String numeroDeTelephone = (String) tableFournisseur
							.getValueAt(tableFournisseur.getSelectedRow(),
									COLONNETELEPHONE);
					if (fournisseurService
							.supprimerFournisseur(numeroDeTelephone) != 0) {
						JOptionPane.showMessageDialog(null,
								"Fournisseur supprimer avec succes",
								"Succes de l'operation",
								JOptionPane.INFORMATION_MESSAGE);
						// afficheListeFournisseur();
						tableModelFournisseur.supprimer(tableFournisseur
								.getSelectedRow());
					} else {
						JOptionPane.showMessageDialog(null,
								"Le fournisseur n'a pas pu etre supprimé",
								"Echec de l'operation",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});
		b_modifier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String numeroDeTelephone = (String) tableFournisseur.getValueAt(tableFournisseur.getSelectedRow(), COLONNETELEPHONE);
				modifierFournisseur.setAncienNumeroDeTelephone(numeroDeTelephone);
				modifierFournisseur.renseigneChamps();
				modifierFournisseur.setVisible(true);
				tableModelFournisseur.fireTableDataChanged();
			}

		});
		b_ajouter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				nouveauFournisseur.reinitialiserChamps();
				nouveauFournisseur.setVisible(true);
			}

		});

		tableFournisseur.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent event) {
				b_supprimer.setEnabled(true);
				b_modifier.setEnabled(true);
			}

		});
		afficheListeFournisseur();
		setLocationRelativeTo(null);
		setModal(true);
	}
	private void afficheListeFournisseur() {
		tableModelFournisseur.supprimerTous();
		LinkedList<Fournisseur> listeFournisseur = fournisseurService
				.listeFournisseur();
		for (Fournisseur fournisseur : listeFournisseur) {
			tableModelFournisseur.ajouter(fournisseur);
		}
	}

	@Override
	public void ajouterFournisseur(FournisseurEvent event) {
		// TODO Auto-generated method stub
		afficheListeFournisseur();
	}

}
