package com.mms.interfaces;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mms.event.ClientEvent;
import com.mms.listener.ClientListener;
import com.mms.pojos.Client;
import com.mms.service.ClientService;
import com.mms.tablemodels.TableModelClient;

// Referenced classes of package interfaces:
//            ActeurExterne, ClientTableModel, NouveauClient

@SuppressWarnings("serial")
public class ListeClient extends ActeurExterne implements ClientListener {
	private JButton b_nouveauClient;
	private TableModelClient tableModelClient;
	private JTable tableClient;
	private ClientService clientService;

	public ListeClient(final NouveauClient nouveauClient,final ModifierClient modifierClient) {
		super("Clients enregistr\351s",
				"             fenetre donnant la liste de tous les clients");
		setTitle("Liste des clients");
		b_nouveauClient = new JButton("Nouveau client");
		tableModelClient = new TableModelClient();
		tableClient = new JTable(tableModelClient);
		clientService = new ClientService();
		panelSouth.add(b_nouveauClient);
		add(new JScrollPane(tableClient), BorderLayout.CENTER);
		afficheListeClient();
		b_supprimer.setEnabled(false);
		b_modifier.setEnabled(false);
		b_supprimer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				int result = JOptionPane.showConfirmDialog(null,
						"Voulez-vous vraiment supprmier cet client ?",
						"Confirmation", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					/** Verifier si l'on ne doit pas au fournisseur */
					String numeroDeTelephone = (String) tableClient.getValueAt(
							tableClient.getSelectedRow(), COLONNETELEPHONE);
					if (clientService.supprimerClient(numeroDeTelephone) != 0) {
						JOptionPane.showMessageDialog(null,
								"Client supprimer avec succes",
								"Succes de l'operation",
								JOptionPane.INFORMATION_MESSAGE);
						tableModelClient.supprimer(tableClient.getSelectedRow());
					} else {
						JOptionPane.showMessageDialog(null,
								"Le client n'a pas pu etre supprimé",
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
				String numeroDeTelephone = (String) tableClient.getValueAt(
						tableClient.getSelectedRow(), COLONNETELEPHONE);
				modifierClient.setAncienNumeroDeTelephone(numeroDeTelephone);
				modifierClient.renseigneChamps();
				modifierClient.setVisible(true);
				tableModelClient.fireTableDataChanged();
			}

		});
		b_nouveauClient.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				nouveauClient.reinitialiserChamps();
				nouveauClient.setVisible(true);
			}

		});

		tableClient.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent event) {
				b_supprimer.setEnabled(true);
				b_modifier.setEnabled(true);
			}

		});
		afficheListeClient();
		setLocationRelativeTo(null);
		setModal(true);
	}

	public void afficheListeClient() {
		tableModelClient.supprimerTous();
		LinkedList<Client> listeClient = clientService.listeClient();
		for (Client client : listeClient) {
			tableModelClient.ajouter(client);
		}
	}

	public JDialog returnThis() {
		return this;
	}

	public static void main(String[] args) {
		// new ListeClient();
	}

	@Override
	public void ajouterClient(ClientEvent event) {
		// TODO Auto-generated method stub
		afficheListeClient();
	}
}
