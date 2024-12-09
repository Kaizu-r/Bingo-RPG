package bingo.game;

public class Item{
    public String name;
    public String[] sprite;
    public int atk;
    public int hp;
    public int armor;
    public int luck;
    public int damageBonus;


    public Item(String name){
        this.name = name;
    }
    public Item(String name, String[] sprite, int atk, int hp, int armor, int luck, int damageBonus){
        this.name = name;
        this.sprite = sprite;
        this.atk = atk;
        this.hp = hp;
        this.armor = armor;
        this.luck = luck;
        this.damageBonus = damageBonus;
    }

}