package com.alipay.sofa.facade.model.request;

import java.io.Serializable;


/**
 * @author kerry
 * @date 2019-5-21 15:02:12
 */
public class RegisterUserRequest implements Serializable {

    private String name;

    private String password;

    private String repeatPassword;

    private String email;

    private Long phone;

    /**
     * Constructor.
     */
    public RegisterUserRequest() {
    }

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

    /**
     * Gets get repeat password.
     *
     * @return the get repeat password
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Sets set repeat password.
     *
     * @param repeatPassword the repeat password
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * Gets get email.
     *
     * @return the get email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets set email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets get phone.
     *
     * @return the get phone
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * Sets set phone.
     *
     * @param phone the phone
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
