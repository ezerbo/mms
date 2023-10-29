package com.mms.ui.client;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mms.event.ClientEvent;
import com.mms.ui.user.ActeurExterne;
import com.mms.listener.ClientListener;
import com.mms.service.ClientService;
import com.mms.ui.tablemodels.ClientTableModel;

public class ClientListUI extends ActeurExterne implements ClientListener {
	private final ClientTableModel clientTableModel = new ClientTableModel();

	private final JTable clientsTable = new JTable(clientTableModel);

	private final ClientService clientService = new ClientService();

	private final JButton newClientButton = new JButton("Nouveau client");

	public ClientListUI() {
		super("Clients enregistr\351s", "             fenetre donnant la liste de tous les clients");
		init();
		registerEvents();
	}

	public void init() {
		setTitle("Liste des clients");
		JButton newClientButton = new JButton("Nouveau client");
		panelSouth.add(newClientButton);
		add(new JScrollPane(clientsTable), BorderLayout.CENTER);
		deleteClientButton.setEnabled(false);
		updateClientButton.setEnabled(false);
		showClients();
		setLocationRelativeTo(null);
		setModal(true);
	}

	private void registerEvents() {
		deleteClientButton.addActionListener(e -> {
			int confirmationResult = JOptionPane.showConfirmDialog(null
					, "Voulez-vous vraiment supprmier ce client ?"
					, "Confirmation"
					, JOptionPane.YES_NO_OPTION);
			if (confirmationResult == JOptionPane.YES_OPTION) {
				if (clientService.supprimerClient(getValue(COLONNETELEPHONE)) != 0) {
					JOptionPane.showMessageDialog(null
							, "Client supprimer avec succes"
							, "Succes de l'operation"
							, JOptionPane.INFORMATION_MESSAGE);
					clientTableModel.delete(clientsTable.getSelectedRow());
					return;
				}
				JOptionPane.showMessageDialog(null
						, "Le client n'a pas pu etre supprime"
						, "Echec de l'operation"
						, JOptionPane.ERROR_MESSAGE);
			}
		});

		updateClientButton.addActionListener(e -> clientTableModel.fireTableDataChanged());

		newClientButton.addActionListener(e -> {});

		clientsTable.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent event) {
				deleteClientButton.setEnabled(true);
				updateClientButton.setEnabled(true);
			}

		});

	}

	private String getValue(int pos) {
		return (String) clientsTable.getValueAt(clientsTable.getSelectedRow(), pos);
	}

	private void showClients() {
		clientTableModel.addAll(clientService.listeClient());
	}

	public static void main(String[] args) {
		new ClientListUI().setVisible(true);
	}

	@Override
	public void ajouterClient(ClientEvent event) {
		showClients();
	}
}
