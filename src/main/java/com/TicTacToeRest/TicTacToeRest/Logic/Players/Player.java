package com.TicTacToeRest.TicTacToeRest.Logic.Players;

import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.Parser;

import java.util.Scanner;

public class Player {
    //class fields
    public static int id = 0;
    private static int idPlayer = 1;
    private String name = "Vasia";
    private String marker;
    //constructors
    public Player(){
        id++;
    }
    //getters
    public String getName(){return name;}
    //setters
    public void setName(String name) {
        this.name = name;
        Parser.addHistory(name);
    }
    public void setMarker(String marker){
        this.marker=marker;
    }
    //methods
    /*Метод Спрашивающий имя игрока*/
    /*Метод записывающий в XML данные игрока*/
    /*Метод записывающий в XML ходы игрока*/
}
