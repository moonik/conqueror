package conqueror.shop;

public class ShopDTO {

    private Long amount;
    private String warrior;

    public ShopDTO(){}

    public ShopDTO(Long amount, String warrior) {
        this.amount = amount;
        this.warrior = warrior;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getWarrior() {
        return warrior;
    }

    public void setWarrior(String warrior) {
        this.warrior = warrior;
    }
}
