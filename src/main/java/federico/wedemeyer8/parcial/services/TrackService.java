package federico.wedemeyer8.parcial.services;

import federico.wedemeyer8.parcial.models.Album;
import federico.wedemeyer8.parcial.models.Genre;
import federico.wedemeyer8.parcial.models.MediaType;
import federico.wedemeyer8.parcial.models.Track;
import federico.wedemeyer8.parcial.repositories.*;
import federico.wedemeyer8.parcial.repositories.Identifier.IdentifierRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackService {

    final private TrackRepository trackRepository;
    final private AlbumRepository albumRepository;
    final private IdentifierRepositoryImpl identifierRepository;
    final private GenreRepository genreRepository;
    final private MediaTypeRepository mediaTypeRepository;


    public Optional<Track> findById(Integer id) {return trackRepository.findById(id);}

    public List<Track> findAll() {return trackRepository.findAll();}
    @Transactional
    public Track createTrack(String name, Integer albumId, Integer mediaTypeId, Integer genreId, String composer,
                             Integer miliseconds, Integer bytes, Float unitPrice) {

        Integer id = identifierRepository.nextValue("Tracks");

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album no encontrado"));

        MediaType mediaType = mediaTypeRepository.findById(mediaTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de media no encontrado"));

        Genre genre = genreRepository.findById(genreId).
                orElseThrow(()-> new IllegalArgumentException("Género no encontrado"));

        Track track = new Track(id, name, composer, miliseconds, bytes, unitPrice, album, genre, mediaType);

        return trackRepository.save(track);

    }
    @Transactional
    public Track updateTrack(Integer trackId, String name, Integer albumId, Integer mediaTypeId, Integer genreId, String composer,
                             Integer miliseconds, Integer bytes, Float unitPrice) {

        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new IllegalArgumentException("Track no encontrado"));

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album no encontrado"));

        MediaType mediaType = mediaTypeRepository.findById(mediaTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de media no encontrado"));

        Genre genre = genreRepository.findById(genreId).
                orElseThrow(()-> new IllegalArgumentException("Género no encontrado"));

        track.setAlbum(album);
        track.setName(name);
        track.setComposer(composer);
        track.setGenre(genre);
        track.setBytes(bytes);
        track.setMiliseconds(miliseconds);
        track.setMediaType(mediaType);
        track.setUnitPrice(unitPrice);

        trackRepository.save(track);
        return track;

    }
    @Transactional
    public void deleteTrack(Integer id) {trackRepository.deleteById(id);}

}
