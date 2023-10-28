package com.mms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vente {

    private int numerovente;

    private Client client;

    private Session session;

    private Etat etat;

    private Double montantTotalVenteHt;

    private Double montantTotalVenteTtc;

    private Double montantTotalVenteTva;

    private Date datevente;

}
