package conqueror.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserResources {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private Long gold;

    public UserResources() {
    }

    public UserResources(Long id, Long userId, Long gold) {
        this.id = id;
        this.userId = userId;
        this.gold = gold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
