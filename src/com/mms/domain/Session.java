package com.mms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session implements java.io.Serializable {

    private int idsession;

    private User user;

    private String datedebutsession;

    private String datefinsession;

    private Integer dureesession;

}
