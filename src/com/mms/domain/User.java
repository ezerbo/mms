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
public class User implements java.io.Serializable {

    private int idutilisateur;

    private UserType userType;

    private String nomutilisateur;

    private String prenomutilisateur;

    private String loginutilisateur;

    private String telephoneutilisateur;

    private Date dateembaucheutilisateur;

    private Date datedepartutilisateur;
}
