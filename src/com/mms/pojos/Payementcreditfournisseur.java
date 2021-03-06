package com.mms.pojos;
// Generated 25 nov. 2012 13:14:59 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * Payementcreditfournisseur generated by hbm2java
 */
@SuppressWarnings("serial")
public class Payementcreditfournisseur  implements java.io.Serializable {


     private PayementcreditfournisseurId id;
     private Creditfournisseur creditfournisseur;
     private Session session;
     private Date datepayement;
     private BigDecimal montant;

    public Payementcreditfournisseur() {
    }

	
    public Payementcreditfournisseur(PayementcreditfournisseurId id, Creditfournisseur creditfournisseur, Session session) {
        this.id = id;
        this.creditfournisseur = creditfournisseur;
        this.session = session;
    }
    public Payementcreditfournisseur(PayementcreditfournisseurId id, Creditfournisseur creditfournisseur, Session session, Date datepayement, BigDecimal montant) {
       this.id = id;
       this.creditfournisseur = creditfournisseur;
       this.session = session;
       this.datepayement = datepayement;
       this.montant = montant;
    }
   
    public PayementcreditfournisseurId getId() {
        return this.id;
    }
    
    public void setId(PayementcreditfournisseurId id) {
        this.id = id;
    }
    public Creditfournisseur getCreditfournisseur() {
        return this.creditfournisseur;
    }
    
    public void setCreditfournisseur(Creditfournisseur creditfournisseur) {
        this.creditfournisseur = creditfournisseur;
    }
    public Session getSession() {
        return this.session;
    }
    
    public void setSession(Session session) {
        this.session = session;
    }
    public Date getDatepayement() {
        return this.datepayement;
    }
    
    public void setDatepayement(Date datepayement) {
        this.datepayement = datepayement;
    }
    public BigDecimal getMontant() {
        return this.montant;
    }
    
    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }




}


