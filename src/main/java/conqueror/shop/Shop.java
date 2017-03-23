package conqueror.shop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Shop {

    @Id
    @GeneratedValue
    private Long id;

    private String warrior; // warrior name
    private int attack;
    private int hp;
    private int price;

    public Shop() {
    }

    public Shop(String warrior, int attack, int hp, int price) {
        this.warrior = warrior;
        this.attack = attack;
        this.hp = hp;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
