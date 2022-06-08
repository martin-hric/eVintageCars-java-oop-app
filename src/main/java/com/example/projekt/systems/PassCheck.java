package com.example.projekt.systems;

public class PassCheck {

    /**
     * check ci ma hesla aspon 10 znakov, 1 velke pismeno a 1 cislo
     * @param pass pass
     * @throws PassException
     */
    public void passCheck(String pass) throws PassException
    {
        if (pass.length() < 10) {
            throw new PassException();
        }
        boolean Big = false, Num = false;

        for (int i = 0; i < pass.length(); i++)
        {
            if(pass.charAt(i) <= 90 && pass.charAt(i) >= 65) Big = true;
            if(pass.charAt(i) >= 48 && pass.charAt(i) <= 57) Num = true;
        }
        if(!Big || !Num){
            throw new PassException();
    }
}
}
