package com.home.samplerestserver.messages.signed;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Objects;

@JsonInclude(Include.ALWAYS)
public class WeighingRequest extends SignableMessage implements Serializable {
    private static final long serialVersionUID = 9L;
    
    private Message1201 message = new Message1201();

    public WeighingRequest() {
    }

    public Message1201 getMessage() {
        return message;
    }

    public void setMessage(Message1201 message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.message);
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
        final WeighingRequest other = (WeighingRequest) obj;
        return Objects.equals(this.message, other.message);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WeighingRequest {");
        sb.append(super.toString());
        sb.append(", ").append(message);
        sb.append('}');
        return sb.toString();
    }
}
