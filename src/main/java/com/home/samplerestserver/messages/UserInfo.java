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

    public UserInfo() {
        this.userName = "";
        this.credential = new Credential("", -1);
    }

    public UserInfo(String userName, Credential credential) {
        this.userName = userName;
        this.credential = credential;
    }

    public UserInfo(String userName, String nickName, int pinCode) {
        this.userName = userName;
        this.credential = new Credential(nickName, pinCode);
    }

    @XmlElement
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlElement
    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.userName);
        hash = 59 * hash + Objects.hashCode(this.credential);
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
        final UserInfo other = (UserInfo) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.credential, other.credential)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserInfo{" + "userName=" + userName + ", credential=" + credential + '}';
    }
}
