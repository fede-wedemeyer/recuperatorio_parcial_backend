package federico.wedemeyer8.parcial.application.controllers;

import federico.wedemeyer8.parcial.application.requests.create.CreateMediaTypeRequest;
import federico.wedemeyer8.parcial.application.requests.update.UpdateMediaTypeRequest;
import federico.wedemeyer8.parcial.application.responses.MediaTypeResponse;
import federico.wedemeyer8.parcial.application.responses.ResponseHandler;
import federico.wedemeyer8.parcial.models.MediaType;
import federico.wedemeyer8.parcial.services.MediaTypeService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MediaTypeController {
    private final MediaTypeService mediaTypeService;


    @GetMapping("/api/media-types")
    public ResponseEntity<Object> findAll() {
        try {
            val mediaTypes = mediaTypeService.findAll()
                    .stream()
                    .map(MediaTypeResponse::from)
                    .toList();
            return ResponseHandler.success(mediaTypes);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/api/media-types/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        try { Optional<MediaType> mediaType = mediaTypeService.findById(id);
            return ResponseHandler.success(mediaType);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }

    }

    @PostMapping("/api/media-types")
    public ResponseEntity<Object> create(@RequestBody CreateMediaTypeRequest request)
    {
        try {
            MediaType mediaType = mediaTypeService.createMediaType(request.getName());
            return ResponseHandler.success(MediaTypeResponse.from(mediaType));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }

    }

    @PutMapping("/api/media-types/{id}")
    public ResponseEntity<Object> update(@RequestBody UpdateMediaTypeRequest request, @PathVariable Integer id) {
        try {
            MediaType mediaType = mediaTypeService.updateMediaType(id, request.getName());
            return ResponseHandler.success(MediaTypeResponse.from(mediaType));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();

        }
    }

    @DeleteMapping("/api/media-types/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        try {
            mediaTypeService.deleteMediaType(id);
            return ResponseHandler.success("Media type eliminated");
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

}
