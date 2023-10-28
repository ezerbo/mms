package com.mms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lignecreditvente implements java.io.Serializable {

    private Produit produit;

    private Creditclient creditclient;

    private Integer quantitelignecredit;

    private BigDecimal montantlignecreditht;

    private BigDecimal montantlignecreditttc;

}
