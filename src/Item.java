package bingo.game;

public class Item{
    public String name;
    public String[] sprite;
    public int atk;
    public int hp;
    public int armor;
    public int luck;

    public Item(String name, String[] sprite, int atk, int hp, int armor, int luck){
        this.name = name;
        this.sprite = sprite;
        this.atk = atk;
        this.hp = hp;
        this.armor = armor;
        this.luck = luck;
    }

}