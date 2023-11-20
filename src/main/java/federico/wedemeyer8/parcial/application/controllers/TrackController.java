package federico.wedemeyer8.parcial.application.controllers;

import federico.wedemeyer8.parcial.application.requests.create.CreateTrackRequest;
import federico.wedemeyer8.parcial.application.requests.update.UpdateTrackRequest;
import federico.wedemeyer8.parcial.application.responses.ResponseHandler;
import federico.wedemeyer8.parcial.application.responses.TrackResponse;
import federico.wedemeyer8.parcial.models.Track;
import federico.wedemeyer8.parcial.services.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @GetMapping("/api/tracks")
    public ResponseEntity<Object> getAll() {
        try {
            List<TrackResponse> tracks = trackService.findAll()
                    .stream()
                    .map(TrackResponse::from)
                    .toList();

            return ResponseHandler.success(tracks);

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }

    }

    @GetMapping("/api/tracks/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Integer id) {
        try {
            val track = trackService.findById(id)
                    .map(aTrack -> ResponseHandler.success(TrackResponse.from(aTrack)))
                    .orElseGet(ResponseHandler::notFound);

            return ResponseHandler.success(track);

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping("/api/tracks")
    public ResponseEntity<Object> create(@RequestBody CreateTrackRequest request) {
        try {
            Track track = trackService.createTrack(request.getName(), request.getAlbumId(), request.getMediaTypeId(),
                    request.getGenreId(), request.getComposer(), request.getMilliseconds(), request.getBytes(), request.getUnitPrice());

            return ResponseHandler.success(TrackResponse.from(track));

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        }

    }

    @PutMapping("/api/tracks/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateTrackRequest request){
        try {
            Track updatedTrack = trackService.updateTrack(id, request.getName(), request.getAlbumId(), request.getMediaTypeId(),
                    request.getGenreId(), request.getComposer(), request.getMilliseconds(), request.getBytes(), request.getUnitPrice());

            return ResponseHandler.success(TrackResponse.from(updatedTrack));

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }

    }

    @DeleteMapping("/api/tracks/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            trackService.deleteTrack(id);
            return ResponseHandler.success("Track eliminated");

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        }
    }
}
