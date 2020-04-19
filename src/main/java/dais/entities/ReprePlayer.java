package dais.entities;

public class ReprePlayer {
    int year;
    int repre_id;
    int player_id;

    public ReprePlayer(int y, int r, int p) {
        this.year = y;
        this.repre_id = r;
        this.player_id = p;
    }

    public int getYear() {
        return this.year;
    }

    public int getRepreId() {
        return this.repre_id;
    }

    public int getPlayerId() {
        return this.player_id;
    }
}
