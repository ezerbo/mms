package com.mms.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.mms.tablemodels.LigneDeVente;
import com.mms.util.ApercuImpression;

@SuppressWarnings("serial")
public class InterfaceFacture extends JDialog {
	private JPanel panelCentre;
	private JPanel panelSud;
	private JPanel panelNord;
	private JPanel panelOuest;
	private JPanel panelEst;
	private ApercuImpression apercu;
	@SuppressWarnings("unused")
	private LinkedList<LigneDeVente> listeVente;
	/************/
	private JButton boutonFermer;
public InterfaceFacture(LinkedList<LigneDeVente> listeVente){
	this.listeVente = listeVente;
	getContentPane().setLayout(new BorderLayout(10,10));
	panelCentre = new JPanel();
	panelEst = new JPanel();
	panelSud = new JPanel();
	panelOuest = new JPanel();
	panelNord = new JPanel();
	boutonFermer = new JButton("Fermer");
	panelCentre.setLayout(new BorderLayout());
	apercu = new ApercuImpression(listeVente);
	panelCentre.add(new JScrollPane(apercu));
	panelSud.setLayout(new FlowLayout(FlowLayout.LEFT));
	panelSud.add(boutonFermer);
	add(panelCentre,BorderLayout.CENTER);
	add(panelSud,BorderLayout.SOUTH);
	add(panelNord,BorderLayout.NORTH);
	add(panelOuest,BorderLayout.WEST);
	add(panelEst,BorderLayout.EAST);
	boutonFermer.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			dispose();
		}
	});
	setSize(new Dimension(840,580));
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(null);
	setModal(true);
	setVisible(true);
}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
