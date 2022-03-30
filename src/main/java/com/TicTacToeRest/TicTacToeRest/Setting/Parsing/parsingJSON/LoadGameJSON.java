package com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingJSON;

import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.Load;
import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingJSON.model.Game;
import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingJSON.model.Steps.Step;
import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingXML.LoadXML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public interface LoadGameJSON {
    /*Метод преобразующий json Файл в обект Game*/
    default Game jsonToGame(String fileName){
        int id = 0;
        String name1 = null;
        String name2= null;
        String gameResult;
        ArrayList<Step> stepsList = new ArrayList<Step>();
        int stepId;
        int playerId;
        int x;
        int y;
        Game gameForParse;
//------------------------------------
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)){
            JSONObject root = (JSONObject) parser.parse(reader);
            id = Integer.parseInt(root.get("id")+"");

        }catch (Exception e){
            System.out.println("Parsing error" + e.toString());
        }
//-----------

        try (FileReader reader = new FileReader(fileName)){
            JSONObject root = (JSONObject) parser.parse(reader);
            name1 = (String)root.get("name1");

        }catch (Exception e){
            System.out.println("Parsing error" + e.toString());
        }

//-----------
        try (FileReader reader = new FileReader(fileName)){
            JSONObject root = (JSONObject) parser.parse(reader);
            name2 = (String)root.get("name2");

        }catch (Exception e){
            System.out.println("Parsing error" + e.toString());
        }
//-----------
        gameForParse = new Game(id,name1,name2);
//-----------
        try (FileReader reader = new FileReader(fileName)){
            JSONObject root = (JSONObject) parser.parse(reader);
            JSONArray steps = (JSONArray) root.get("stepsList");
            for(Object it: steps){
                JSONObject stepsOb = (JSONObject) it;
                stepId = Integer.parseInt(stepsOb.get("stepId") + "");
                playerId = Integer.parseInt(stepsOb.get("playerId")+"");
                x = Integer.parseInt(stepsOb.get("x")+"");
                y = Integer.parseInt(stepsOb.get("y")+"");
                gameResult = (String)stepsOb.get("gameResult");
                gameForParse.addStep(new Step(stepId,playerId,x,y,gameResult));
            }
        }catch (Exception e){
            System.out.println("Parsing error" + e.toString());
        }
//------------------------------------
        return gameForParse;

    }
/*Метод печатающий игру*/
    default void printGameJSON(String nameGame){
        String[][] gameField = {{"|-|","|-|","|-|"},
                {"|-|","|-|","|-|"},
                {"|-|","|-|","|-|"}};
        String markerX = "|X|",markerO = "|O|";
        int playerId,x,y,stepId;
        String playerName;

        System.out.println("Парсинг игры из json файла");

        Load loader = new Load();
        Game game = loader.jsonToGame(nameGame);
        List<Step> list = game.getStepsList();
        for(int i = 0;i<list.size();i++) {
            playerId = list.get(i).getPlayerId();
            x = list.get(i).getX();
            y = list.get(i).getY();
            stepId = list.get(i).getStepId();
            if(playerId==1 && stepId != 0  ){
                gameField[x][y] = markerX;
                LoadXML.printGameField(gameField);
                System.out.println("");
            }
            if(playerId==2 &&stepId != 0  ){
                gameField[x][y] = markerO;
                LoadXML.printGameField(gameField);
                System.out.println("");
            }
            if(stepId == 0 ){
                if(playerId == 1){
                    System.out.println("Player " + playerId + " -> " + game.getName1() + " is winner as '" + "X" + "'!");
                }
                if(playerId == 2){
                    System.out.println("Player " + playerId + " -> " + game.getName2() + " is winner as '" + "O" + "'!");
                }
                if(playerId == 0){
                    System.out.println("Draw!");
                }
            }

        }
    }
}
