package federico.wedemeyer8.parcial.application.responses;

import federico.wedemeyer8.parcial.models.MediaType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediaTypeResponse {
    Integer id;
    String name;

    public static MediaTypeResponse from(MediaType media){
        return MediaTypeResponse.builder()
                .id(media.getMediaTypeId())
                .name(media.getName())
                .build();
    }
}
