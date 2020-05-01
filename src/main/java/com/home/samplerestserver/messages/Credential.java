package com.home.samplerestserver.messages;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement
public class Credential implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nickName;
    private int pinCode;

    /**
     * The default constructor is a must. Otherwise an exception happens.
     */
    public Credential() {
        this.nickName = "";
        this.pinCode = -1;
    }

    public Credential(String nickName, int pinCode) {
        this.nickName = nickName;
        this.pinCode = pinCode;
    }

    @XmlElement
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @XmlElement
    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.nickName);
        hash = 11 * hash + this.pinCode;
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
        final Credential other = (Credential) obj;
        if (this.pinCode != other.pinCode) {
            return false;
        }
        if (!Objects.equals(this.nickName, other.nickName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Credential{" + "nickName=" + nickName + ", pinCode=" + pinCode + '}';
    }
}
