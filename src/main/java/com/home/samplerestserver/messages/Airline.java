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

    private String code;
    private String name;

    /**
     * Create an Airline with empty code and name.
     */
    public Airline() {
        code = "";
        name = "";
    }

    /**
     * Create an Airline with the given code and name.
     *
     * @param code the code e.g. "LH"
     * @param name the name e.g. "Lufthansa"
     */
    public Airline(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Get the airline code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the airline code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get the airline name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the airline name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

   /**
    * {@inheritDoc}
    */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.code);
        hash = 47 * hash + Objects.hashCode(this.name);
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
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Airline{" + "code=" + code + ", name=" + name + '}';
    }
}
