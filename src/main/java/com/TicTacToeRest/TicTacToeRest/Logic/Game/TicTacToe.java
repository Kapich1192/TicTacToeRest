package com.TicTacToeRest.TicTacToeRest.Logic.Game;


import com.TicTacToeRest.TicTacToeRest.Logic.Players.Player;
import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.Parser;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class TicTacToe extends JComponent {
    //class fields
    public static final int FIELD_EMPTY = 0;
    public static final int FIELD_X = 10;
    public static final int FIELD_O = 200;
    public static final int FIELD_GAME_OVER_WIN_X = 1000;
    public static final int FIELD_GAME_OVER_WIN_O = 2000;
    public static final int FIELD_GAME_OVER_DRAW = 5000;
    int[][] field; // Массив для игрового поля
    boolean isXturn;// Переменная для определения хода
    //constructors
    public TicTacToe(){
        Player p1 = new Player();
        Player p2 = new Player();
        p1.setName(JOptionPane.showInputDialog("Enter the name of the first player playing for the crosses"));
        p2.setName(JOptionPane.showInputDialog("Enter the name of the second player playing for the noughts.If you want to play with the computer, enter ROBOT"));
        p1.setMarker("X");
        p2.setMarker("O");
        enableEvents(AWTEvent.MOUSE_EVENT_MASK); // Включаем считывание кликов мыши
        field = new int[3][3]; // Создаем массив при создании игры
        initGame();// Заполняем массив нулями
    }
    //methods
    /*Метод определяющий победителя*/
    int checkState(){
        int diag = 0;
        int diag2 =0;

        for(int i=0;i<3;i++){
            //Сумма значений по диагонали от левого угла
            diag += field[i][i];
            //Сумма значений по диагонали от правого угла
            diag2 += field[i][2-i];
        }
        //Если по диагонали стоят одни крестики или нолики выходим из метода
        if(diag == FIELD_O*3 || diag ==FIELD_X * 3){return diag;}
        //Если по диагонали стоят одни крестики или нолики выходим из метода
        if(diag2 == FIELD_O*3 || diag2 ==FIELD_X * 3){return diag2;}

        int check_i, check_j;
        boolean hasEmpty = false;
        //Проверим по всем рядам
        for (int i = 0; i<3; i++){
            check_i = 0;
            check_j = 0;
            for (int j=0;j<3;j++){
                if(field[i][j] == 0){
                    hasEmpty = true;
                }
                check_i += field[i][j];
                check_j += field[j][i];
            }
            //Если выиграли крестики или нолики выходим
            if(check_i==FIELD_O*3 || check_i == FIELD_X*3){
                return check_i;
            }
            if(check_j==FIELD_O*3|| check_j == FIELD_X*3){return check_j;}
        }
        if(hasEmpty) return 0;else return -1;
    }
    /*Метод просматривает массив и рисует крестики и нолики*/
    public void drawXO(Graphics graphics){
        //Сканируем масссив вложенным циклом
        for(int i = 0; i<3;i++){
            for(int j = 0; j<3;j++){
                //если в ячейке идентификатор крестик рисуем его
                if(field[i][j] == FIELD_X){
                    drawX(i,j,graphics);
                }else
                    //если в ячейке идентификатор нолик рисуем его
                    if (field[i][j] == FIELD_O){
                        drawO(i,j,graphics);
                    }

            }
        }
    }
    /*Метод выполняет отрисовку крестика*/
    public void drawX(int i , int j, Graphics graphics){
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i *dw;
        int y = j *dh;
        //Отрисовка линии от верхнего левого угла в правый нижний
        graphics.drawLine(x,y,x+dw,y+dh);
        //Отрисовка линии от левого нижнего угла в правый верхний
        graphics.drawLine(x,y+dh,x+dw,y);
    }
    /*Метод выполняет отрисовку нолика*/
    public void drawO(int i , int j, Graphics graphics){
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i *dw;
        int y = j *dh;
        //Отрисовка нолика
        graphics.drawOval(x+5*dw/100,y,dw*9/10,dh);
    }
    /*Метод заполняет игровое поле пустыми значениями при начале в игру*/
    public void initGame(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                field[i][j] = FIELD_EMPTY; //Очищаем массив, заполняя его нулями
            }
        }
        isXturn = true;
    }
    /*Обработка кликов мыши*/
    @Override
    protected void processMouseEvent(MouseEvent mouseEvent){
        super.processMouseEvent(mouseEvent);
        if(mouseEvent.getButton() == MouseEvent.BUTTON1){//проверяем нажатие левой клавиши мыши
            int x = mouseEvent.getX();//Получаем координату клика x
            int y = mouseEvent.getY();//Получаем координату клика y
            //Переводим координаты в индексы ячейки в массиве field
            int i = (int)((float)x/getWidth()*3);
            int j = (int)((float) y / getHeight() *3);
            //Проверяем, что выбранная ячейка пустая и туда можно сходить
            if(field[i][j] == FIELD_EMPTY){
                //Проверка чей сейчас ход, если Х - ставим крестик, если О - ставим нолик
                field[i][j] = isXturn?FIELD_X:FIELD_O;
                Integer gg = field[i][j];
                try {
                    Parser.addHistory(gg,i,j);//Запись события в парсер
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TransformerException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                isXturn = !isXturn;//Меняем идентификатор хода
                repaint();//Перерисовка компонента вызываем paintComponent()
                int res = checkState();
                if(res!=0){
                    if(res == FIELD_O*3){
                        //Победа О
                        JOptionPane.showMessageDialog(this,"The noughts won!","WIN!",JOptionPane.INFORMATION_MESSAGE);
                        try {
                            Parser.addHistory(FIELD_GAME_OVER_WIN_O,-1,-1);//Запись события в парсер
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (TransformerException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        }
                        //Выйти из игры
                    }else if(res == FIELD_X*3){
                        //Победа Х
                        JOptionPane.showMessageDialog(this,"The crosses have won!","WIN!",JOptionPane.INFORMATION_MESSAGE);
                        try {
                            Parser.addHistory(FIELD_GAME_OVER_WIN_X,-1,-1);//Запись события в парсер
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (TransformerException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        }
                        //Выйти из игры
                    }else{
                        //Ничья
                        JOptionPane.showMessageDialog(this,"Draw!","DRAW!",JOptionPane.INFORMATION_MESSAGE);
                        try {
                            Parser.addHistory(FIELD_GAME_OVER_DRAW,-1,-1);//Запись события в парсер
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (TransformerException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        }
                        //Выйти из игры
                    }
                    //Перезапускаем игру

                    initGame();
                    //Перерисовываем поле
                    repaint();
                }

            }
        }
    }
    /*Метод отвечает за отрисовку компонентов*/
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(Color.RED);
        //Очищаем холст
        graphics.clearRect(0,0,getWidth(),getHeight());
        //рисуем сетку из линий
        drawGrid(graphics);
        //Рисуем крестики и нолики
        drawXO(graphics);

    }
    /*Метод разбивает окно на ячейки*/
    public void drawGrid(Graphics graphics){
        int w = getWidth(); //Ширина игрового поля
        int h = getHeight(); // Высота игрового поля
        int dw = w/3;// ширина одной ячейки
        int dh = h/3;// высота одной ячейки
        graphics.setColor(Color.BLACK); // цвет линий
        //цикл отрисовки линий
        for (int i = 1; i<3 ; i++){
            graphics.drawLine(0,dh*i,w,dh*i); // рисуем горизонтальную линию
            graphics.drawLine(dw*i,0,dw*i,h); // рисуем вертикальную линию
        }
    }
}

