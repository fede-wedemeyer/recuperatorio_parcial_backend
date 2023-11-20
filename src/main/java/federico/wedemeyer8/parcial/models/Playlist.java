package federico.wedemeyer8.parcial.models;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "playlists")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Playlist {
    @Id
    @Column(name = "PlaylistId")
    private Integer playlistId;

    @Column(name = "Name")
    private String name;

    @ManyToMany
    @JoinTable(name = "playlist_track",
            joinColumns = @JoinColumn(name = "PlaylistId"),
            inverseJoinColumns = @JoinColumn(name = "TrackId"))
    private List<Track> tracks;
}
