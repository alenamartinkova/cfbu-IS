package org.alenamartinkova.dais.DTO;

import org.alenamartinkova.dais.business.Statistics;

public class StatisticsDTO {
    Integer statsID;
    Integer playerID;
    Integer assists;
    Integer goals;
    Integer points;

    public StatisticsDTO() {}

    public StatisticsDTO(Integer sID, Integer pID, Integer a, Integer g, Integer p) {
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

    public Statistics toBO() {
        return new Statistics(this.statsID, this.playerID, this.assists, this.goals, this.points);
    }
}

