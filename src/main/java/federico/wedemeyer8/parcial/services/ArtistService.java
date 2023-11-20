package federico.wedemeyer8.parcial.services;

import federico.wedemeyer8.parcial.models.Artist;
import federico.wedemeyer8.parcial.repositories.ArtistRepository;
import federico.wedemeyer8.parcial.repositories.Identifier.IdentifierRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistService{
    private final IdentifierRepository identifierRepository;
    private final ArtistRepository artistRepository;

    public List<Artist> findAll(){
        return artistRepository.findAll();
    }


    public Optional<Artist> findById(Integer id)
    {
        return artistRepository.findById(id);
    }

    @Transactional
    public Artist createArtist(String name){
        val artistId = identifierRepository.nextValue(Artist.TABLE_NAME);
        val newArtist = new Artist(artistId, name);
        return artistRepository.save(newArtist);
    }

    @Transactional
    public void deleteArtist(Integer id){
        artistRepository.deleteById(id);
    }

    @Transactional
    public Artist updateArtist(Integer id, String name) {
        val existingArtist = artistRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Artista no encontrado"));
        existingArtist.setName(name);
        artistRepository.save(existingArtist);

        return existingArtist;
    }

}