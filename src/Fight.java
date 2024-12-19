package bingo.game;

import bingo.game.*;
import java.io.IOException;
import java.util.Random;

public class Fight{
    private Player player;
    private Enemy enemy;
    public boolean row[];
    public boolean column[];
    public boolean diagonal[];
    public boolean blackout[];

    public Fight(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
        column = new boolean[2];
        diagonal = new boolean[2];
        row = new boolean[2];
    }

    public void printHP(){
        System.out.print("Health: ");
        for(int i = 0; i < player.hp/10; i++){
            System.out.print("[+]");
        }
        for(int i = 0; i < (int) (30 - player.hp/3); i++)
            System.out.print(" ");
        System.out.print("                            Health: ");
        for(int i = 0; i < enemy.hp/10; i++){
            System.out.print("[+]");
        }
        System.out.println();
        System.out.print(String.format("        %3d/%3d", player.hp, player.maxHP));
        System.out.print("                                                ");
        System.out.println(String.format("        %3d/%3d", enemy.hp, enemy.maxHp));
    }
    public void printArmor(){
        System.out.print("Armor: ");
        for(int i = 0; i < player.armor/5; i++){
            System.out.print("[T]");
        }
        for(int i = 0; i < (int) (50 - player.armor/3); i++)
            System.out.print(" ");
        System.out.print("      Armor ");
        for(int i = 0; i < enemy.armor/5; i++){
            System.out.print("[T]");
        }
        System.out.println();
        System.out.print(String.format("        %3d   ", player.armor));
        System.out.print("                                          ");
        System.out.println(String.format("         %3d", enemy.armor));
    }

    public void printSprites(){
        for(int i = 0; i < 16; i++){
            System.out.print(player.sprite.sprite[i]);
            for(int j = 0; j < 60 - player.sprite.sprite[i].length(); j++){
                System.out.print(" ");
            }
            System.out.print(enemy.sprite.sprite[i]);
            System.out.println();
        }
    }

    public void printCards(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(String.format("|%2d", player.card.bingo[j][i]));
            }
            System.out.print("                                                  ");
            for(int j = 0; j < 5; j++){
                System.out.print(String.format("|%2d", enemy.card.bingo[j][i]));
            }
            System.out.println();
        }
    }

    public void buff(){
        if(row[0]){
            player.heal(5);
            System.out.print("Row formed!! Healed 5hp                                       ");
        }
        else
            System.out.print("                                                              ");
        if(row[1]){
            enemy.heal(5);
            System.out.print("Row formed!! Healed 5hp                                       ");
        }
        System.out.println();
        if(column[0]){
            player.armorBuff();
            System.out.print("Column formed!! Gained 5 armor                                ");
        }
        else
            System.out.print("                                                              ");
        if(column[1]){
            enemy.armorBuff();
            System.out.print("Column formed!! Gained 5 armor                                ");
        }
        System.out.println();
        if(diagonal[0]){
            player.damageBuff();
            System.out.print("Column formed!! Gained 30% damage bonus                       ");
        }
        else
            System.out.print("                                                              ");
        if(diagonal[1]){
            enemy.damageBuff(); 
            System.out.print("Column formed!! Gained 30% damage bonus                       ");
        }
        System.out.println();  
    }



    public int[] roll(){
        int[] arr = new int[5];
        Random ran = new Random();
        int locX;
        int locY = 0;
        for(int i = 0; i < 5; i++){
            arr[i] = Card.draw();
            locX = (arr[i] - 1)%5;

            for(int j = 0; j < 5; j++){
                if(player.card.bingo[locX][j] == arr[i]){
                    locY = j;
                    break;
                }
            }
            player.card.match();
            row[0] = player.card.checkColumn(locX);
            column[0] = player.card.checkRow(locY);
            diagonal[0] = player.card.checkDiagonal(locX, locY);
            for(int j = 0; j < 5; j++){
                if(enemy.card.bingo[locX][j] == arr[i]){
                    locY = j;
                    break;
                }
            }
            enemy.card.match();
            row[1] = enemy.card.checkColumn(locX);
            column[1] = enemy.card.checkRow(locY);
            diagonal[1] = enemy.card.checkDiagonal(locX, locY);

        }
        player.match = player.card.matches;
        enemy.match = enemy.card.matches;
        return arr;
    }

    public int onMove(int move){
        int p = player.move(move, (Entity) enemy);
        int a = enemy.move();
        int e = enemy.onMove(a, (Entity) player);

        if(p == 0)
            System.out.print("Fail                         ");
        else
            System.out.print("                             ");
        if(e == 0){
            System.out.print("Fail");
        }
        player.die();
        enemy.die();

        return a;
    }

    public boolean win(){
        return (!enemy.alive);
    }

    public void resetCard(){
        player.card.resetCard();
        enemy.card.resetCard();
        player.card.resetDraw();
        enemy.card.resetCard();
    }

    public void reset(){
        if(player.card.numsLeft() < 3 && enemy.card.numsLeft() < 3)
            resetCard();
    }
    public void printDesc(int pmove, int emove, boolean a, boolean b){
        if(a){
            switch(pmove){
                case 1:
                    if(player.atk1.dmgBonus != 0)
                        System.out.print("+" + player.atk1.dmgBonus + "damage% ");
                    else
                        System.out.print("               ");
                    if(player.atk1.armor != 0)
                        System.out.print("+" + player.atk1.armor + "armor ");
                    else
                        System.out.print("               ");
                    break;
                case 2:
                    if(player.atk2.dmgBonus != 0)
                        System.out.print("+" + player.atk2.dmgBonus + "damage% ");
                    else
                        System.out.print("               ");
                    if(player.atk2.armor != 0)
                        System.out.print("+" + player.atk2.armor + "armor ");
                    else
                        System.out.print("               ");
                    break;
            }
        }
        else
            System.out.print("               ");
        System.out.print("                                         ");
        if(b){
            switch(emove){
                case 0:
                    if(enemy.atk1.dmgBonus != 0)
                        System.out.print("+" + enemy.atk1.dmgBonus + "damage% ");
                    else
                        System.out.print("               ");
                    if(enemy.atk1.armor != 0)
                        System.out.print("+" + enemy.atk1.armor + "armor ");
                    else
                        System.out.print("               ");
                    break;
                case 1:
                    if(enemy.atk2.dmgBonus != 0)
                        System.out.print("+" + enemy.atk2.dmgBonus + "damage% ");
                    else
                        System.out.print("               ");
                    if(enemy.atk2.armor != 0)
                        System.out.print("+" + enemy.atk2.armor + "armor ");
                    else
                        System.out.print("               ");
                    break;
            }
        }
        System.out.println();
    }
    
    public void printMoves(){
        System.out.println("Attack          Damge   Cost                                Attack         Damage   Cost");
        System.out.print("[1]" + player.atk1.name);
        for(int i = 0; i < 15 - player.atk1.name.length(); i++){
            System.out.print(" ");
        }
        System.out.print(player.atk1.damage + "      " + player.atk1.cost);
        System.out.print("                                   ");
        System.out.print(enemy.atk1.name);
        for(int i = 0; i < 15 - enemy.atk1.name.length(); i++){
            System.out.print(" ");
        }
        System.out.println(enemy.atk1.damage + "      " + enemy.atk1.cost);
        System.out.print("[2]" + player.atk2.name);
        for(int i = 0; i < 15 - player.atk2.name.length(); i++){
            System.out.print(" ");
        }
        System.out.print(player.atk2.damage + "      " + player.atk2.cost);
        System.out.print("                                   ");
        System.out.print(enemy.atk2.name);
        for(int i = 0; i < 15 - enemy.atk2.name.length(); i++){
            System.out.print(" ");
        }
        System.out.println(enemy.atk2.damage + "      " + enemy.atk2.cost);
        System.out.print("[3] Potion " + player.potionCount + "              2");
        System.out.print("                                   ");
        System.out.println("Dodge                  2");
        System.out.println("[4] Dodge                 2");
    }
    
    public void printMatches(){
        System.out.println("Matches: " + player.card.matches + "                                                      " + enemy.card.matches);

    }

    public String emTried(int move){
        switch(move){
            case 0:
                return enemy.atk1.name;
            case 1:
                return enemy.atk2.name;
            case 2:
                return "Dodge";
            default:
                return "null";
        }
    }

    public String pTried(int move){
        switch(move){
            case 1:
                return player.atk1.name;
            case 2:
                return player.atk2.name;
            case 3:
                return "Potion";
            case 4:
                return "Dodge";
            default:
                return "null";
        }
    }

    public void printTried(int p, int e){
        System.out.println("Tried to use: ");
        wait(1500);
        System.out.print(pTried(p));
        wait(1500);
        System.out.print("                                                  ");
        System.out.println(emTried(e));
        wait(1500);
        if(player.canMove(p))
            System.out.print("    Success");
        else
            System.out.print("    Fail");
        System.out.print("                                                  ");
        if(enemy.canMove(e))
            System.out.print("    Success");
        else
            System.out.print("    Fail");
    }

    public static Enemy buildEnemy(int ran) throws IOException{
        Enemy em;
        Attack atk1;
        Attack atk2;
        switch(ran){
            case 0: //archer
                atk1 = new Attack("Barrage", 5, 0, 0,1);
                atk2 = new Attack("Mighty Shot", 15, 0, 0.1, 3);
                atk1.setDescription();
                atk2.setDescription();
                em = new Enemy("archer", 0, 30, 0, true, atk1, atk2);
                break;
            case 1: //knight
                atk1 = new Attack("Cleave", 10, 0, 0, 3);
                atk2 = new Attack("Carian Slice", 30, 10, 0, 4);
                atk1.setDescription();
                atk2.setDescription();
                em = new Enemy("knight", 0, 40, 5, true, atk1, atk2);
                break;
            default:
                return null;
        }
        return em;
    }

    public static Enemy buildElite(int count) throws IOException{
        Enemy em;
        Attack atk1;
        Attack atk2;
        switch(count){
            case 0: //warrior
                atk1 = new Attack("Pound", 20, 0, 0,2);
                atk2 = new Attack("Berserk", 10, 5, 0.2, 3);
                atk1.setDescription();
                atk2.setDescription();
                em = new Enemy("warrior", 0, 60, 0, true, atk1, atk2);
                break;
            case 1: //cavalry
                atk1 = new Attack("Lunge", 15, 0, 0, 2);
                atk2 = new Attack("Sweep", 30, 0, 0, 3);
                atk1.setDescription();
                atk2.setDescription();
                em = new Enemy("cavalry", 0, 50, 5, true, atk1, atk2);
                break;
            case 2://wyrm
                atk1 = new Attack("Harden", 0, 10, 0.2, 2);
                atk2 = new Attack("Tackle", 30, 0, 0, 3);
                atk1.setDescription();
                atk2.setDescription();
                em = new Enemy("wyrm", 0, 70, 10, true, atk1, atk2);
                break;
            case 3://sentinel
                atk1 = new Attack("Gurdian Beam", 40, 0, 0, 3);
                atk2 = new Attack("Enhancement", 0, 0, 0.3, 3);
                atk1.setDescription();
                atk2.setDescription();
                em = new Enemy("sentinel", 0, 60, 10, true, atk1, atk2);
                break;
            default:
                return null;
        }
        return em;
    }

    public static Enemy buildBoss() throws IOException{
        Attack atk1 = new Attack("Ruinous Flame", 50, 0, 0, 4);
        Attack atk2 = new Attack("Burning Surge", 20, 0, 0, 2);
        Enemy boss = new Enemy("dragon", 0, 100, 10, true, atk1, atk2);
        return boss;
    }

    public static void wait(int t){
        try{
            Thread.sleep(t);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }


    
}

