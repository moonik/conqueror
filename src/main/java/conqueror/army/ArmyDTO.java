package conqueror.army;

public class ArmyDTO {

    private String typeOfWarrior;
    private Long amount;
    private Long idSender;

    public ArmyDTO(){}

    public ArmyDTO(String typeOfWarrior, Long amount, Long idSender) {
        this.typeOfWarrior = typeOfWarrior;
        this.amount = amount;
        this.idSender = idSender;
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

    public Long getIdSender() {
        return idSender;
    }

    public void setIdSender(Long idSender) {
        this.idSender = idSender;
    }
}
