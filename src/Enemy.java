package bingo.game;

import bingo.game.Entity;
import bingo.game.Attack;
import java.util.Random;
import java.io.IOException;

public class Enemy extends Entity{
    public Enemy(String name, int damageBonus, int hp, int armor, boolean alive, Attack atk1, Attack atk2) throws IOException{
        super(name, damageBonus, hp, armor, alive);
        this.atk1 = atk1;
        this.atk2 = atk2;

    }

    public int move(){
        Random ran = new Random();

        int r = ran.nextInt(3);
        return r;
    }
}