package com.mms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayementcreditfournisseurId implements java.io.Serializable {

    private int idcreditfournisseur;

    private int idsession;

    public boolean equals(Object other) {
        if ((this == other)) return true;
        if ((other == null)) return false;
        if (!(other instanceof PayementcreditfournisseurId)) return false;
        PayementcreditfournisseurId castOther = (PayementcreditfournisseurId) other;
        return (this.getIdcreditfournisseur() == castOther.getIdcreditfournisseur())
                && (this.getIdsession() == castOther.getIdsession());
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + this.getIdcreditfournisseur();
        result = 37 * result + this.getIdsession();
        return result;
    }


}


