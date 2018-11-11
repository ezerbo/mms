package com.mms.util;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class Listener extends MouseAdapter {

	JLabel comp;
	String texteMouseEntered;
	String texteMouseExited;

	public Listener(JLabel comp, String texteMouseEntered,
			String texteMouseExited) {
		this.comp = comp;
		this.texteMouseEntered = texteMouseEntered;
		this.texteMouseExited = texteMouseExited;
		comp.setCursor(Cursor.getPredefinedCursor(12));
	}

	public void mouseEntered(MouseEvent e) {
		comp.setText(texteMouseEntered);
	}

	public void mouseExited(MouseEvent e) {
		comp.setText(texteMouseExited);
	}
}
