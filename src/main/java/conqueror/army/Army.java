package conqueror.army;

import conqueror.castle.Castle;
import conqueror.shop.Shop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Army {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Shop warrior;
    private Long amount;
    @OneToOne
    private Castle castle;
    private Boolean deffense;

    public Army() {
    }

    public Army(Shop warrior, Long amount, Castle castle, Boolean deffense) {
        this.warrior = warrior;
        this.amount = amount;
        this.castle = castle;
        this.deffense = deffense;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shop getWarrior() {
        return warrior;
    }

    public void setWarrior(Shop warrior) {
        this.warrior = warrior;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Castle getCastle() {
        return castle;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    public Boolean getDeffense() {
        return deffense;
    }

    public void setDeffense(Boolean deffense) {
        this.deffense = deffense;
    }
}
