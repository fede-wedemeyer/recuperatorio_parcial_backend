package federico.wedemeyer8.parcial.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name= Artist.TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Artist {
    public static final String TABLE_NAME = "artists";

    @Id
    @Column(name= "ArtistId")
    private Integer artistId;

    @Column(name="Name")
    private String name;

}
