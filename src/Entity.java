package bingo.game;

public abstract class Entity{
    private Card card;
    public String[] sprite;
    public String name;
    public boolean alive;
    public int damageBonus;
    public int hp;
    public int armor;

    public void takeDamage(int damage){
        this.hp -= damage - (armor >> 1);
    }

    public int attack(int dmg){
        return dmg + (dmg * damageBonus);
    }
    

}
