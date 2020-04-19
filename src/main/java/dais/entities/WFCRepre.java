package dais.entities;

public class WFCRepre {
    int wfc_id;
    int rank;
    int repre_id;

    public WFCRepre(int wfc_id, int r, int r_id) {
        this.wfc_id = wfc_id;
        this.rank = r;
        this.repre_id = r_id;
    }

    public int getWfcId() {
        return this.wfc_id;
    }

    public int getRank() {
        return this.rank;
    }

    public int getRepreId() {
        return this.repre_id;
    }
}
