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
public class Payementcreditclient implements java.io.Serializable {

    private PayementcreditclientId id;

    private Creditclient creditclient;

    private Session session;

    private Date datepayement;

    private BigDecimal montant;
}


