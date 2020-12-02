package vis.interfaces;

import vis.entities.Pitch;

public interface PitchInterface {
    Integer insert(Pitch pitch);
    Integer update(Pitch pitch);
    Integer delete(Integer id);
}
