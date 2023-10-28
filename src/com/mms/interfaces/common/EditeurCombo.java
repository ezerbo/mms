package com.mms.interfaces.common;

import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;
 

public class EditeurCombo extends BasicComboBoxEditor
{
	private final JTextField editor;
	public EditeurCombo(ComboBoxEditable combo){
		super();
		editor = new TextFieldCombo(combo);
	} 
	public Component getEditorComponent(){
		return editor;
	}
}