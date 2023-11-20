package federico.wedemeyer8.parcial.application.requests.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = {@JsonIgnore})
public class CreateTrackRequest {

    String name;

    String composer;

    Integer milliseconds;

    Integer bytes;

    Float unitPrice;

    Integer mediaTypeId;

    Integer genreId;

    Integer albumId;
}
