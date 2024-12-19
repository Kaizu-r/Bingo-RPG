package bingo.game;

import java.util.ArrayList;
import java.io.IOException;
import bingo.game.Item;
import bingo.game.Weapon;
import bingo.game.Entity;

public class Player extends Entity{
    private ArrayList<Item> inventory;
    public int potionCount;
    private int maxPotion;
    private Item[] equipped;
    private ArrayList<Weapon> equipment;
    private Weapon weapon;
    public int maxHP;
    public int potionStrength;
    public int maxArmor;
    public double maxDmgBonus;
    public int luck;    //for luck stuff

    public Player(String name, double dmgBonus, int hp, int armor) throws IOException{
        super(name, dmgBonus, hp, armor, true);
        maxHP = hp;
        maxArmor = armor;
        maxDmgBonus = dmgBonus;
        inventory = new ArrayList<Item>();
        equipment = new ArrayList<Weapon>();
        equipped = new Item[3];
        maxPotion = 2;
        potionCount = 2;
        potionStrength = 20;
    }

    public void resetStats(){
        hp = maxHP;
        armor = maxArmor;
        damageBonus = maxDmgBonus;
        potionCount = maxPotion;
    }
    //permanent upgrades
    public void healthPlus(int h){
        maxHP += h;
        hp += h;
    }
    public void armorPlus(int a){
        maxArmor += a;
        armor += a;
    }
    public void dmgBonusPlus(double d){
        damageBonus += d;
        maxDmgBonus += d;
    }
    public void resetDmg(){
        damageBonus = maxDmgBonus;
    }
    public void resetArmor(){
        armor = maxArmor;
    }

    public void pickup(Item item){
        inventory.add(item);
    }

    public boolean canMove(int move){
        switch(move){
            case 1:
                return (match >= this.atk1.cost);
            case 2:
                return (match >= this.atk2.cost);
            case 3: //potion
                return(match >= 2);
            default: //dodge
                return(match >= 2);
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
                this.heal(potionStrength);
                potionCount--;
                return 1;
            default:
                dodge();
                return 1;

        }
    }
    public void potionStrengthPlus(){
        potionStrength += 4;
    }
    public void potionCountPlus(){
        maxPotion++;
        potionCount++;
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