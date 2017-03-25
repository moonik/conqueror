package conqueror.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserGold {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private Long gold;

    public UserGold(){}

    public UserGold(Long id, String userName, Long gold) {
        this.id = id;
        this.userName = userName;
        this.gold = gold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }
}
