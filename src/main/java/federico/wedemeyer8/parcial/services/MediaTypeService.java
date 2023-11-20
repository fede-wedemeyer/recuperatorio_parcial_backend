package federico.wedemeyer8.parcial.services;

import federico.wedemeyer8.parcial.models.MediaType;
import federico.wedemeyer8.parcial.repositories.Identifier.IdentifierRepository;
import federico.wedemeyer8.parcial.repositories.MediaTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediaTypeService {

    private final MediaTypeRepository mediaTypeRepository;
    private final IdentifierRepository identifierRepository;


    @Transactional
    public List<MediaType> findAll() {
        return mediaTypeRepository.findAll();
    }

    @Transactional
    public Optional<MediaType> findById(Integer id) {
        return mediaTypeRepository.findById(id);
    }

    @Transactional
    public MediaType createMediaType(String name) {
        Integer id = identifierRepository.nextValue("media_types");
        MediaType mediaType = new MediaType(id, name);

        return mediaTypeRepository.save(mediaType);
    }

    @Transactional
    public MediaType updateMediaType(Integer id, String name) {
        MediaType existingMediaType = mediaTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Media type no encontrado"));

        existingMediaType.setName(name);

        mediaTypeRepository.save(existingMediaType);
        return existingMediaType;
    }

    @Transactional
    public void deleteMediaType(Integer id) {
        mediaTypeRepository.deleteById(id);
    }
}


