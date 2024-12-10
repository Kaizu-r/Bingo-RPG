package bingo.art;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Sprite{
    public String[] sprite;

    public Sprite(File file) throws IOException{
        Scanner scan = new Scanner(file);
        sprite = new String[16];

        for(int i = 0; i < 16; i++){
            sprite[i] = scan.nextLine();
        }
    }

}