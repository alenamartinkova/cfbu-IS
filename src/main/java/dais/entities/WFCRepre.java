package dais.entities;

public class WFCRepre {
    Integer wfc_id;
    Integer rank;
    Integer repre_id;

    public WFCRepre(Integer wfc_id, Integer r, Integer r_id) {
        this.wfc_id = wfc_id;
        this.rank = r;
        this.repre_id = r_id;
    }

    public Integer getWfcId() {
        return this.wfc_id;
    }

    public Integer getRank() {
        return this.rank;
    }

    public Integer getRepreId() {
        return this.repre_id;
    }
}
