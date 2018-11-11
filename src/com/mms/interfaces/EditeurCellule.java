package com.mms.interfaces;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class EditeurCellule extends JTextField {
	public EditeurCellule() {

	}

	protected Document createDefaultModel() {
		return new PlainDocument() {
			public void insertString(int offs, String str, AttributeSet a) {

			}
		};

	}
}
