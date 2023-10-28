package com.mms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fournisseur {

    private int idfournisseur;

    private String nomFournisseur;

    private String prenomFournisseur;

    private String telephonefournisseur;
}
