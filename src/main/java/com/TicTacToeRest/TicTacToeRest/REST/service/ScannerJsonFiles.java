package com.TicTacToeRest.TicTacToeRest.REST.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class ScannerJsonFiles {
    // Отображение все файлов и папок основной директории
    public static ArrayList<String> displayAllJsonFilesDirectories() {
        File filess = new File("");
        URI ur = filess.toURI();
        String urlDirectory = String.valueOf(ur).substring(6);
        File dir = new File(urlDirectory);
        ArrayList<String> listFiles = new ArrayList<>();
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    //Ничего не делаем
                } else {
                    //Записываем в список наш json
                    String fileName = file.getCanonicalPath();
                    if(fileName.endsWith("json")) {
                        fileName = fileName.substring(fileName.indexOf("Game"));
                        listFiles.add(fileName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listFiles;
    }
}
