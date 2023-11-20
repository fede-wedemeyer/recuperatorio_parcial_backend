package federico.wedemeyer8.parcial.application.responses;

import federico.wedemeyer8.parcial.models.Track;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackResponse {
    Integer id;
    String name;
    AlbumResponse album;
    String media_type;
    String genre;
    String composer;
    Integer milliseconds;
    Integer bytes;
    Float unitprice;


    public static TrackResponse from(Track track) {
        return TrackResponse.builder()
                .id(track.getTrackId())
                .name(track.getName())
                .album(AlbumResponse.from(track.getAlbum()))
                .media_type(track.getMediaType().getName())
                .genre(track.getGenre().getName())
                .composer(track.getComposer())
                .milliseconds(track.getMiliseconds())
                .bytes(track.getBytes())
                .unitprice(track.getUnitPrice())
                .build();
    }
}