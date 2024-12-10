package bingo.game;

import bingo.game.*;
import java.util.Random;

public class Fight{
    private int arr[];
    private Player player;
    private Enemy enemy;

    public Fight(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
    }

    public void printHP(){
        System.out.print("Health: ");
        for(int i = 0; i < player.hp/10; i++){
            System.out.print("[H]");
        }
        System.out.print("                                            Health: ");
        for(int i = 0; i < enemy.hp/10; i++){
            System.out.print("[H]");
        }
        System.out.println();
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
                System.out.print("|" + player.card.bingo[i][j]);
            }
            System.out.print("                   ");
            for(int j = 0; j < 5; j++){
                System.out.print("|" + enemy.card.bingo[i][j]);
            }
            System.out.println();
        }
    }

    public void roll(){
        arr = new int[5];
        Random ran = new Random();

        for(int i = 0; i < 5; i++){
            arr[i] = Card.draw();
            player.card.match();
            enemy.card.match();
        }
        player.match = player.card.matches;
        enemy.match = enemy.card.matches;
    }

    public void onMove(int move){
        int p = player.move(move, (Entity) enemy);
        int e = enemy.onMove(enemy.move(), (Entity) player);

        if(p == 0)
            System.out.print("Fail                         ");
        else
            System.out.print("                             ");
        if(e == 0){
            System.out.print("Fail");
        }
        player.die();
        enemy.die();

        player.card.matches = 0;
        enemy.card.matches = 0;

    }

    public boolean win(){
        return (!enemy.alive);
    }
    


}