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
public class Lignedachat implements java.io.Serializable {

    private Produit produit;

    private Achat achat;

    private Integer quantiteligneachat;

    private BigDecimal montantligneachatht;

    private BigDecimal montantligneventettc;
}


