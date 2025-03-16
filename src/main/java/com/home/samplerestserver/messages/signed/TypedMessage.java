package com.home.samplerestserver.messages.signed;

import java.io.Serializable;

public abstract class TypedMessage implements Serializable {
    private static final long serialVersionUID = 900L;
    
    private MsgHeader header = new MsgHeader();

    public MsgHeader getHeader() {
        return header;
    }

    public void setHeader(MsgHeader header) {
        this.header = header;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MsgHeader {");
        sb.append(header);
        sb.append('}');
        return sb.toString();
    }
}
