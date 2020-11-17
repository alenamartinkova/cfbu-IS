package dais.entities;

import java.util.Date;

public class Match {
    Integer matchID;
    Boolean postponed;
    Date date;

    Match(Integer matchID, Boolean p, Date d) {
        this.matchID = matchID;
        this.postponed = p;
        this.date = d;
    }

    public Boolean getPostponed() {
        return this.postponed;
    }

    public Date getDate() {
        return this.date;
    }

    public Integer getMatchID() {
        return this.matchID;
    }
}
