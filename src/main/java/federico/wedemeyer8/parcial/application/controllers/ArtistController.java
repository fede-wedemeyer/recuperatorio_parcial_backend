package federico.wedemeyer8.parcial.application.controllers;

import federico.wedemeyer8.parcial.application.requests.create.CreateArtistRequest;
import federico.wedemeyer8.parcial.application.requests.update.UpdateArtistRequest;
import federico.wedemeyer8.parcial.application.responses.ArtistResponse;
import federico.wedemeyer8.parcial.application.responses.ResponseHandler;
import federico.wedemeyer8.parcial.models.Artist;
import federico.wedemeyer8.parcial.services.ArtistService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping("/api/artists")
    public ResponseEntity<Object> getAll(){
        try{
            val artists = artistService.findAll()
                    .stream()
                    .map(ArtistResponse::from)
                    .toList();
            return ResponseHandler.success(artists);

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }


    @GetMapping("/api/artists/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Integer id){
        try{
            val artist = artistService.findById(id)
                    .map(aArtist -> ResponseHandler.success(ArtistResponse.from(aArtist)))
                    .orElseGet(ResponseHandler::notFound);
            return ResponseHandler.success(artist);

        } catch (IllegalArgumentException e){
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }


    @PostMapping("/api/artists")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateArtistRequest request) {
        try{
            Artist artist = artistService.createArtist(request.getName());
            return ResponseHandler.success(ArtistResponse.from(artist));
        } catch(IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        }
    }


    @PutMapping("/api/artists/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @Valid @RequestBody UpdateArtistRequest artistRequest) {
        try {
            Artist updatedArtist = artistService.updateArtist(id, artistRequest.getName());
            return ResponseHandler.success(ArtistResponse.from(updatedArtist));

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }


    @DeleteMapping("/api/artists/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            artistService.deleteArtist(id);
            return ResponseHandler.success("Artist elimnated");
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        }
    }

}
