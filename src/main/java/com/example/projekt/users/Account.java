package com.example.projekt.users;

import java.io.Serializable;

/**
 * Trieda uctov, manazuje prihlasovanie,odhlasovanie
 */
public class Account implements Serializable {
    private final String login,password;
    private final HumanBeing owner;
    private boolean loggedIn;

    public HumanBeing getOwner() {
        return this.owner;
    }

    public String getLogin()
    {
        return login;
    }

    public Account(HumanBeing owner, String login, String pass) {
        this.owner = owner;
        this.login = login;
        this.password = pass;
        loggedIn = false;
    }

    public boolean loginUser(String Login, String pass) {
        if (Login.equals(login) && password.equals(pass)) {
            loggedIn = true;
        }
        return loggedIn;
    }

    public void logOutUser()
    {
        loggedIn = false;
    }
}
