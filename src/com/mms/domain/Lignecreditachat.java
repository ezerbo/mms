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
public class Lignecreditachat implements java.io.Serializable {

    private Produit produit;

    private Creditfournisseur creditfournisseur;

    private Integer quantiteligneachat;

    private BigDecimal montantligneachatht;

    private BigDecimal montantligneachatttc;
}
