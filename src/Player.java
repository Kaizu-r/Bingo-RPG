package bingo.game;

import java.util.ArrayList;
import java.io.IOException;
import bingo.game.Item;
import bingo.game.Weapon;
import bingo.game.Entity;

public class Player extends Entity{
    private ArrayList<Item> inventory;
    private int potionCount;
    private Item[] equipped;
    private ArrayList<Weapon> equipment;
    private Weapon weapon;
    public int luck;    //for luck stuff

    public Player(String name, int dmgBonus, int hp, int armor) throws IOException{
        super(name, dmgBonus, hp, armor, true);
        inventory = new ArrayList<Item>();
        equipment = new ArrayList<Weapon>();
        equipped = new Item[3];
        potionCount = 2;
       
    }

    public void pickup(Item item){
        inventory.add(item);
    }

    public boolean canMove(int move){
        switch(move){
            case 1:
                return (match <= this.atk1.cost);
            case 2:
                return (match <= this.atk2.cost);
            case 3: //potion
                return(match <= 2);
            default: //dodge
                return(match <= 2);
        }
    }

    public int move(int move, Entity e){
        if(!canMove(move))
            return 0;
        switch(move){
            case 1:
                if(e.dodging){
                    e.resetDodge();
                    return 0;
                }
                e.takeDamage(this.attack(atk1));
                return 1;
            case 2:
                if(e.dodging){
                    e.resetDodge();
                    return 0;
                }
                e.takeDamage(this.attack(atk2));
                return 1;
            case 3:
                if(potionCount == 0){
                    return 0;
                }
                this.heal(10);
                potionCount--;
                return 1;
            default:
                dodge();
                return 1;

        }
    }

    public boolean canEquip(){
        for(int i = 0; i < 3; i++){
            if(equipped[i] == null);
                return true;
        }
        return false;
    }
    

    public void equipItem(int location, int index){
        if(this.canEquip()){
            Item item = inventory.get(location);
            equipped[index] = item;
            inventory.remove(location);

            this.hp += item.hp;
            this.armor += item.armor;
            this.damageBonus += item.damageBonus;
            this.luck += item.luck;

        }
    }
    public void unequipItem(int index){
        inventory.add(equipped[index]); //copy to inventory
        this.hp -= equipped[index].hp;
        this.armor -= equipped[index].armor;
        this.damageBonus -= equipped[index].damageBonus;
        this.luck -= equipped[index].luck;

        equipped[index] = null;     //remove item from equipment
    }
    public void pickWeapon(Weapon weapon){
        equipment.add(weapon);
    }

    public void equipWeapon(int location){
        if(this.weapon == null){
            this.weapon = equipment.get(location);
            atk1 = this.weapon.atk1;
            atk2 = this.weapon.atk2;
            equipment.remove(location);
            return;
            
        }

        equipment.add(this.weapon);
        this.weapon = equipment.get(location);
    }

    public void unequipWeaon(){
        equipment.add(this.weapon);
        this.atk1 = null;
        this.atk2 = null;
        this.weapon = null;
    }


}