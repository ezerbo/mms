package com.mms.interfaces;

import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;
 
/*	 * AutoCompleteEditor.java	 */
public class EditeurCombo extends BasicComboBoxEditor
{
	private JTextField editor = null;
	public EditeurCombo(ComboBoxEditable combo){
		super();
		editor = new TextFieldCombo(combo);
	} 
	public Component getEditorComponent(){
		return editor;
	}
}