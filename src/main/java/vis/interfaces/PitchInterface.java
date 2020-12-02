package vis.interfaces;

import vis.entities.Pitch;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PitchInterface {
    Integer insert(Pitch pitch);
    Integer update(Pitch pitch);
    Integer delete(Integer id);
    ArrayList<Pitch> fetch() throws SQLException;
    ArrayList<Pitch> fetchByAttr(Object ... values);
}
