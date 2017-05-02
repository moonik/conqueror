package conqueror.userResources;

public class UserResourcesDTO {

    private Long idSender;
    private Long amount;

    public UserResourcesDTO(){}

    public UserResourcesDTO(Long idSender, Long amount) {
        this.idSender = idSender;
        this.amount = amount;
    }

    public Long getIdSender() {
        return idSender;
    }

    public void setIdSender(Long idSender) {
        this.idSender = idSender;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
