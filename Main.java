import bingo.art.*;
import bingo.game.*;

import java.util.Scanner;
public class Main{
    public static void main(String args[]){
        Game game = new Game("New");

        int a, b;
        Scanner scan = new Scanner(System.in);
        do{
            do{
                game.printGame();
                System.out.print("                       :");
                a = scan.nextInt();
                b = scan.nextInt();
                Main.clear();
            }while(a < 0 || b < 0 || a > 5 || b > 5 || game.bingo[a][b] != 0);
            int ran = game.roll(a, b);
        }while(game.totalRolls != 36);
    }

    public static void clear(){
        System.out.print("\033[1;1H\033[2J\033[3J");
    }
}