package com.home.samplerestserver.messages.signed;

import com.home.samplerestserver.util.SignatureAlgorithm;
import com.home.samplerestserver.util.SignatureAlgorithms;
import java.io.Serializable;
import java.security.InvalidKeyException;
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
    
    public void sign(String jsonString, String key)
            throws NoSuchAlgorithmException, InvalidKeyException {
        SignatureAlgorithm algorithm = SignatureAlgorithms.of(signHeader.getSignType());
        signHeader.setSign(algorithm.calculate(jsonString, key));
    }

    public boolean verify(String jsonData, String key)
            throws NoSuchAlgorithmException, InvalidKeyException {
        SignatureAlgorithm algorithm = SignatureAlgorithms.of(signHeader.getSignType());
        return algorithm.verify(jsonData, signHeader.getSign(), key);
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
