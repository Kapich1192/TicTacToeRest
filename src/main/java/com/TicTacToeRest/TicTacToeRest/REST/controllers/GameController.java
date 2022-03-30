package com.TicTacToeRest.TicTacToeRest.REST.controllers;

import com.TicTacToeRest.TicTacToeRest.REST.service.ScannerJsonFiles;
import com.TicTacToeRest.TicTacToeRest.REST.exceptions.NotFoundException;
import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.Load;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("gameplay")
public class GameController {
    private static int counter = 0;
    public List<Map<String,String>> games = lister();

    /*Метод заполняющий лист с играми*/
    public static List<Map<String,String>> lister(){
        ArrayList<String>listGamesName = ScannerJsonFiles.displayAllJsonFilesDirectories();
        String fileName;
        String id;
        List<Map<String,String>> games = new ArrayList<>();
        for (int i = 0;i<listGamesName.size();i++) {
            counter++;
            fileName =  listGamesName.get(i);
            String finalFileName = fileName;
            id = counter +"";
            String finalId = id;
            games.add(new HashMap<String, String>() {{
                put("id", finalId);
                put("text", finalFileName);
            }});
        }
        return games;
    }
    //GET
    @GetMapping
    public List<Map<String,String>> list(){
        System.out.println("Work list");
        lister();
        return games;
    }
    @GetMapping("{id}")
    public Map<String,String> getOne(@PathVariable String id){
        System.out.println("Work getOne");
        Map<String,String> game = getGame(id);
        String gameName = game.toString();
        gameName = gameName.substring(gameName.indexOf("Game"),gameName.length()-1);
        System.out.println(gameName);
        Load loader = new Load();
        loader.printGameJSON(gameName);
        return getGame(id);
    }
    private Map<String, String> getGame(String id) {
        return games.stream()
                .filter(game -> game.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
    //POST
    @PostMapping
    public  Map<String,String> create(@RequestBody Map<String,String> message){
        message.put("id",String.valueOf(counter++));
        games.add(message);
        return message;
    }
    //PUT
    @PutMapping("{id}")
    public  Map<String,String>  update(@PathVariable String id, @RequestBody Map<String,String> message){
        Map<String, String> messageFromDb = getGame(id);
        messageFromDb.putAll(message);
        messageFromDb.put("id",id);
        return messageFromDb;
    }
    //DELETE
    @DeleteMapping("{id}")
    public void delete (@PathVariable String id){
        System.out.println("Work delete");
        Map<String,String> game = getGame(id);
        String gameName = game.toString();
        gameName = gameName.substring(gameName.indexOf("Game"),gameName.length()-1);
        System.out.println(gameName);
        File file = new File(gameName);
        Path path = file.toPath();
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        games.remove(game);
    }
}
