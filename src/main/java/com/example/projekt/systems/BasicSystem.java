package com.example.projekt.systems;

import com.example.projekt.users.Provider;
import com.example.projekt.users.RichUser;
import com.example.projekt.users.User;

import java.io.*;
import java.util.LinkedList;

/**
 * Basic system kde je implementovana serializacia
 *  pouzitie generickosti <T> pre LinkedList
 * @param <T>
 */
public abstract class  BasicSystem<T>  implements Serializable,Runnable{

    protected LinkedList<T> list = new LinkedList<T>();

    public LinkedList<T> getListAdmin() {
        return list;
    }
    public LinkedList<T> getList(User person) {
        return null;
    }

    public LinkedList<T> getList(RichUser person) {
        return null;
    }

    public LinkedList<T> getList(Provider person) {
        return null;
    }

    public void serialize(String file) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(list);
        out.close();
        fileOut.close();
        System.out.printf("Serializovane data su ulozene v %s \n",file);
    }

    public void deserialize(String file) throws ClassNotFoundException, IOException {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(fileIn);
            list = (LinkedList<T>)inputStream.readObject();
            inputStream.close();
            fileIn.close();
        } catch (ClassNotFoundException c){
            System.out.println("Class nebola najdena");
        }
    }
}
