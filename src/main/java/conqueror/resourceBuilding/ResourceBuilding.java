package conqueror.resourceBuilding;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ResourceBuilding {

    @Id
    @GeneratedValue
    private Long id;

    private Long castleId;
    private int profit;
    private int goldCostToUpgrade;
    private int currentLevel;

    public ResourceBuilding(){}

    public ResourceBuilding(Long castleId, int profit, int goldCostToUpgrade, int currentLevel) {
        this.castleId = castleId;
        this.profit = profit;
        this.goldCostToUpgrade = goldCostToUpgrade;
        this.currentLevel = currentLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCastleId() {
        return castleId;
    }

    public void setCastleId(Long castleId) {
        this.castleId = castleId;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getGoldCostToUpgrade() {
        return goldCostToUpgrade;
    }

    public void setGoldCostToUpgrade(int goldCostToUpgrade) {
        this.goldCostToUpgrade = goldCostToUpgrade;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
