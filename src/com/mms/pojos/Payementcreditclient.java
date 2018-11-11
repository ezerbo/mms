package com.mms.pojos;
// Generated 25 nov. 2012 13:14:59 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * Payementcreditclient generated by hbm2java
 */
@SuppressWarnings("serial")
public class Payementcreditclient  implements java.io.Serializable {


     private PayementcreditclientId id;
     private Creditclient creditclient;
     private Session session;
     private Date datepayement;
     private BigDecimal montant;

    public Payementcreditclient() {
    }

	
    public Payementcreditclient(PayementcreditclientId id, Creditclient creditclient, Session session) {
        this.id = id;
        this.creditclient = creditclient;
        this.session = session;
    }
    public Payementcreditclient(PayementcreditclientId id, Creditclient creditclient, Session session, Date datepayement, BigDecimal montant) {
       this.id = id;
       this.creditclient = creditclient;
       this.session = session;
       this.datepayement = datepayement;
       this.montant = montant;
    }
   
    public PayementcreditclientId getId() {
        return this.id;
    }
    
    public void setId(PayementcreditclientId id) {
        this.id = id;
    }
    public Creditclient getCreditclient() {
        return this.creditclient;
    }
    
    public void setCreditclient(Creditclient creditclient) {
        this.creditclient = creditclient;
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

