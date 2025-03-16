package com.home.samplerestserver.messages.signed;

import java.io.Serializable;
import java.util.Objects;

public class SignHeader implements Serializable {
    private static final long serialVersionUID = 1L;
    
    String charset = "UTF-8";
    String signType = "MD5";
    String mchId = "ICP";
    String sign;

    public SignHeader() {
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.charset);
        hash = 29 * hash + Objects.hashCode(this.signType);
        hash = 29 * hash + Objects.hashCode(this.mchId);
        hash = 29 * hash + Objects.hashCode(this.sign);
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
        final SignHeader other = (SignHeader) obj;
        if (!Objects.equals(this.charset, other.charset)) {
            return false;
        }
        if (!Objects.equals(this.signType, other.signType)) {
            return false;
        }
        if (!Objects.equals(this.mchId, other.mchId)) {
            return false;
        }
        return Objects.equals(this.sign, other.sign);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("charset=").append(charset);
        sb.append(", signType=").append(signType);
        sb.append(", mchId=").append(mchId);
        sb.append(", sign=").append(sign);
        return sb.toString();
    }
}
