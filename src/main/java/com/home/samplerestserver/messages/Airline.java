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

    /**
     * Create an Airline with empty code and name.
     */
    public Airline() {
        airlineCode = "";
        airlineName = "";
    }

    /**
     * Create an Airline with the given code and name.
     *
     * @param airlineCode the code e.g. "LH"
     * @param airlineName the name e.g. "Lufthansa"
     */
    public Airline(String airlineCode, String airlineName) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
    }

    /**
     * Get the airline code.
     *
     * @return the code
     */
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * Set the airline code.
     *
     * @param airlineCode the code
     */
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    /**
     * Get the airline name.
     *
     * @return the name
     */
    public String getAirlineName() {
        return airlineName;
    }

    /**
     * Set the airline name.
     *
     * @param airlineName the name
     */
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

   /**
    * {@inheritDoc}
    */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.airlineCode);
        hash = 47 * hash + Objects.hashCode(this.airlineName);
        return hash;
    }

   /**
    * {@inheritDoc}
    */
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

   /**
    * {@inheritDoc}
    */
    @Override
    public String toString() {
        return "Airline{" + "airlineCode=" + airlineCode + ", airlineName=" + airlineName + '}';
    }
}
