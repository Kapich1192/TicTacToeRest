package com.TicTacToeRest.TicTacToeRest.Application;

import com.TicTacToeRest.TicTacToeRest.Logic.Game.TicTacToe;
import com.TicTacToeRest.TicTacToeRest.REST.TicTacToeRestApplication;
import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.Load;
import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingXML.LoadXML;
import org.springframework.boot.SpringApplication;
import org.xml.sax.SAXException;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.*;

public class Application {
    public static void main(String[] args){
        JFrame window = new JFrame("TicTacToe"); // Создаем наше главное окно c игрой
        window.setSize(400,400);//Задаем размер окна
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Добавляем кнопку закрыть
        window.setLayout(new BorderLayout());//менеджер компоновки
        window.setLocationRelativeTo(null);// чтобы окно было по центру экрана
        JFrame windowSetting = new JFrame("TicTacToeSetting"); // Создаем наше окно с настройками
        JPanel panel = new JPanel(); // Создаем панель для кнопок
        windowSetting.setSize(200,400);//Задаем размер окна
        windowSetting.setLocation(window.getX()+window.getHeight(),window.getY());//Размещаем окно с настройками

        JButton parcerButton = new JButton(new MyAction());//Создаем кнопку для загрузчика парсера
        parcerButton.setText("LoadParsingXML"); // Устанавливаем ей имя
        JButton parcer2Button = new JButton(new MyAction2());//Создаем кнопку для загрузчика парсера
        parcer2Button.setText("LoadParsingJSON"); // Устанавливаем ей имя
        JButton startServerButton = new JButton(new MyAction3());//Создаем кнопку для загрузчика парсера
        startServerButton.setText("Start Server Rest"); // Устанавливаем ей имя

        panel.add(parcerButton);// Размещаем кнопку на панели
        panel.add(parcer2Button);// Размещаем кнопку на панели
        panel.add(startServerButton);// Размещаем кнопку на панели

        windowSetting.add(panel);//Выводим панель в окно с настройками
        TicTacToe game = new TicTacToe();//Создаем объект нашего класса игры
        window.add(game);//добавлем созданный объект в окно с игрой
        windowSetting.setVisible(true);//включаем видимость окна с настройками
        window.setVisible(true);//включаем видимость окна с игрой
    }
    /*Класс для запуска парсера из кнопки в настройках*/
    static class MyAction extends AbstractAction{

        /*дескрипшен кнопки*/
        MyAction(){
            putValue(AbstractAction.SHORT_DESCRIPTION,"вывод файла с историей в консоль\n " +
                    "Требуется ввести имя файла в формате \"имя_файла.xml\"");
        }
        /*Активити кнопки*/
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList str = null;
            String string = JOptionPane.showInputDialog("Enter the XML file name for parsing");

            try {
                str = LoadXML.loadStringXML(string);
            } catch (ParserConfigurationException | IOException | SAXException ex) {
                ex.printStackTrace();
            }
            LoadXML.printGame(str);
                System.out.println("The name was not entered");

        }
    }
    /*Класс для запуска парсера из кнопки в настройках*/
    static class MyAction2 extends AbstractAction{

        /*дескрипшен кнопки*/
        MyAction2(){
            putValue(AbstractAction.SHORT_DESCRIPTION,"output of the file with the history to the console \\n \" +\n" +
                    "\"It is required to enter the file name in the format \"file_name.xml\"");
        }
        /*Активити кнопки*/
        @Override
        public void actionPerformed(ActionEvent e) {

            String string = JOptionPane.showInputDialog("Enter the name of the JSON file for parsing");
            Load loader = new Load();
            loader.printGameJSON(string);
        }
    }
    static class MyAction3 extends AbstractAction{

        /*дескрипшен кнопки*/
        MyAction3(){
            putValue(AbstractAction.SHORT_DESCRIPTION,"Server Start");
        }
        /*Активити кнопки*/
        @Override
        public void actionPerformed(ActionEvent e) {
                SpringApplication.run(TicTacToeRestApplication.class);
        }
    }
}

