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
public class Creditfournisseur implements java.io.Serializable {

    private int idcreditfournisseur;

    private Fournisseur fournisseur;

    private Session session;

    private Etat etat;

    private BigDecimal montanttotalht;

    private BigDecimal montanttotalttc;

    private Date datecredit;

    private BigDecimal montantpaye;

    private Set<Payementcreditfournisseur> payementcreditfournisseurs = new HashSet<>(0);

    private Set<Lignecreditachat> lignecreditachats = new HashSet<>(0);


}
