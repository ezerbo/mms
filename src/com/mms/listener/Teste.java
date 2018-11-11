package com.mms.listener;

import javax.swing.JFrame;

import com.mms.interfaces.TextFieldTable;

@SuppressWarnings("serial")
public class Teste extends JFrame {
	public Teste() {
		getContentPane().add(new TextFieldTable());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Teste();
	}

}
