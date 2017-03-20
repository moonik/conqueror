package conqueror.army;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Army {

    @Id
    @GeneratedValue
    private Long id;

    private String typeOfWarrior;
    private Long amount;
    private Long castleId;
    private Boolean deffense;

    public Army() {
    }

    public Army(String typeOfWarrior, Long amount, Long castleId, Boolean deffense) {
        this.typeOfWarrior = typeOfWarrior;
        this.amount = amount;
        this.castleId = castleId;
        this.deffense = deffense;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfWarrior() {
        return typeOfWarrior;
    }

    public void setTypeOfWarrior(String typeOfWarrior) {
        this.typeOfWarrior = typeOfWarrior;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getCastleId() {
        return castleId;
    }

    public void setCastleId(Long castleId) {
        this.castleId = castleId;
    }

    public Boolean getDeffense() {
        return deffense;
    }

    public void setDeffense(Boolean deffense) {
        this.deffense = deffense;
    }
}
