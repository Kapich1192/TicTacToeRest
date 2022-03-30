package com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingXML;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface LoadXML{
    //methods
    /*Печатает игровое поле*/
    static void printGameField(String[][] gf){
        for(int i = 0; i<3;i++ ){
            for(int j =0; j<3;j++){
                System.out.print(gf[i][j]);
            }
            System.out.println("");
        }
    }
    /*Выводит на экран историю из списка*/
    static void printGame(ArrayList<String> list){
        String[][] gameField = {{"|-|","|-|","|-|"},
                {"|-|","|-|","|-|"},
                {"|-|","|-|","|-|"}};
        //цикл ходов

        String markerX = "|X|",markerO = "|O|";
        String playerId,x,y,playerName;
        //Определяем размер цикла ходов
        System.out.println("Парсинг игры из xml файла");
        String pars;
        for (int i = 0;i<list.size();i++){
            if(!list.get(i).startsWith("null")&&list.get(i).length()>2){
                pars = list.get(i);
                if(pars.startsWith("p")) {
                    playerId = pars.substring(pars.indexOf("playerId=") + 9, pars.indexOf("playerId=") + 10);
                    x = pars.substring(pars.indexOf("x=") + 2, pars.indexOf("x=") + 3);
                    y = pars.substring(pars.indexOf("y=") + 2, pars.indexOf("y=") + 3);
                    if(Integer.parseInt(playerId)==1){
                        gameField[Integer.parseInt(x)][Integer.parseInt(y)] = markerX;
                        printGameField(gameField);
                        System.out.println("");
                    }else if(Integer.parseInt(playerId)==2){
                        gameField[Integer.parseInt(x)][Integer.parseInt(y)] = markerO;
                        printGameField(gameField);
                        System.out.println("");
                    }
                }else if(pars.startsWith("P")){
                    playerId = pars.substring(pars.indexOf("playerId=") + 10, pars.indexOf("playerId=") + 11);
                    playerName = pars.substring(pars.indexOf("name=") + 6, pars.indexOf(" sym"));

                    if(Integer.parseInt(playerId)==1){
                        System.out.println("Player " + playerId + " -> " + playerName + " is winner as 'X'");
                    }else if(Integer.parseInt(playerId)==2){
                        System.out.println("Player " + playerId + " -> " + playerName + " is winner as 'O'");
                    }
                }else if(pars.startsWith("D")){
                    System.out.println("Draw!");
                }
            }
        }

    }
    /*Возвращает строку из XML файла*/
    static ArrayList loadStringXML(String fileName) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<String> string = new ArrayList<>();
        DefaultHandler handler = new DefaultHandler(){

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                String name = attributes.getValue("Step");
                if(name != null && !name.isEmpty()){
                    System.out.println(name);
                }
                string.add(attributes.getLocalName(1)+"="+attributes.getValue(1)+";"+
                        attributes.getLocalName(2)+"="+attributes.getValue(2)+";"+
                        attributes.getLocalName(3)+"="+attributes.getValue(3)+";");
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                String str= "";
                for(int i = 0; i< length;i++){
                    str +=ch[start + i];
                }
                string.add(str);
            }
        };
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        File file = new File(fileName);
        parser.parse(file,handler);
        return string;
    }

}
