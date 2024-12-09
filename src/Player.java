package bingo.game;

import java.util.ArrayList;
import bingo.game.Item;
import bingo.game.Weapon;
import bingo.game.Entity;

public class Player extends Entity{
    private ArrayList<Item> inventory;
    private Item[] equipped;
    private ArrayList<Weapon> equipment;
    private Weapon weapon;
    public int luck;    //for luck stuff

    public Player(char type, String name, int dmgBonus, int hp, int armor){
        super(name, dmgBonus, hp, armor, true);
        inventory = new ArrayList<Item>();
        equipment = new ArrayList<Weapon>();
        equipped = new Item[3];
       
    }

    public void pickup(Item item){
        inventory.add(item);
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

    public void equipWeapon(int location){
        if(this.weapon == null){
            this.weapon = equipment.get(location);
            equipment.remove(location);
            return;
            
        }

        equipment.add(this.weapon);
        this.weapon = equipment.get(location);
    }


}