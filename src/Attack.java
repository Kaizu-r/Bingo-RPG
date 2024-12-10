package bingo.game;

//attack component
public class Attack{
    public int damage;
    public String name;
    public int cost;

    public Attack(String name, int damage, int cost){
        this.name = name;
        this.damage = damage;
        this.cost = cost;
    }
}