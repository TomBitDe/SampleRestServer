package com.home.samplerestserver.messages.signed;

import com.home.samplerestserver.util.MD5Signature;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public abstract class SignableMessage implements Serializable {
    private static final long serialVersionUID = 800L;
    
    private SignHeader signHeader = new SignHeader();

    public SignHeader getSignHeader() {
        return signHeader;
    }

    public void setSignHeader(SignHeader signHeader) {
        this.signHeader = signHeader;
    }
    
    public void sign(String jsonString, String key) throws NoSuchAlgorithmException {
        signHeader.setSign(MD5Signature.calculateMD5Signature(jsonString, key));
    }
    
    public boolean verify(String jsonData, String key) throws NoSuchAlgorithmException {
        return MD5Signature.verifySignature(jsonData, signHeader.getSign(), key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SignHeader {");
        sb.append(signHeader);
        sb.append('}');
        return sb.toString();
    }
}
