package federico.wedemeyer8.parcial.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "media_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MediaTypeId")
    private Integer mediaTypeId;

    @Column(name = "Name")
    private String name;
}
