package com.mms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lignedevente {

    private int idLigneDeVente;

    private Vente vente;

    private Categorie categorie;

    private Integer quantitelignevente;

    private Double montantligneventeht;

    private Double montantLigneVenteTva;

    private Double montantligneventettc;
}
