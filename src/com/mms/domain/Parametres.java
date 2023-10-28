package com.mms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Parametres implements java.io.Serializable {

    private int dureeviemdp;

    private Integer longueurmdp;

    private Integer tentativemdp;

    private Integer tempsinactivitesmdp;

    private Double tauxtva;
}


