package federico.wedemeyer8.parcial.application.requests.update;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateMediaTypeRequest {
    String name;
}
