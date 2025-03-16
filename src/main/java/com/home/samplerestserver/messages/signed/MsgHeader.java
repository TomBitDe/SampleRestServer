package com.home.samplerestserver.messages.signed;

import com.home.samplerestserver.util.ConvertDateTime;
import java.io.Serializable;
import java.util.Objects;

public class MsgHeader implements Serializable {
   private static final long serialVersionUID = 3L;
   
   private String msg_id;
   private String msg_type;
   private String msg_crea_dttm = ConvertDateTime.toDTTM(ConvertDateTime.getCurrentDateTime());

    public MsgHeader() {
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public String getMsg_crea_dttm() {
        return msg_crea_dttm;
    }

    public void setMsg_crea_dttm(String msg_crea_dttm) {
        this.msg_crea_dttm = msg_crea_dttm;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.msg_id);
        hash = 97 * hash + Objects.hashCode(this.msg_type);
        hash = 97 * hash + Objects.hashCode(this.msg_crea_dttm);
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
        final MsgHeader other = (MsgHeader) obj;
        if (!Objects.equals(this.msg_id, other.msg_id)) {
            return false;
        }
        if (!Objects.equals(this.msg_type, other.msg_type)) {
            return false;
        }
        return Objects.equals(this.msg_crea_dttm, other.msg_crea_dttm);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("msg_id=").append(msg_id);
        sb.append(", msg_type=").append(msg_type);
        sb.append(", msg_crea_dttm=").append(msg_crea_dttm);
        return sb.toString();
    }
}
