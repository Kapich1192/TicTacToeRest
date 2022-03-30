package com.TicTacToeRest.TicTacToeRest.Setting.Parsing;

import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.Adapter.Adapter;
import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingJSON.model.Game;
import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingJSON.model.Steps.Step;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    public static final int FIELD_X = 10;
    public static final int FIELD_O = 200;
    public static final int FIELD_GAME_OVER_WIN_X = 1000;
    public static final int FIELD_GAME_OVER_WIN_O = 2000;
    public static final int FIELD_GAME_OVER_DRAW = 5000;
    public static String player1,player2;
    public static int countWinPl1 = 0, countWinPl2=0, StepGame = 0, countGame = 0, k;
    public static ArrayList<String> history = new ArrayList<String>();
    public static XMLStreamWriter writer;
    public static Save saver;
    public static String xx,yy,markerX = "X",markerO = "O",kk;
    public static File gameJSON;
    public static Game games;
    public static Step step;
    public static void newGame()throws XMLStreamException, IOException{
        String game = "Game" + countGame +"." + player1 + "vs" + player2 ;
        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        writer = factory.createXMLStreamWriter(new FileOutputStream(game + ".xml"));
        saver = new Save();
        saver.startDocument(writer,countGame,player1,player2);
        games = new Game(countGame,player1,player2);
        gameJSON = saver.addGame(games);
    }
    /*Метод добавляющий в список строку с параметрами входа*/
    public static void addHistory(int temp,int y,int x) throws XMLStreamException, ParserConfigurationException, IOException,
            TransformerException, SAXException {

        if (StepGame == 0) {
            newGame();
        }
        if (temp == FIELD_X) {
            if (StepGame == 0) {
                countGame++;
            }
            StepGame++;
            String text = "Game №" + countGame + ". Ste game st=" + StepGame + ". Player " + player1 + " X cходил на x=" + x + ", y=" + y + ";";
            xx=""+x;
            yy=""+y;
            k = Adapter.adapter(y,x);
            kk = ""+k;
            step = new Step(StepGame,1,x,y);
            games.addStep(step);
            saver.refreshGame(games);
            saver.saveElement(writer,text,StepGame,xx,yy,markerX);
        } else if (temp == FIELD_O) {
            if (StepGame == 0) countGame++;
            StepGame++;
            String text = "игра №" + countGame + ". Шаг игры st=" + StepGame + ". игрок " + player2 + " O сходил на x=" + x + ", y=" + y + ";";
            xx=""+x;
            yy=""+y;
            k = Adapter.adapter(y,x);
            kk = ""+k;
            step = new Step(StepGame,2,x,y);
            games.addStep(step);
            saver.refreshGame(games);
            saver.saveElement(writer,text,StepGame,xx,yy,markerO);
        } else if (temp == FIELD_GAME_OVER_WIN_X) {
            StepGame = 0;
            countWinPl1++;
            String text ="PlayerId=1 name= "+player1+" symbol="+markerX;
            step = new Step(StepGame,1,0,0,text);
            games.addStep(step);
            saver.refreshGame(games);
            saver.saveElement(writer,text);
            saver.endDocument(writer);

        } else if (temp == FIELD_GAME_OVER_WIN_O) {
            StepGame = 0;
            countWinPl2++;
            String text ="PlayerId=2 name= "+player2+" symbol="+markerO;
            step = new Step(StepGame,2,0,0,text);
            games.addStep(step);
            saver.refreshGame(games);
            saver.saveElement(writer,text);
            saver.endDocument(writer);

        } else if (temp == FIELD_GAME_OVER_DRAW) {
            StepGame = 0;
            String text = "Draw!";
            step = new Step(StepGame,0,0,0,text);
            games.addStep(step);
            saver.refreshGame(games);
            saver.saveElement(writer,text);
            saver.endDocument(writer);


        }
    }
    /*Запишем имена игроков*/
    public static void addHistory(String string){
        if(player1 == null)player1 = string;
        else player2 = string;
    }

    private static class JSON {
    }
}


