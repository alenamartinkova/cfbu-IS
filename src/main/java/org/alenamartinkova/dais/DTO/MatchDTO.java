package org.alenamartinkova.dais.DTO;

import org.alenamartinkova.dais.business.Match;

import java.sql.Timestamp;

public class MatchDTO {
    Integer matchID;
    Integer postponed;
    Timestamp date;
    Integer pitchID;

    public MatchDTO() {}

    public MatchDTO (Integer matchID, Integer p, String d, Integer pID) {
        this.matchID = matchID;
        this.postponed = p;
        this.date = Timestamp.valueOf(d);
        this.pitchID = pID;
    }

    public Integer getPostponed() {
        return this.postponed;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public Integer getMatchID() {
        return this.matchID;
    }

    public Integer getPitchID() { return this.pitchID; }

    public Match toBO() {
        return new Match(this.matchID, this.pitchID, this.date.toString(), this.pitchID);
    }
}
