package com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingJSON.model;

import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingJSON.model.Steps.Step;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
@JsonAutoDetect
public class Game {
    private int id;
    private String name1;
    private String name2;
    private String gameResult;
    private ArrayList<Step> stepsList;

//Constructors
    public Game(int id) {
        this.id = id;
        stepsList = new ArrayList<Step>();
    }

    public Game(int id, String name1, String name2) {
        this.id = id;
        this.name1 = name1;
        this.name2 = name2;
        stepsList = new ArrayList<Step>();
    }

    //Getters
    public int getId() { return id; }
    public String getName1() { return name1; }
    public String getName2() { return name2; }
    public ArrayList<Step> getStepsList() {
        return stepsList;
    }

//Setters
    public void setId(int id) { this.id = id; }
    public void setStepsList(ArrayList<Step> stepsList) { this.stepsList = stepsList; }
    public void setName1(String name1) { this.name1 = name1; }
    public void setName2(String name2) { this.name2 = name2; }
//Methods
public void addStep(Step step){
    stepsList.add(step);
}
public Step gameResultValue(String name){
    gameResult = name + " is winner";
    Step step = new Step(gameResult);
    stepsList.add(step);
    return step;
}
    public void gameResultValue(){
        gameResult = "Draw!";

    }


    @Override
    public String toString() {
        return "Game" ;

    }
}
