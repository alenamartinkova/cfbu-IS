package org.alenamartinkova.dais.business;

import org.alenamartinkova.dais.DTO.StatisticsDTO;
import org.alenamartinkova.dais.gateways.StatisticsGateway;

public class Statistics {
    Integer statsID;
    Integer playerID;
    Integer assists;
    Integer goals;
    Integer points;

    public Statistics(){};
    public Statistics(Integer sID, Integer pID, Integer a, Integer g, Integer p) {
        this.statsID = sID;
        this.playerID = pID;
        this.assists = a;
        this.goals = g;
        this.points = p;
    }

    public Integer getAssists() {
        return this.assists;
    }

    public Integer getGoals() {
        return this.goals;
    }

    public Integer getPlayerID() {
        return this.playerID;
    }

    public Integer getPoints() {
        return this.points;
    }

    public Integer getStatsID() {
        return this.statsID;
    }

    /**
     * Function that gets players statistics
     * @param playerID selected player ID
     * @return Statistics object
     */
    public static Statistics fetchByPlayerID(Integer playerID) {
        return StatisticsGateway.fetchByPlayerID(playerID).toBO();
    }

    public StatisticsDTO toDTO() {
        StatisticsDTO statisticsDTO = new StatisticsDTO(this.statsID, this.playerID, this.assists, this.goals, this.points);
        return statisticsDTO;
    }
}
