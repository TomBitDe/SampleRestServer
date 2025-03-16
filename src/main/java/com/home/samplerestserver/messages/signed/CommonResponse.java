package com.home.samplerestserver.messages.signed;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class CommonResponse extends SignableMessage implements Serializable {
    private static final long serialVersionUID = 5L;

    String status;
    String message;
    
    ErrorContent error = new ErrorContent();

    public CommonResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorContent getError() {
        return error;
    }

    public void setError(ErrorContent error) {
        this.error = error;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.status);
        hash = 37 * hash + Objects.hashCode(this.message);
        hash = 37 * hash + Objects.hashCode(this.error);
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
        final CommonResponse other = (CommonResponse) obj;
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return Objects.equals(this.error, other.error);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CommonResponse {");
        sb.append("status=").append(status);
        sb.append(", message=").append(message);
        sb.append(", error=").append(error);
        sb.append('}');
        return sb.toString();
    }
}
