package com.home.samplerestserver.messages;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Credential information.
 */
@XmlRootElement
public class Credential implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * No @XmlElement annotation here. Otherwise an exception happens.
     */
    private String nickName;
    private int pinCode;

    /**
     * The default constructor is a must. Otherwise an exception happens.
     */
    public Credential() {
        this.nickName = "";
        this.pinCode = -1;
    }

    /**
     * Create a credential with the given nickname and pin code.
     *
     * @param nickName the nickname
     * @param pinCode  the pin code
     */
    public Credential(String nickName, int pinCode) {
        this.nickName = nickName;
        this.pinCode = pinCode;
    }

    /**
     * The annotation must be placed here, not at the private declaration position.
     *
     * @return the nickname
     */
    @XmlElement
    public String getNickName() {
        return nickName;
    }

    /**
     * Set the nickname.
     *
     * @param nickName the nickname to assign
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * The annotation must be placed here, not at the private declaration position.
     *
     * @return the pin code
     */
    @XmlElement
    public int getPinCode() {
        return pinCode;
    }

    /**
     * Set the pin code for this credential.
     *
     * @param pinCode the pin code to assign
     */
    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

   /**
    * {@inheritDoc}
    */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.nickName);
        hash = 11 * hash + this.pinCode;
        return hash;
    }

   /**
    * {@inheritDoc}
    */
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
        return Objects.equals(this.nickName, other.nickName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Credential{" + "nickName=" + nickName + ", pinCode=" + pinCode + '}';
    }
}
