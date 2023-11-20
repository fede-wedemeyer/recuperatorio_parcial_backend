package federico.wedemeyer8.parcial.services;

import federico.wedemeyer8.parcial.models.Album;
import federico.wedemeyer8.parcial.models.Artist;
import federico.wedemeyer8.parcial.repositories.AlbumRepository;
import federico.wedemeyer8.parcial.repositories.Identifier.IdentifierRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistService artistService;
    private final IdentifierRepository identifierRepository;

    public List<Album> findAll(){
        return albumRepository.findAll();
    }

    public Optional<Album> findById(Integer id){
        return albumRepository.findById(id);
    }


    @Transactional
    public Album createAlbum(Integer artist_id, String title){
        val albumId = identifierRepository.nextValue("albums");
        val artist = artistService.findById(artist_id).orElseThrow(() -> new IllegalArgumentException("Artist not found"));
        Album newAlbum = new Album(albumId, title, artist);
        albumRepository.save(newAlbum);
        return newAlbum;
    }

    @Transactional
    public void deleteAlbum(Integer id){
        albumRepository.deleteById(id);
    }

    @Transactional
    public Album updateAlbum(Integer id, String title, Integer artist_id) {
        val artist = artistService.findById(artist_id).orElseThrow(() -> new IllegalArgumentException("Artista no encontrado"));
        val existingAlbum = albumRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Album no encontrado"));
        existingAlbum.setAlbumId(id);
        existingAlbum.setArtist(artist);
        existingAlbum.setTitle(title);
        albumRepository.save(existingAlbum);

        return existingAlbum;
    }
}
