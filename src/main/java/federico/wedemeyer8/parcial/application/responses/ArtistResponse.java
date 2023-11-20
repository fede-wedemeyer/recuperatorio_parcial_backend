package federico.wedemeyer8.parcial.application.responses;

import federico.wedemeyer8.parcial.models.Artist;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArtistResponse {
    Integer id;
    private String name;

    public static ArtistResponse from(Artist artist){
        return ArtistResponse.builder()
                .id(artist.getArtistId())
                .name(artist.getName())
                .build();
    }
}