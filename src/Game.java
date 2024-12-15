package bingo.game;

import java.util.Random;
import bingo.art.Blocks;

//working board
public class Game{
    public String name;
    public int[][] bingo;
    int rolls;
    public int totalRolls;
    public int grassCount = 18;
    public int eliteCount = 4;
    public int enemyCount = 7; 
    public int lootCount = 3;   
    public int shrineCount = 0;

    public Game(String name){
        bingo = new int[6][6];
        shrineCount = 0;    //set this to 3 after a few rolls so it doesnt spawn immediately
        enemyCount = 0;  //set to 7 after a few rolls
        this.name = name;
    } 
    //0 for locked, 1 grass, 2 enemy, 3 loot, 4 shrine, 5 elite, 6 boss


    private int blockMake(int a, int b){
        int ran;
        Random random = new Random();
        int arr[] = {grassCount, enemyCount, lootCount};   //contains all enemies here

        do{
            ran = random.nextInt(9);
            //playing with probabilities
            if(ran < 5)
                ran = 0;    //60% chance of grass
            else if(ran < 8)
                ran = 1;    //30% chance of enemy
            else if(ran < 9)
                ran = 2; //10% chance of loot
        }while(arr[ran] == 0);  //roll again if its out of the thing

        bingo[a][b] = ran + 1;
        arr[ran]--; //decrease count

        grassCount = arr[0];
        enemyCount = arr[1];
        lootCount = arr[2];
        return ran;
    }

    //returns whatever tile it is getting
    public int roll(int a, int b){
        if(totalRolls == 35){
            totalRolls++;
            bingo[a][b] = 6;
            return 6;   //boss fight
        }
        if(totalRolls == 3){
            enemyCount = 7; //start spawning enemies
        }
        if(totalRolls == 6){
            shrineCount = 3;    //start spawning shrines here
        }
        if((totalRolls + 1)%6 == 0 && totalRolls >= 11){  //elite only after 11 rolls cuz we not evil
            rolls = 0;
            totalRolls++;
            bingo[a][b] = 5;   //elite
            eliteCount--;
            return 5;
        }
        if(rolls == 0 && totalRolls > 11){   //guaranteed shrine after elite
            shrineCount--;
            rolls++;
            totalRolls++;
            bingo[a][b] = 4;
            return 4;
        }
        rolls++;
        totalRolls++;
        int ran = this.blockMake(a, b);

        return ran + 1;
    }

    public void printGame(){
        System.out.print("                                     ");
        System.out.println("         0        1         2         3         4        5");
        System.out.println();
        for(int i = 0; i < 6; i++){
            for(int k = 1; k < 5; k++){
                System.out.print("                                     ");
                if(k == 3)
                    System.out.print(i + "   ");
                else
                    System.out.print("    ");
                for(int j = 0; j < 6; j++){
                    switch(bingo[i][j]){
                        case 0:
                            Blocks.locked(k);
                            break;
                        case 1:
                            Blocks.grass(k);
                            break;
                        case 2:
                            Blocks.enemy(k);
                            break;
                        case 3:
                            Blocks.loot(k);
                            break;
                        case 4:
                            Blocks.shrine(k);
                            break;
                        case 5:
                            Blocks.elite(k);
                            break;
                        case 6:
                            Blocks.boss(k);
                            break;
                    }
                }
                System.out.println();
            }
        }
    }
    public boolean checkRow(int a){
        for(int j = 0; j<6; j++){
            if(bingo[a][j] == 0)
                return false;
        }
        return true;
    }
    public boolean checkColumn(int a){
        for(int j = 0; j < 6; j++){
            if(bingo[j][a] == 0)
                return false;
        }
        return true;
    }
    public boolean checkDiagonal(int a, int b){
        if(a != b)
            return false;
        for(int i = 0; i < 6; i++){
            if(bingo[i][i] == 0)
                return false;
        }
        return true;
    }

    


}