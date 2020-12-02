package vis.interfaces;

import vis.entities.Address;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddressInterface {
    Integer insert(Address address);
    Integer update(Address address);
    Integer delete(Integer id);
    ArrayList<Address> fetch() throws SQLException;
    ArrayList<Address> fetchByAttr(Object ... values);
}
