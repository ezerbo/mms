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
public class Password implements java.io.Serializable {

    private int idmdp;

    private User user;

    private String valeurmdp;

    private Date datecreationmdp;

    private Date datevaliditemdp;

}


