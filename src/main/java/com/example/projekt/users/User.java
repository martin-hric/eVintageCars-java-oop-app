package com.example.projekt.users;

import com.example.projekt.cars.Bid;

import java.util.LinkedList;

/**
 * Abstraktna trieda od ktorej dedia vsetky ostatne triedy pouzivatelov
 */
public abstract class User implements HumanBeing {
    protected LinkedList<Bid> myBids = new LinkedList<Bid>();

    public LinkedList<Bid> getMyBids(){
        return this.myBids;
    }

    public void setMyBids(LinkedList<Bid> list){
        this.myBids = list;
    }

    public void addBid(Bid bid){
        this.myBids.add(bid);
    }

    public void deleteBid(Bid bid){
        this.myBids.remove(bid);
    }

}
