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
public class Depense implements java.io.Serializable {

    private int iddepense;

    private Session session;

    private String libelledepense;

    private BigDecimal montantdepense;
}


