package dais.entities;

public class ReprePlayer {
    Integer year;
    Integer repre_id;
    Integer player_id;

    public ReprePlayer(Integer y, Integer r, Integer p) {
        this.year = y;
        this.repre_id = r;
        this.player_id = p;
    }

    public Integer getYear() {
        return this.year;
    }

    public Integer getRepreId() {
        return this.repre_id;
    }

    public Integer getPlayerId() {
        return this.player_id;
    }
}
