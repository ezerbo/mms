package com.mms.ui.common;

import javax.swing.JComboBox;

public class ComboBoxEditable extends JComboBox {
	private boolean teste;

	public ComboBoxEditable() {
		this.setEditor(new EditeurCombo(this));
		this.setEditable(true);
		teste = true;
	}

	public void fireActionEvent() {
		if (teste)
			super.fireActionEvent();
	}

	/**
	 * @return the teste
	 */
	public boolean isTeste() {
		return teste;
	}

	/**
	 * @param teste
	 *            the teste to set
	 */
	public void setTeste(boolean teste) {
		this.teste = teste;
	}
}
