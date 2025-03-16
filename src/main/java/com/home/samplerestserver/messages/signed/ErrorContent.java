package com.home.samplerestserver.messages.signed;

import java.util.Objects;

public class ErrorContent {
    private static final long serialVersionUID = 5L;

    private Integer code;
    private String detail;

    public ErrorContent() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.code);
        hash = 47 * hash + Objects.hashCode(this.detail);
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
        final ErrorContent other = (ErrorContent) obj;
        if (!Objects.equals(this.detail, other.detail)) {
            return false;
        }
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ErrorContent {");
        sb.append("code=").append(code);
        sb.append(", detail=").append(detail);
        sb.append('}');
        return sb.toString();
    }
}
