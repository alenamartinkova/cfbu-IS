package business;

import DTO.MatchDTO;
import gateways.MatchGateway;

import java.sql.Timestamp;

public class Match {
    Integer matchID;
    Integer postponed;
    Timestamp date;
    Integer pitchID;

    public Match(){};
    public Match(Integer matchID, Integer p, String d, Integer pID) {
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

    public MatchDTO toDTO() {
        return null;
    }

    /**
     * Function that returns selected match
     * @param matchID
     * @return
     */
    public static Match fetchByID(Integer matchID) {
        return MatchGateway.fetchByID(matchID);
    }
}
