package federico.wedemeyer8.parcial.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name= Album.TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Album {
    public static final String TABLE_NAME = "albums" ;

    @Id
    @Column(name = "albumid")
    private Integer albumId;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ArtistId")
    private Artist artist;

}
