package bingo.game;

//attack component
public class Attack{
    public int damage;
    public int armor;
    public double dmgBonus;
    public String name;
    public int cost;
    public String description;

    public Attack(String name, int damage, int armor, double dmgBonus, int cost){
        this.name = name;
        this.damage = damage;
        this.armor = armor;
        this.dmgBonus = dmgBonus;
        this.cost = cost;
    }
    public void setDescription(){
        if(hasDescription())
            description = "Gained +" + this.armor + ", +" + this.dmgBonus;
    }
    public boolean hasDescription(){
        return (armor == 0 || dmgBonus == 0);
    }
}