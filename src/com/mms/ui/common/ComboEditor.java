package com.mms.ui.common;

import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;
 

public class ComboEditor extends BasicComboBoxEditor
{
	private final JTextField editor;
	public ComboEditor(ComboBoxEditable combo){
		super();
		editor = new TextFieldCombo(combo);
	} 
	public Component getEditorComponent(){
		return editor;
	}
}