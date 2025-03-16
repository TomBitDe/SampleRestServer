package com.home.samplerestserver.messages.signed;

import java.io.Serializable;
import java.util.Objects;

public class Message1201 extends TypedMessage implements Serializable {
    private static final long serialVersionUID = 5L;
    
    String scale;

    public Message1201() {
        super.getHeader().setMsg_type("1201");
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.scale);
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
        final Message1201 other = (Message1201) obj;
        return Objects.equals(this.scale, other.scale);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Message1201 {");
        sb.append(super.toString());
        sb.append(", scale=").append(scale);
        sb.append('}');
        return sb.toString();
    }
}
