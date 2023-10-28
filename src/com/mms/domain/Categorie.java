package com.mms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Categorie implements java.io.Serializable {

    private int idcategorie;

    private String designation;

    private Integer quantitestock;

    private Integer prixunitairevente;

    private Integer prixunitaireachat;

    private Integer quantiteIdeale;

    private Integer quantiteSecurite;

}
