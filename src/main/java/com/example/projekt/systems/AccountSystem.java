package com.example.projekt.systems;

import com.example.projekt.users.*;
import java.io.*;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Zakladna trieda uctov na ich vytvaranie a menezovanie, extenduje BasicSystem
 */
public class AccountSystem extends BasicSystem implements Serializable {
    private Account user;
    private static int m_id = 1;

    public Account getUser(){return user;}

    public LinkedList getList(User person) {
        return (LinkedList<Account>)list;
    }

    public LinkedList getList(RichUser richUser) {
        return (LinkedList<Account>)list;
    }

    public LinkedList getList(Provider provider) {
        return (LinkedList<Account>)list;
    }

    public boolean logInUser(String login, String pass) {
        for (Account account : (LinkedList<Account>)list)
        {
            if (account.loginUser(login, pass))
            {
                user = account;
                return true;
            }
        }
        return false;
    }

    public Account findAccountID(int id) {
        for(Account account : (LinkedList<Account>)list)
        {
            if (account.getOwner().getID() == id) {
                return account;
            }
        }
        return null;
    }

    public boolean existsUser(String login) {
        for (Account account : (LinkedList<Account>)list)
        {
            if (account.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
    public Account findAccountName(String name) {
        for(Account acc : (LinkedList<Account>)list)
        {
            if (acc.getOwner().getName().equals(name)) {
                return acc;
            }
        }
        return null;
    }

    public void addCheapUser(String name, String login, String password) {
        list.add(new Account(new CheapUser(name,m_id),login,password));
        m_id++;
    }
    public void addRichUser(String name, String login, String password) {
        list.add(new Account(new RichUser(m_id,name),login,password));
        m_id++;
    }
    public void addProvider(String name, String login, String password) {
        list.add(new Account(new Provider(m_id,name),login,password));
        m_id++;
    }
    public void addAdmin(String name, String login, String password) {
        list.add(new Account(new Admin(m_id,name),login,password));
        m_id++;
    }
    @Override
    public void run() {
        try {
            serialize("accounts.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
