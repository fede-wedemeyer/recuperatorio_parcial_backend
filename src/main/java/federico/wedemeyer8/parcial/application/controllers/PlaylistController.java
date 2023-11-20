package federico.wedemeyer8.parcial.application.controllers;

import federico.wedemeyer8.parcial.application.requests.create.CreatePlaylistRequest;
import federico.wedemeyer8.parcial.application.requests.update.UpdatePlaylistRequest;
import federico.wedemeyer8.parcial.application.responses.PlaylistResponse;
import federico.wedemeyer8.parcial.application.responses.ResponseHandler;
import federico.wedemeyer8.parcial.models.Playlist;
import federico.wedemeyer8.parcial.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;


    @GetMapping("/api/playlists")
    public ResponseEntity<Object> getAll() {
        try {
            val playlists = playlistService.findAll()
                    .stream()
                    .map(PlaylistResponse::from)
                    .toList();
            return ResponseHandler.success(playlists);

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/api/playlists/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Integer id) {
        try {
            val playlist = playlistService.findById(id)
                    .map(aPlaylist -> ResponseHandler.success(PlaylistResponse.from(aPlaylist)))
                    .orElseGet(ResponseHandler::notFound);
            return ResponseHandler.success(playlist);

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }

    }

    @PostMapping("/api/playlists")
    public ResponseEntity<Object> create(@RequestBody CreatePlaylistRequest request) {
        try { Playlist playlist = playlistService.createPlaylist(request.getName(), request.getTracks());
            return ResponseHandler.success(PlaylistResponse.from(playlist));

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        }
    }

    @PutMapping("/api/playlists/{id}")
    public ResponseEntity<Object> update(@RequestBody UpdatePlaylistRequest request, @PathVariable Integer id) {
        try {
            Playlist playlist = playlistService.updatePlaylist(id, request.getName(), request.getTracks());


            return ResponseHandler.success(PlaylistResponse.from(playlist));

        } catch (IllegalArgumentException e) {

            return ResponseHandler.badRequest(e.getMessage());

        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }


    @DeleteMapping("/api/playlists/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            playlistService.deletePlaylist(id);
            return ResponseHandler.success("Playlist eliminated");
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        }
    }
}
