package federico.wedemeyer8.parcial.services;

import federico.wedemeyer8.parcial.models.Genre;
import federico.wedemeyer8.parcial.repositories.GenreRepository;
import federico.wedemeyer8.parcial.repositories.Identifier.IdentifierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    private final IdentifierRepository identifierRepository;

    @Transactional
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Transactional
    public Optional<Genre> findById(Integer id) {
        return genreRepository.findById(id);
    }

    @Transactional
    public Genre createGenre(String name) {
        Integer id = identifierRepository.nextValue("genres");
        Genre genre = new Genre(id, name);
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre updateGenre(Integer id, String name) {
        Genre existingGenre = genreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Género no encontrado"));
        existingGenre.setName(name);
        genreRepository.save(existingGenre);

        return existingGenre;
    }

    @Transactional
    public void deleteGenre(Integer id) {
        Genre existingGenre = genreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Género no encontrado"));
        genreRepository.delete(existingGenre);
    }

}
