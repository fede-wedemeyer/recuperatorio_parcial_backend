package federico.wedemeyer8.parcial.application.requests.update;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateAlbumRequest {
    @NotBlank(message = "Title is mandatory")
    String title;
    Integer artistId;
}