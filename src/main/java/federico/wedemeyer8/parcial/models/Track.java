package federico.wedemeyer8.parcial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.standard.Media;
import java.util.List;

@Entity
@Table(name= Track.TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    public static final String TABLE_NAME = "tracks";

    @Id
    @Column(name = "TrackId")
    private Integer trackId;

    @Column(name = "Name")
    String name;

    @Column(name = "Composer")
    String composer;

    @Column(name = "milliseconds")
    Integer miliseconds;

    @Nullable
    @Column(name = "Bytes")
    Integer bytes;

    @Column(name = "UnitPrice")
    Float unitPrice;


    @ManyToOne
    @JoinColumn(name = "AlbumId")
    Album album;


    @OneToOne
    @JoinColumn(name = "GenreId")
    Genre genre;

    @OneToOne
    @JoinColumn(name = "MediaTypeId")
    MediaType mediaType;

    //@ManyToMany(mappedBy = "tracks")
    //@JsonIgnore
    //private List<Playlist> playlists;

}
