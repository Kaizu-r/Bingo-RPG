package bingo.game;

import bingo.game.Item;
import bingo.game.Attack;

public class Weapon extends Item{
    public Attack atk1;
    public Attack atk2;

    public Weapon(String name, Attack atk1, Attack atk2){
        super(name);
        this.name = name;
        this.atk1 = atk1;
        this.atk2 = atk2;
    }

    
}