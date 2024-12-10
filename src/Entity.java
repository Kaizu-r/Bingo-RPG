package bingo.game;

import java.io.File;
import java.io.IOException;
import bingo.art.Sprite;
import bingo.game.Path;
import bingo.game.Attack;

public abstract class Entity{
    public Card card;
    public String name;
    public Sprite sprite;
    public boolean alive;
    public int damageBonus;
    public int hp;
    public int maxHp;
    public int armor;
    public Attack atk1;
    public Attack atk2;
    private File file;
    private Path path;
    public boolean dodging;
    public int match;  //number of nums that matched in the board thing

    public Entity(String name, int damageBonus, int hp, int armor, boolean alive) throws IOException{
        this.name = name;
        this.damageBonus = damageBonus;
        this.maxHp = hp;
        this.hp = hp;
        this.armor = armor;
        this.alive = alive;
        card = new Card();
        path = new Path(this.name);

        file = new File(path.path);

        sprite = new Sprite(file);

    }

    public void takeDamage(int damage){
        this.hp -= damage - (armor >> 1);
    }

    public void dodge(){
        dodging = true;
    }
    public void resetDodge(){
        dodging = false;
    }
    public boolean canMove(int move){
        switch(move){
            case 0:
                return (match <= atk1.cost);
            case 1:
                return (match <= atk2.cost);
            default: //dodge
                return(match <= 2);
        }
    }
    //0 fail, 1 success
    public int onMove(int move, Entity e){
        if(!this.canMove(move))
            return 0;
        switch(move){
            case 0:
                if(e.dodging){
                    e.resetDodge();
                    return 0;
                }
                    
                e.takeDamage(this.attack(atk1));
                return 1;
            case 1:
                if(e.dodging){
                    e.resetDodge();
                    return 0;
                }
                e.takeDamage(this.attack(atk2));
                return 1;
            default:
                dodge();
                return 1;

        }
    }

    public int attack(Attack atk){
        return atk.damage + (damageBonus * atk.damage);
    }
    public void heal(int amount){
        this.hp += amount;
    }
    public void die(){
        alive = !(hp<= 0);
    }
    

}
