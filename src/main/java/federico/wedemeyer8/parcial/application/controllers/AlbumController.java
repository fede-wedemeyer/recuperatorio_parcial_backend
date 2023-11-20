package federico.wedemeyer8.parcial.application.controllers;
import federico.wedemeyer8.parcial.application.requests.create.CreateAlbumRequest;
import federico.wedemeyer8.parcial.application.requests.update.UpdateAlbumRequest;
import federico.wedemeyer8.parcial.application.responses.AlbumResponse;
import federico.wedemeyer8.parcial.application.responses.ResponseHandler;
import federico.wedemeyer8.parcial.models.Album;
import federico.wedemeyer8.parcial.services.AlbumService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AlbumController {

        private final AlbumService albumService;

        @GetMapping("/api/albums")
        public ResponseEntity<Object> getAll() {
            try {
                val albums = albumService.findAll()
                        .stream()
                        .map(AlbumResponse::from)
                        .toList();
                return ResponseHandler.success(albums);

            } catch (IllegalArgumentException e) {
                return ResponseHandler.badRequest(e.getMessage());
            } catch (Exception e) {
                return ResponseHandler.internalError();
            }
        }


        @GetMapping("/api/albums/{id}")
        public ResponseEntity<Object> getOne(@PathVariable Integer id) {
            try {
                val album = albumService.findById(id)
                        .map(aAlbum -> ResponseHandler.success(AlbumResponse.from(aAlbum)))
                        .orElseGet(ResponseHandler::notFound);
                return ResponseHandler.success(album);

            } catch (IllegalArgumentException e) {
                return ResponseHandler.badRequest(e.getMessage());
            } catch (Exception e) {
                return ResponseHandler.internalError();
            }
        }


        @PostMapping("/api/albums")
        public ResponseEntity<Object> create(@Valid @RequestBody CreateAlbumRequest request) {
            try {
                Album album = albumService.createAlbum(request.getArtistId(), request.getTitle());
                return ResponseHandler.success(AlbumResponse.from(album));
            } catch (IllegalArgumentException e) {
                return ResponseHandler.badRequest(e.getMessage());
            }
        }

        @PutMapping("/api/albums/{id}")
        public ResponseEntity<Object> update(@PathVariable Integer id, @Valid @RequestBody UpdateAlbumRequest albumRequest) {
            try {
                Album updatedAlbum = albumService.updateAlbum(id, albumRequest.getTitle(), albumRequest.getArtistId());
                return ResponseHandler.success(AlbumResponse.from(updatedAlbum));

            } catch (IllegalArgumentException e) {
                return ResponseHandler.badRequest(e.getMessage());
            } catch (Exception e) {
                return ResponseHandler.internalError();
            }
        }

        @DeleteMapping("/api/albums/{id}")
        public ResponseEntity<Object> delete(@PathVariable Integer id) {
            try {
                albumService.deleteAlbum(id);
                return ResponseHandler.success("Album elimnated");
            } catch (IllegalArgumentException e) {
                return ResponseHandler.badRequest(e.getMessage());
            }
        }
    }

