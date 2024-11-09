package com.home.samplerestserver.messages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Information on all Airlines.
 */
@JsonInclude(Include.NON_EMPTY)
public class AirlineInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Airline> airlines;

    public AirlineInfo() {
    }

    public AirlineInfo(List<Airline> airlines) {
        this.airlines = airlines;
    }

    public List<Airline> getAirlines() {
        return airlines;
    }

    public void setAirlines(List<Airline> airlines) {
        this.airlines = airlines;
    }

    @Override
    public int hashCode() {
        return Objects.hash(airlines);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AirlineInfo other = (AirlineInfo) obj;
        return Objects.equals(airlines, other.airlines);
    }

    @Override
    public String toString() {
        return "AirlineInfo{" + "airlines=" + airlines + '}';
    }
}
