package com.mms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayementcreditclientId implements java.io.Serializable {

    private int idcreditclient;

    private int idsession;

    public boolean equals(Object other) {
        if ((this == other)) return true;
        if ((other == null)) return false;
        if (!(other instanceof PayementcreditclientId)) return false;
        PayementcreditclientId castOther = (PayementcreditclientId) other;
        return (this.getIdcreditclient() == castOther.getIdcreditclient())
                && (this.getIdsession() == castOther.getIdsession());
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + this.getIdcreditclient();
        result = 37 * result + this.getIdsession();
        return result;
    }

}


