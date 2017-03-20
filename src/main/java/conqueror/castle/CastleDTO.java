package conqueror.castle;

public class CastleDTO {

    private String owner;
    private String castleName;
    private int x;
    private int y;

    public CastleDTO() {
    }

    public CastleDTO(String owner, String castleName, int x, int y) {
        this.owner = owner;
        this.castleName = castleName;
        this.x = x;
        this.y = y;
    }
}
