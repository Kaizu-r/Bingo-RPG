import bingo.art.*;
import bingo.game.*;
import java.io.IOException;

import java.util.Scanner;
public class Main{
    public static void main(String args[]) throws IOException{
        Player player = new Player("knight", 0, 100, 10);
        Attack atk1 = new Attack("Zoltrak", 20, 3);
        Attack atk2 = new Attack("ligma", 50, 1);
        Weapon weapon = new Weapon("Journey's end", atk1, atk2);
        player.pickWeapon(weapon);
        player.equipWeapon(0);

        Attack satk1 = new Attack("shoot", 20, 3);
        Attack satk2 = new Attack("Mighty shot", 20, 3);
        Enemy skelly = new Enemy("dragon", 0, 50, 10, true, satk1, satk2);
 

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
            if(ran == 2){
                startFight(player, skelly);
            }
        }while(game.totalRolls != 36 || player.alive);
    }

    public static void clear(){
        System.out.print("\033[1;1H\033[2J\033[3J");
    }

    public static void startFight(Player player, Enemy skelly){
        Fight fight = new Fight(player, skelly);
        Scanner scan = new Scanner(System.in);
        do{
            fight.printSprites();
            fight.printHP();
            fight.printCards();
            System.out.print("Enter move here: ");
            int a = scan.nextInt();

            fight.roll();
            fight.onMove(a);
            Main.clear();


        }while(skelly.alive || player.alive);
    }
}