package com.mms.ui.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mms.ui.common.CopyRight;
import com.mms.service.SalesService;
import com.mms.ui.tablemodels.CreditClientTableModel;

public class CreditClient extends JDialog {

	private JPanel panelCentre;
	private CreditClientTableModel model;
	private JTable tableCredit;
	private JPanel panelSud;
	private JPanel panelNord;
	private JPanel panelIcon;
	private JPanel panelTitle;
	private JPanel panelTable;
	private JLabel label1;
	private JLabel label2;
	@SuppressWarnings("unused")
	private SalesService salesService;

	public CreditClient() {
		panelCentre = new JPanel();
		panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout());
		model = new CreditClientTableModel();
		tableCredit = new JTable(model);
		panelSud = new JPanel();
		setLayout(new BorderLayout());
		setTitle("Liste des credits octroy�s au clients");
		panelCentre.setLayout(new BorderLayout());
		panelSud.setLayout(new FlowLayout(FlowLayout.LEFT));
		label1 = new JLabel("    Credits client");
		label2 = new JLabel("          fenetre donnant la liste de credits client");
		panelNord = new JPanel();
		panelTitle = new JPanel();
		panelIcon = new JPanel();
		label1.setFont(new Font("Times new Roman", 2, 24));
		label2.setFont(new Font("Times new Roman", 2, 18));
		salesService = new SalesService();
		panelIcon.setLayout(new FlowLayout(2));
		panelIcon.setBackground(Color.WHITE);
		panelIcon.add(new JLabel(new ImageIcon("ressources/images/credit.jpg")));
		panelTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTitle.setBackground(Color.WHITE);
		panelTitle.setLayout(new BorderLayout());
		panelTitle.add(label2,  BorderLayout.CENTER);
		panelTitle.add(label1,  BorderLayout.NORTH);
		panelNord.setBackground(Color.WHITE);
		panelNord.setLayout(new GridLayout(1, 2));
		panelNord.add(panelTitle);
		panelNord.add(panelIcon);
		panelSud.setLayout(new GridLayout(1,1));
		panelSud.add(new CopyRight());
		add(panelNord,  BorderLayout.NORTH);
		add(panelSud, BorderLayout.SOUTH);
		panelTable.add(new JScrollPane(tableCredit),  BorderLayout.CENTER);
		add(panelCentre,  BorderLayout.CENTER);
		setSize(new Dimension(740, 520));
		setLocationRelativeTo(null);
		afficheCreditClient();
		setModal(true);
		setVisible(true);
	}

	public void afficheCreditClient() {
		
	}

	public static void main(String[] args) {
		new CreditClient();
	}
}
