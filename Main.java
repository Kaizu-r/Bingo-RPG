import bingo.art.*;
import bingo.game.*;

import java.util.Scanner;
public class Main{
    public static void main(String args[]){
        Game game = new Game("New");

        int a, b;
        Scanner scan = new Scanner(System.in);
        do{
            game.printGame();
            System.out.println("                       :");
            a = scan.nextInt();
            b = scan.nextInt();
            int r = game.roll(a, b);
            Main.clear();

        }while(game.totalRolls != 25);
    }

    public static void clear(){
        System.out.print("\033[1;1H\033[2J\033[3J");
    }
}