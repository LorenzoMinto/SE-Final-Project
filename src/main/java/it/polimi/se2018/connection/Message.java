package it.polimi.se2018.connection;

import java.io.Serializable;

public abstract class Message implements Serializable{

    protected String content;

    public String getMessage(){
        return this.content;
    }
}