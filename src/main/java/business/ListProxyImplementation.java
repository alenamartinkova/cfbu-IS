package business;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListProxyImplementation implements MyList {
    private MyList playerList;

    @Override
    public ArrayList<Player> getPlayerList() throws SQLException {
        if (this.playerList == null) {
            System.out.println("Fetching list");
            this.playerList = new ListsImplementation();
        }
        return this.playerList.getPlayerList();
    }
}
