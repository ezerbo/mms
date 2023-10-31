package com.mms.ui.common;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class TextFieldTable extends JTextField {
	public void requestFocus() {
		super.requestFocus();
		if (getText().equals("0")) {
			this.setText("");
		}

	}

	protected Document createDefaultModel() {
		return new PlainDocument() {
			public void insertString(int offs, String str, AttributeSet a)
					throws BadLocationException {
				if (str == null || str.length() == 0)
					return;
				String text = getText(0, getLength());
				try {
					Integer.parseInt(str);
					super.remove(0, getLength());
					super.insertString(0, text + str, a);
				} catch (NumberFormatException e) {
				}

			}
		};
	}
}
