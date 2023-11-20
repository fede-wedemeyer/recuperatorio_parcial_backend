package federico.wedemeyer8.parcial.application.responses;

import federico.wedemeyer8.parcial.models.Playlist;
import federico.wedemeyer8.parcial.models.Track;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaylistResponse {
    Integer id;
    String name;
    List<Track> tracks;

    public static PlaylistResponse from(Playlist playlist) {
        return PlaylistResponse.builder()
                .id(playlist.getPlaylistId())
                .name(playlist.getName())
                .tracks(playlist.getTracks())
                .build();

    }

}


