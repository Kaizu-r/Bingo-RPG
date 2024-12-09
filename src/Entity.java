package bingo.game;



public abstract class Entity{
    private Card card;
    public String[] sprite;
    public String name;
    public boolean alive;
    public int damageBonus;
    public int hp;
    public int armor;
    public boolean dodging;

    public Entity(String name, int damageBonus, int hp, int armor, boolean alive){
        this.name = name;
        this.damageBonus = damageBonus;
        this.hp = hp;
        this.armor = armor;
        this.alive = alive;
        card = new Card();
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
    public int attack(int dmg){
        return dmg + (dmg * damageBonus);
    }
    public void die(){
        alive = !(hp<= 0);
    }
    

}
