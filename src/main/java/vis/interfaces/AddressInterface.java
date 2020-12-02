package vis.interfaces;

import vis.entities.Address;

public interface AddressInterface {
    Integer insert(Address address);
    Integer update(Address address);
    Integer delete(Integer id);
}
