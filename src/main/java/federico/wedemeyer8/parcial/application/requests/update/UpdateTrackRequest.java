package federico.wedemeyer8.parcial.application.requests.update;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTrackRequest {

    String name;

    String composer;

    Integer milliseconds;

    Integer bytes;

    Float unitPrice;

    Integer mediaTypeId;

    Integer genreId;

    Integer albumId;
}
