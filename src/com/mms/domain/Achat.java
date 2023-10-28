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
public class Achat implements java.io.Serializable {

    private int numeroachat;

    private Fournisseur fournisseur;

    private Session session;

    private Etat etat;

    private BigDecimal montanttotalachatht;

    private BigDecimal montanttotalachatttc;

    private Date dateachat;

    private Set<Lignedachat> lignedachats = new HashSet<>(0);
}
