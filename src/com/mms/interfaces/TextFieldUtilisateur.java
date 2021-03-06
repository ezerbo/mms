package com.mms.interfaces;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class TextFieldUtilisateur extends JTextField {

	public void requestFocus() {
		super.requestFocus();
		if (getText().equals("0"))
			this.setText("");

	}

	protected Document createDefaultModel() {
		return new PlainDocument() {
			public void insertString(int offs, String str, AttributeSet a)
					throws BadLocationException {
				if (str == null || str.length() == 0)
					return;
				String text = getText(0, getLength());
				/** recuperation de l'ancienne valeur dans le JTextField */

				if (!str.equals(" ")) {
					super.remove(0, getLength());// suppression de l'ancienne
													// valeur dans le JTextField
					super.insertString(0, text + str, a);
					/**
					 * ajout de l'ancienne valeur augment� de la valeur
					 * nouvellement ajout�e
					 */
					return;
				}
			}
		};
	}

}
