package com.mms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payementcreditfournisseur implements java.io.Serializable {

    private PayementcreditfournisseurId id;

    private Creditfournisseur creditfournisseur;

    private Session session;

    private Date datepayement;

    private BigDecimal montant;

}


