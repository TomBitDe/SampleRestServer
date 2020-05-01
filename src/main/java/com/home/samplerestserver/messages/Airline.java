package com.home.samplerestserver.messages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Objects;

/**
 * Airline information.
 */
@JsonInclude(Include.NON_EMPTY)
public class Airline implements Serializable {
    private static final long serialVersionUID = 1L;

    private String airlineCode;
    private String airlineName;

    public Airline() {
        airlineCode = "";
        airlineName = "";
    }

    public Airline(String airlineCode, String airlineName) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.airlineCode);
        hash = 47 * hash + Objects.hashCode(this.airlineName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Airline other = (Airline) obj;
        if (!Objects.equals(this.airlineCode, other.airlineCode)) {
            return false;
        }
        if (!Objects.equals(this.airlineName, other.airlineName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Airline{" + "airlineCode=" + airlineCode + ", airlineName=" + airlineName + '}';
    }
}
