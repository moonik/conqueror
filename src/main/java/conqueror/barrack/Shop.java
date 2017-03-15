package conqueror.barrack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Shop {

    @Id
    @GeneratedValue
    private Long id;

    private String warrior;
    private int attack;
    private int hp;
    private int cost;

    public Shop(){}

    public Shop(String warrior, int attack, int hp, int cost) {
        this.warrior = warrior;
        this.attack = attack;
        this.hp = hp;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarrior() {
        return warrior;
    }

    public void setWarrior(String warrior) {
        this.warrior = warrior;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
