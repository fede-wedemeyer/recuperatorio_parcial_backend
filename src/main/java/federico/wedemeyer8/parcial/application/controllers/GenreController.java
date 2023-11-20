package federico.wedemeyer8.parcial.application.controllers;

import federico.wedemeyer8.parcial.application.requests.create.CreateGenreRequest;
import federico.wedemeyer8.parcial.application.requests.update.UpdateGenreRequest;
import federico.wedemeyer8.parcial.application.responses.GenreResponse;
import federico.wedemeyer8.parcial.application.responses.ResponseHandler;
import federico.wedemeyer8.parcial.models.Genre;
import federico.wedemeyer8.parcial.services.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;


    @GetMapping("/api/genres")
    public ResponseEntity<Object> getAll() {
       try {
            val genres = genreService.findAll()
                    .stream()
                    .map(GenreResponse::from)
                    .toList();
            return ResponseHandler.success(genres);

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/api/genres/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Integer id) {
        try {
            Optional<Genre> genre = genreService.findById(id);
            return ResponseHandler.success(genre);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }

    }

    @PostMapping("/api/genres")
    public ResponseEntity<Object> create(@RequestBody CreateGenreRequest request) {
        try {
            Genre genre = genreService.createGenre(request.getName());
            return ResponseHandler.success(GenreResponse.from(genre));

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();

        }
    }

    @PutMapping("/api/genres/{id}")
    public ResponseEntity<Object> update(@RequestBody UpdateGenreRequest request, @PathVariable Integer id) {
        try {
            Genre updatedGenre = genreService.updateGenre(id, request.getName());
            return ResponseHandler.success(GenreResponse.from(updatedGenre));

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/api/genres/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            genreService.deleteGenre(id);
            return ResponseHandler.success("Genre eliminated");

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
