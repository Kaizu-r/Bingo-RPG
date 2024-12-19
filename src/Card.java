package bingo.game;

import java.util.Random;


public class Card{
    public int bingo[][] = new int[5][5]; //working card
    public int bingoOrig[][] = new int[5][5]; //original card, used for resetting
    private int bingoCheck[][] = new int[5][5];
    public static int randomNumber;
    public boolean winner = false;
    public static int selected[] = new int[75]; //1 to 75
    public static int allNums[] = new int[75]; //all numbers in the card
    public int matches;

    public Card(){
        //genarate numbers

        Random ran = new Random();
        
        for(int i = 0, k = 0; i < 5; i++, k+= 15){
            int arr[] = new int[15];
            for(int j = 0; j < 5; j++){
                int a;
                do{
                    a = ran.nextInt(15);
                }while(arr[a] != 0 || allNums[a + k] != 0);
                arr[a] = 1;
                bingo[i][j] = a + 1 + k;
                bingoOrig[i][j] = a + 1 + k;
                allNums[a + k]++;
            }
        }
        allNums[bingo[2][2] - 1]--; //remove from list the middle number
        bingo[2][2] = 0;    //remove middle for FREE
    }

    public void resetCard(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                bingo[i][j] = bingoOrig[i][j];
            }
        }
        bingo[2][2] = 0; //free
    }

    public void resetMatch(){
        this.matches = 0;   //after a move is finished
    }
    public static int draw(){
        Random ran = new Random();

        do{
            randomNumber = ran.nextInt(75);
        }while(selected[randomNumber] != 0 || allNums[randomNumber] == 0);
        selected[randomNumber] = 1;

        return randomNumber + 1;
    }

    public void delete(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                allNums[bingoOrig[i][j] -1] = 0;
            }
        }
    }

    public void match(){
        //linearly search if it matches
        for(int i= 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(bingo[i][j] == randomNumber + 1){
                    bingoCheck[i][j] = 1;
                    bingo[i][j] = 0;
                    matches++;
            }
        }
        }
        
    }

    public boolean checkRow(int i){
        for(int j = 0; j < 5; j++){
            if(bingo[i][j] != 0)
                return false;
        }
        return true;
    }   

    public boolean checkColumn(int j){
        for(int i = 0; i < 5; i++){
            if(bingo[i][j] != 0)
                return false;
        }
        return true;
    }

    public boolean checkDiagonal(int loc, int loc1){
        if(loc != loc1 || loc1 + loc != 4 )
            return false;
        for(int i = 0; i < 5; i++){
            if(bingo[i][i] != 0)    //main diagonal
                return false;
            if(bingo[i][4 - i] != 0)    //other diagonal
                return false;
        }
        return true;
    }

    public boolean isWinner(){
        return winner;
    }

    public int numsLeft(){
        int n = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(bingo[i][j] != 0){
                    n++;
                }
            }
        }
        return n;
    }

    public void printCard(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(bingo[j][i] + " ");
            }
            System.out.println();
        }
    }

    public void resetDraw(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j<5; j++){
                bingoCheck[i][j] = 0;
            }
        }
        for(int i = 0; i < 75; i++)
            selected[i] = 0;
    }
}