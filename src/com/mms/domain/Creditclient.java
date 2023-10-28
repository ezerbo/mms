package com.mms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Creditclient implements java.io.Serializable {

    private int idcreditclient;

    private Client client;

    private Session session;

    private Etat etat;

    private BigDecimal montanttotalht;

    private BigDecimal montanttotalttc;

    private Date datecredit;

    private BigDecimal montantpaye;

    private Set<Lignedevente> lignedeventes = new HashSet<>(0);

    private Set<Payementcreditclient> payementcreditclients = new HashSet<>(0);

    private Set<Lignecreditvente> lignecreditventes = new HashSet<>(0);

}
