package federico.wedemeyer8.parcial.application.responses;


import federico.wedemeyer8.parcial.models.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenreResponse {
    private Integer id;
    private String name;

    public static GenreResponse from(Genre genre){
        return GenreResponse.builder()
                .id(genre.getGenreId())
                .name(genre.getName())
                .build();
    }
}