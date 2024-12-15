import bingo.art.*;
import bingo.game.*;
import java.io.IOException;
import java.util.Random;

import java.util.Scanner;
public class Main{
    public static void main(String args[]) throws IOException{
        Player player = new Player("player", 0, 40, 5);
        Attack atk1 = new Attack("Zoltrak", 10, 0, 0.1, 3);
        Attack atk2 = new Attack("ligma", 30, 0, 0, 1);
        atk1.setDescription();
        atk2.setDescription();
        Weapon weapon = new Weapon("Journey's end", atk1, atk2);
        player.pickWeapon(weapon);
        player.equipWeapon(0);
 
        int elite = 0;
        Game game = new Game("New");

        int a, b;
        Scanner scan = new Scanner(System.in);
        mainMenu();
        Main.clear();
        do{
            do{
                game.printGame();
                System.out.print("                                                                       :");
                a = scan.nextInt();
                b = scan.nextInt();
            }while(a < 0 || b < 0 || a > 5 || b > 5 || game.bingo[a][b] != 0);
            System.out.println("x");
            int ran = game.roll(a, b);
            if(game.checkRow(a)){
                System.out.println("                                       Row formed!! Gained + 10 hp");
                player.healthPlus(10);
                Fight.wait(2000);
                
            }
            if(game.checkColumn(b)){
                System.out.println("                                       Column formed!! Gained +5 armor");
                player.armorPlus(5);
                 Fight.wait(2000);
            }
            if(game.checkDiagonal(a, b)){
                System.out.println("                                       Diagonal formed!! Gained +10% damage");
                player.dmgBonusPlus(0.1);
                 Fight.wait(2000);
            }
            if(ran == 2){
                Fight.wait(2000);
                Main.clear();
                startFight(player);
                if(player.alive){
                    Main.clear();
                    enemySlain();
                    Fight.wait(4000);
                    Main.clear();
                }

            }
            if(ran == 3){
                System.out.println("                                       Chest Found!!! Stats are increased!!");
                player.armorPlus(10);
                player.dmgBonusPlus(0.1);
                Fight.wait(2000);
            }
            if(ran == 4)
                player.hp = player.maxHP;
            if(ran == 5){
                Fight.wait(2000);
                Main.clear();
                startEliteFight(player, elite);
                if(player.alive){
                    Main.clear();
                    player.potionCountPlus();
                    player.potionStrengthPlus();
                    enemySlain();
                    System.out.println();
                    System.out.println("                      Potions improved!!!");
                    Fight.wait(4000);
                    Main.clear();
                }
                elite++;
            }
            if(ran == 6){
                Fight.wait(2000);
                Main.clear();
                startBoss(player);
                if(player.alive){
                    enemySlain();
                    Fight.wait(4000);
                    Main.clear();
                }
            }
            Main.clear();
        }while(game.totalRolls != 36 && player.alive);

        if(!player.alive){
            died();
        }


        scan.close();
        
    }

    public static void clear(){
        System.out.print("\033[1;1H\033[2J\033[3J");
    }

   static void startFight(Player player) throws IOException{
        int arr[];
        Random ran = new Random();
        Enemy skelly = Fight.buildEnemy(ran.nextInt(2));

        player.card.resetCard();
        player.card.resetDraw();
        Fight fight = new Fight(player, skelly);
        Scanner scan = new Scanner(System.in);


        fight.printSprites();
        fight.printHP();
        fight.printArmor();
        System.out.println();
        fight.printCards();
        player.card.matches = 0;
        skelly.card.matches = 0;
        fight.printMoves();
        System.out.print("Enter move here: ");
        int a = scan.nextInt();

        arr = fight.roll();
        boolean b = player.canMove(a);
        if(a == 3 && b)
            player.heal(player.potionStrength);
        if(a == 4 && b)
            player.dodge();
        int e = skelly.move();
        boolean f = skelly.canMove(e);
        if(e == 2 && f)
            skelly.dodge();
        int p = player.move(a, skelly);
        int q = skelly.onMove(e, player);
        player.resetDodge();
        skelly.resetDodge();

        skelly.die();
        player.die();

        Main.clear();
        fight.reset();
        do{
            fight.printTried(a, e);
            System.out.println();
            System.out.print("                      ");
            for(int i = 0; i < 5; i++)
                System.out.print(arr[i] + " ");
            System.out.println();
            fight.printSprites();
            fight.printDesc(a, e, b, f);
            fight.printHP();
            fight.printArmor();
            System.out.println();
            fight.printCards();
            System.out.println();
            fight.printMatches();
            player.card.matches = 0;
            skelly.card.matches = 0;
            if(!skelly.alive || !player.alive)
                break;
            fight.printMoves();
            System.out.print("Enter move here: ");
            a = scan.nextInt();

            arr = fight.roll();
            b = player.canMove(a);
            if(a == 3 && b)
                player.heal(player.potionStrength);
            if(a == 4 && b)
                player.dodge();
            e = skelly.move();
            f = skelly.canMove(e);
            if(e == 2 && f)
                skelly.dodge();
            p = player.move(a, skelly);
            q = skelly.onMove(e, player);

            player.resetDodge();
            skelly.resetDodge();

            skelly.die();
            player.die();
            Main.clear();
            fight.reset();

        }while(true);
        skelly.card.delete();
        Fight.wait(2000);

    }

    static void startEliteFight(Player player, int elite) throws IOException{
        int arr[];
        Enemy skelly = Fight.buildElite(elite);
        player.card.resetCard();
        player.card.resetDraw();
        Fight fight = new Fight(player, skelly);
        Scanner scan = new Scanner(System.in);


        fight.printSprites();
        fight.printHP();
        fight.printArmor();
        System.out.println();
        fight.printCards();
        player.card.matches = 0;
        skelly.card.matches = 0;
        fight.printMoves();
        System.out.print("Enter move here: ");
        int a = scan.nextInt();

        arr = fight.roll();
        boolean b = player.canMove(a);
        if(a == 3 && b)
            player.heal(player.potionStrength);
        if(a == 4 && b)
            player.dodge();
        int e = skelly.move();
        boolean f = skelly.canMove(e);
        if(e == 2 && f)
            skelly.dodge();
        int p = player.move(a, skelly);
        int q = skelly.onMove(e, player);
        player.resetDodge();
        skelly.resetDodge();

        skelly.die();
        player.die();

        Main.clear();
        fight.reset();
        do{
            fight.printTried(a, e);
            System.out.println();
            System.out.print("                      ");
            for(int i = 0; i < 5; i++)
                System.out.print(arr[i] + " ");
            System.out.println();
            fight.printSprites();
            fight.printDesc(a, e, b, f);
            fight.printHP();
            fight.printArmor();
            System.out.println();
            fight.printCards();
            System.out.println();
            fight.printMatches();
            player.card.matches = 0;
            skelly.card.matches = 0;
            if(!skelly.alive || !player.alive)
                break;
            fight.printMoves();
            System.out.print("Enter move here: ");
            a = scan.nextInt();

            arr = fight.roll();
            b = player.canMove(a);
            if(a == 3 && b)
                player.heal(player.potionStrength);
            if(a == 4 && b)
                player.dodge();
            e = skelly.move();
            f = skelly.canMove(e);
            if(e == 2 && f)
                skelly.dodge();
            p = player.move(a, skelly);
            q = skelly.onMove(e, player);

            player.resetDodge();
            skelly.resetDodge();

            skelly.die();
            player.die();
            Main.clear();
            fight.reset();

        }while(true);
        skelly.card.delete();
        Fight.wait(2000);

    }

   static  void startBoss(Player player) throws IOException{
        int arr[];
        Enemy skelly = Fight.buildBoss();
        player.card.resetCard();
        player.card.resetDraw();
        Fight fight = new Fight(player, skelly);
        Scanner scan = new Scanner(System.in);


        fight.printSprites();
        fight.printHP();
        fight.printArmor();
        System.out.println();
        fight.printCards();
        player.card.matches = 0;
        skelly.card.matches = 0;
        fight.printMoves();
        System.out.print("Enter move here: ");
        int a = scan.nextInt();

        arr = fight.roll();
        boolean b = player.canMove(a);
        if(a == 3 && b)
            player.heal(player.potionStrength);
        if(a == 4 && b)
            player.dodge();
        int e = skelly.move();
        boolean f = skelly.canMove(e);
        if(e == 2 && f)
            skelly.dodge();
        int p = player.move(a, skelly);
        int q = skelly.onMove(e, player);
        player.resetDodge();
        skelly.resetDodge();

        skelly.die();
        player.die();

        Main.clear();
        fight.reset();
        do{
            fight.printTried(a, e);
            System.out.println();
            System.out.print("                      ");
            for(int i = 0; i < 5; i++)
                System.out.print(arr[i] + " ");
            System.out.println();
            fight.printSprites();
            fight.printDesc(a, e, b, f);
            fight.printHP();
            fight.printArmor();
            System.out.println();
            fight.printCards();
            System.out.println();
            fight.printMatches();
            player.card.matches = 0;
            skelly.card.matches = 0;
            if(!skelly.alive || !player.alive)
                break;
            fight.printMoves();
            System.out.print("Enter move here: ");
            a = scan.nextInt();

            arr = fight.roll();
            b = player.canMove(a);
            if(a == 3 && b)
                player.heal(player.potionStrength);
            if(a == 4 && b)
                player.dodge();
            e = skelly.move();
            f = skelly.canMove(e);
            if(e == 2 && f)
                skelly.dodge();
            p = player.move(a, skelly);
            q = skelly.onMove(e, player);

            player.resetDodge();
            skelly.resetDodge();

            skelly.die();
            player.die();
            Main.clear();
            fight.reset();

        }while(true);
        if(!skelly.alive)
            skelly.card.delete();
        Fight.wait(2000);
    }

    static void mainMenu(){
        System.out.println("          ______  _________ _        _______  _______    _______  _______  _______");
        System.out.println("         (  ___ \\ \\__   __/( (    /|(  ____ \\(  ___  )  (  ____ )(  ____ )(  ____ \\");
        System.out.println("         | (   ) )   ) (   |  \\  ( || (    \\/| (   ) |  | (    )|| (    )|| (    \\/");
        System.out.println("         | (__/ /    | |   |   \\ | || |      | |   | |  | (____)|| (____)|| |");
        System.out.println("         |  __ (     | |   | (\\ \\) || | ____ | |   | |  |     __)|  _____)| | ____");
        System.out.println("         | (  \\ \\    | |   | | \\   || | \\_  )| |   | |  | (\\ (   | (      | | \\_  )");
        System.out.println("         | )___) )___) (___| )  \\  || (___) || (___) |  | ) \\ \\__| )      | (___) |");
        System.out.println("         |/ \\___/ \\_______/|/    )_)(_______)(_______)  |/   \\__/|/       (_______)");

        System.out.println();
        System.out.println("              Press Any button and enter to start");
        System.out.println("                          :");
        Scanner scan = new Scanner(System.in);
        char a = scan.next().charAt(0);
    }

    static void died(){
        System.out.println("          _______             ______  _________ _______  ______ \n|\\     /|(  ___  )|\\     /|  (  __  \\ \\__   __/(  ____ \\(  __  \\\n( \\   / )| (   ) || )   ( |  | (  \\  )   ) (   | (    \\/| (  \\  )\n \\ (_) / | |   | || |   | |  | |   ) |   | |   | (__    | |   ) |\n  \\   /  | |   | || |   | |  | |   | |   | |   |  __)   | |   | |\n   ) (   | |   | || |   | |  | |   ) |   | |   | (      | |   ) |\n   | |   | (___) || (___) |  | (__/  )___) (___| (____/\\| (__/  )\n   \\_/   (_______)(_______)  (______/ \\_______/(_______/(______/ \n");
        
    }

    static void enemySlain(){
        System.out.println(" _______  _        _______  _______             _______  _______  _        _        _______  ______ \n(  ____ \\( (    /|(  ____ \\(       )|\\     /|  (  ____ \\(  ____ \\( \\      ( \\      (  ____ \\(  __  \\ \n| (    \\/|  \\  ( || (    \\/| () () |( \\   / )  | (    \\/| (    \\/| (      | (      | (    \\/| (  \\  )\n| (__    |   \\ | || (__    | || || | \\ (_) /   | (__    | (__    | |      | |      | (__    | |   ) |\n|  __)   | (\\ \\) ||  __)   | |(_)| |  \\   /    |  __)   |  __)   | |      | |      |  __)   | |   | |\n| (      | | \\   || (      | |   | |   ) (     | (      | (      | |      | |      | (      | |   ) |\n| (____/\\| )  \\  || (____/\\| )   ( |   | |     | )      | (____/\\| (____/\\| (____/\\| (____/\\| (__/  )\n(_______/|/    )_)(_______/|/     \\|   \\_/     |/       (_______/(_______/(_______/(_______/(______/ \n");
    }


}