package com.mms.util;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class Listener extends MouseAdapter {

	private final JLabel label;
	private final String textOnMouseEntered;
	private final String textOnMouseExited;

	public Listener(JLabel label, String textOnMouseEntered, String textOnMouseExited) {
		this.label = label;
		this.textOnMouseEntered = textOnMouseEntered;
		this.textOnMouseExited = textOnMouseExited;
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public void mouseEntered(MouseEvent e) {
		label.setText(textOnMouseEntered);
	}

	public void mouseExited(MouseEvent e) {
		label.setText(textOnMouseExited);
	}
}
