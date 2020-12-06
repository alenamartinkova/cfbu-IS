package vis;
import vis.business.League;
import vis.gateways.LeagueGateway;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        LeagueGateway l = new LeagueGateway();
        League league = new League(6, "TEST", 6);

    }
}