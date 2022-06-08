package com.example.projekt.cars;

import com.example.projekt.users.Account;
import com.example.projekt.users.Admin;

import java.io.Serializable;

public abstract class Bid implements ProductBeing, Serializable {
    public abstract void acceptBid(Admin admin);
    public abstract void declineBid(Admin admin);
    public abstract Account getBidder();
}
