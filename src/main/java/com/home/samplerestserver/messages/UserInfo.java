package com.home.samplerestserver.messages;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User information.
 */
@XmlRootElement
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
    private Credential credential;

    /**
     * Create a user info with valid defaults.
     */
    public UserInfo() {
        this.userName = "";
        this.credential = new Credential("", -1);
    }

    /**
     * Create a user info.
     *
     * @param userName   the users name
     * @param credential the credentials of the user
     */
    public UserInfo(String userName, Credential credential) {
        this.userName = userName;
        this.credential = credential;
    }

    /**
     * Create a user info.
     *
     * @param userName the users name
     * @param nickName the users nickname
     * @param pinCode  the users pin code
     */
    public UserInfo(String userName, String nickName, int pinCode) {
        this.userName = userName;
        this.credential = new Credential(nickName, pinCode);
    }

    /**
     * Get the users name.
     *
     * @return the name
     */
    @XmlElement
    public String getUserName() {
        return userName;
    }

    /**
     * Set the users name.
     *
     * @param userName the name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the users cedential.
     *
     * @return the credential
     */
    @XmlElement
    public Credential getCredential() {
        return credential;
    }

    /**
     * Set the users credential.
     *
     * @param credential the credential
     */
    public void setCredential(Credential credential) {
        this.credential = credential;
    }

   /**
    * {@inheritDoc}
    */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.userName);
        hash = 59 * hash + Objects.hashCode(this.credential);
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
        final UserInfo other = (UserInfo) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.credential, other.credential)) {
            return false;
        }
        return true;
    }

   /**
    * {@inheritDoc}
    */
    @Override
    public String toString() {
        return "UserInfo{" + "userName=" + userName + ", credential=" + credential + '}';
    }
}
