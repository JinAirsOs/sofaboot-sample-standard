package com.alipay.sofa.facade.model.request;

import java.io.Serializable;


/**
 * @author kerry
 * @date 2019-5-21 15:02:25
 */
public class LoginRequest implements Serializable {

    private String name;

    private String password;

    /**
     * Gets get name.
     *
     * @return the get name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets set name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets get password.
     *
     * @return the get password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets set password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
