package com.mms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserType implements java.io.Serializable {

    private int idtypeutilisateur;

    private String libelletypeutilisateur;

    private Set<User> users = new HashSet<>(0);

}
