package com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingJSON;

import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingJSON.model.Game;
import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingJSON.model.Steps.Step;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public interface SaveGameJSON {

    /* Метод Создает файл с игрой */
    default File addGame(int id,String name1,String name2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String gameName =  "Game" + id +"." + name1 + "vs" + name2;
        Game game = new Game(id,name1,name2);
        File saveFile = new File(gameName + ".json");
        objectMapper.writeValue(saveFile,game);
        return saveFile;
    }
    default File addGame(Game game) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String gameName =  "Game" + game.getId() +"." + game.getName1() + "vs" + game.getName2();
        File saveFile = new File(gameName + ".json");
        objectMapper.writeValue(saveFile,game);
        return saveFile;
    }
    default void refreshGame(Game game) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String gameName =  "Game" + game.getId() +"." + game.getName1() + "vs" + game.getName2();
        File saveFile = new File(gameName + ".json");
        objectMapper.writeValue(saveFile,game);

    }
    /* Метод добавляет шаг */
    default void addStep(File saveFile,Game game, int stepId, int playerId, int x, int y) throws IOException {
        Step step = new Step(stepId,playerId,x,y);
        ArrayList<Step> GameList = game.getStepsList();
        GameList.add(step);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(saveFile,GameList);
    }

}