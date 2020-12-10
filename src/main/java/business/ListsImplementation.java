package business;

import gateways.PlayerGateway;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListsImplementation implements MyList {
    @Override
    public ArrayList<Player> getPlayerList() throws SQLException {
        return getList();
    }

    private static ArrayList<Player> getList() throws SQLException {
        return PlayerGateway.fetch();
    }
}
