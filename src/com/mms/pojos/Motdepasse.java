package com.mms.pojos;
// Generated 25 nov. 2012 13:14:59 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Motdepasse generated by hbm2java
 */
@SuppressWarnings("serial")
public class Motdepasse  implements java.io.Serializable {


     private int idmdp;
     private Utilisateur utilisateur;
     private String valeurmdp;
     private Date datecreationmdp;
     private Date datevaliditemdp;

    public Motdepasse() {
    }

	
    public Motdepasse(int idmdp, Utilisateur utilisateur) {
        this.idmdp = idmdp;
        this.utilisateur = utilisateur;
    }
    public Motdepasse(int idmdp, Utilisateur utilisateur, String valeurmdp, Date datecreationmdp, Date datevaliditemdp) {
       this.idmdp = idmdp;
       this.utilisateur = utilisateur;
       this.valeurmdp = valeurmdp;
       this.datecreationmdp = datecreationmdp;
       this.datevaliditemdp = datevaliditemdp;
    }
   
    public int getIdmdp() {
        return this.idmdp;
    }
    
    public void setIdmdp(int idmdp) {
        this.idmdp = idmdp;
    }
    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }
    
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public String getValeurmdp() {
        return this.valeurmdp;
    }
    
    public void setValeurmdp(String valeurmdp) {
        this.valeurmdp = valeurmdp;
    }
    public Date getDatecreationmdp() {
        return this.datecreationmdp;
    }
    
    public void setDatecreationmdp(Date datecreationmdp) {
        this.datecreationmdp = datecreationmdp;
    }
    public Date getDatevaliditemdp() {
        return this.datevaliditemdp;
    }
    
    public void setDatevaliditemdp(Date datevaliditemdp) {
        this.datevaliditemdp = datevaliditemdp;
    }




}

