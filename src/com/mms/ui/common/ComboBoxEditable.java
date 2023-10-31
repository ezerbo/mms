package com.mms.ui.common;

import javax.swing.JComboBox;

public class ComboBoxEditable extends JComboBox {
	private boolean teste;

	public ComboBoxEditable() {
		this.setEditor(new ComboEditor(this));
		this.setEditable(true);
		teste = true;
	}

	public void fireActionEvent() {
		if (teste)
			super.fireActionEvent();
	}

	/**
	 * @param teste
	 *            the teste to set
	 */
	public void setTeste(boolean teste) {
		this.teste = teste;
	}
}
