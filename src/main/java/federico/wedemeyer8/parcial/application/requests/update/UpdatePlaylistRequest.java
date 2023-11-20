package federico.wedemeyer8.parcial.application.requests.update;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePlaylistRequest {
    String name;
    List<Integer> tracks;
}
