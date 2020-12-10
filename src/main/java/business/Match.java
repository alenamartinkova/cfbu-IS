package business;

import DTO.MatchDTO;
import gateways.MatchGateway;

import java.sql.Timestamp;
import java.util.ArrayList;

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
        MatchDTO matchDTO = new MatchDTO(this.matchID, this.pitchID, this.date.toString(), this.pitchID);
        return matchDTO;
    }

    public static ArrayList<Match> arrayListToBO(ArrayList<MatchDTO> matchDTOS) {
        ArrayList<Match> m = new ArrayList<>();

        for(int i = 0; i < matchDTOS.size(); i++) {
            m.add(matchDTOS.get(i).toBO());
        }
        return m;
    }

    /**
     * Function that returns selected match
     * @param matchID
     * @return
     */
    public static Match fetchByID(Integer matchID) {
        return MatchGateway.fetchByID(matchID).toBO();
    }
}
