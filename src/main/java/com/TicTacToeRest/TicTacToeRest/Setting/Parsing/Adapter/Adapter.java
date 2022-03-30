package com.TicTacToeRest.TicTacToeRest.Setting.Parsing.Adapter;

public class Adapter {
    /* Метод интерпритирующий координаты */
    public static int adapter(int y, int x){
        int k = 0;
        for(int i = 0; i < y+1; i++){
            for(int j = 0;j < x+1;j++){
                k++;
            }
        }
        return k;
    }
    public static int adapterX(int k){
        int x = 0; return x;
    }
    public static int adapterY(int k){
        int y = 0; return y;
    }
}