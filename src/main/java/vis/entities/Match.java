package dais.entities;

import java.util.Date;

public class Match {
    Integer matchID;
    Integer postponed;
    Date date;

    public Match(Integer matchID, Integer p, Date d) {
        this.matchID = matchID;
        this.postponed = p;
        this.date = d;
    }

    public Integer getPostponed() {
        return this.postponed;
    }

    public Date getDate() {
        return this.date;
    }

    public Integer getMatchID() {
        return this.matchID;
    }
}
