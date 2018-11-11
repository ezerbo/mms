package com.mms.util;

import java.util.LinkedList;

import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLEditorKit;

import com.mms.tablemodels.LigneDeVente;

@SuppressWarnings("serial")
public class ApercuImpression extends JEditorPane {
	private HTMLEditorKit editorKit = new HTMLEditorKit();
	private LinkedList<LigneDeVente> listeVente = null;
	public ApercuImpression(LinkedList<LigneDeVente> listeVente) {
		this.listeVente = listeVente;
		setContentType("text/html");
		setEditorKit(editorKit);
		setEditable(false);
		setText(entete()+afficherApercu());
		
	}
	private String afficherApercu() {
		StringBuilder text = new StringBuilder("<table  border=1 align=center>");
		text.append("<th>");
		text.append("DESIGNATION");
		text.append("</th>");
		
		text.append("<th");
		text.append("QUANTITE");
		text.append("</th>");
		
		text.append("<th>");
		text.append("TOTAL H.T");
		text.append("</th>")
		;
		text.append("<th>");
		text.append("TOTAL T.V.A");
		text.append("</th>");
		
		text.append("<th>");
		text.append("TOTAL T.T.C");
		text.append("</th>");
		
		for (LigneDeVente ligneCourante : listeVente) {
			if(!ligneCourante.getDesignation().equals("")&&!ligneCourante.getQuantite().equals("")){
			text.append("<tr align=center>");
			
			text.append("<td>");
			text.append(ligneCourante.getDesignation());
			text.append("</td>");
			text.append("<td>");
			text.append(ligneCourante.getQuantite());
			text.append("</td>");
			text.append("<td>");
			text.append(ligneCourante.getPrixGlobalHt());
			text.append("</td>");
			text.append("<td>");
			text.append(ligneCourante.getMontantTva());
			text.append("</td>");
			text.append("<td>");
			text.append(ligneCourante.getPrixGlobalTtc());
			text.append("</td>");
			text.append("</tr>");
			}
			
		}
		text.append("</table>");
		return text.toString();
	}
	private String entete(){
		StringBuilder entete = new StringBuilder("<head>");
		entete.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"SimpleSoft_MMS/src/com/mms/mon_style.css\" />");
		entete.append("</head>");
		return entete.toString();
	}
}
