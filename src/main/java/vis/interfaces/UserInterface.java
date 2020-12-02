package vis.interfaces;

import vis.entities.User;

public interface UserInterface {
    Integer insert(User user);
    Integer update(User user);
    Integer delete(Integer id);
}
