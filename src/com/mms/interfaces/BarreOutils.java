package com.mms.interfaces;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class BarreOutils extends JToolBar {
	private JButton b_exportPdf;
	private JButton b_exportExcel;
	private JButton b_imprimer;
	private JButton b_apercu;

	public BarreOutils() {
		b_exportPdf = new JButton();
		b_exportPdf.setIcon(new ImageIcon("ressources/images/acrobat_img.jpg"));
		b_exportExcel = new JButton();
		b_exportExcel.setIcon(new ImageIcon("ressources/images/excel_img.jpg"));
		b_imprimer = new JButton();
		b_imprimer.setIcon(new ImageIcon("ressources/images/imprimer.jpg"));
		b_apercu = new JButton();
		b_apercu.setIcon(new ImageIcon("ressources/images/apercu.jpg"));
		add(b_apercu);
		add(b_imprimer);
		add(b_exportPdf);
		add(b_exportExcel);
	}
}
