package com.mms.ui.common;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class TextFieldCombo extends JTextField {
	ComboBoxEditable combo;

	public TextFieldCombo(ComboBoxEditable combo) {
		super();
		this.combo = combo;
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					applySelection();
				}
			}
		});
	}

	public void applySelection() {
		setText(combo.getSelectedItem().toString());
	}

	public void requestFocus() {
		super.requestFocus();
		this.setText("");

	}

	protected Document createDefaultModel() {
		return new PlainDocument() {
			public void insertString(int offs, String str, AttributeSet a)
					throws BadLocationException {

				if (str == null || str.length() == 0)
					return;
				int size = combo.getItemCount();
				String text = getText(0, getLength());
				for (int i = 0; i < size; i++) {
					String item = combo.getItemAt(i).toString();
					if (getLength() + str.length() > item.length())
						continue;
					if ((text + str).equalsIgnoreCase(item)) {
						combo.setTeste(false);
						combo.setSelectedIndex(i);
						combo.setTeste(true);
						if (!combo.isPopupVisible())
							combo.setPopupVisible(true);
						super.remove(0, getLength());
						super.insertString(0, text + str, a);
						return;
					} else if (item.substring(0, getLength() + str.length())
							.equalsIgnoreCase(text + str)) {
						combo.setTeste(false);
						combo.setSelectedIndex(i);
						combo.setTeste(true);
						if (!combo.isPopupVisible())
							combo.setPopupVisible(true);
						super.remove(0, getLength());
						super.insertString(0, text + str, a);
						return;
					}
				}
			}
		};
	}

}
