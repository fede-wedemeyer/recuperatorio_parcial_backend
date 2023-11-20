package federico.wedemeyer8.parcial.application.responses;

import federico.wedemeyer8.parcial.models.Album;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AlbumResponse {
    Integer id;
    String title;
    ArtistResponse artist;

    public static AlbumResponse from(Album album) {
        if (album == null) {
            return null;
        } else {
            return AlbumResponse.builder()
                    .id(album.getAlbumId())
                    .title(album.getTitle())
                    .artist(ArtistResponse.from(album.getArtist()))
                    .build();
        }
    }

}
