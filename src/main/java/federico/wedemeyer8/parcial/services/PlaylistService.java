package federico.wedemeyer8.parcial.services;

import federico.wedemeyer8.parcial.models.Playlist;
import federico.wedemeyer8.parcial.models.Track;
import federico.wedemeyer8.parcial.repositories.Identifier.IdentifierRepository;
import federico.wedemeyer8.parcial.repositories.PlaylistRepository;
import federico.wedemeyer8.parcial.repositories.TrackRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    final private PlaylistRepository playlistRepository;
    final private IdentifierRepository identifierRepository;
    final private TrackRepository trackRepository;

    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }
    public Optional<Playlist> findById(Integer id) {return playlistRepository.findById(id);
    }
    @Transactional
    public Playlist createPlaylist(String name, List<Integer> tracks) {
        Integer id = identifierRepository.nextValue("playlists");

        List<Track> trackList = new ArrayList<>();

        for (Integer trackId : tracks) {
            trackList.add(trackRepository.findById(trackId).orElseThrow(() -> new IllegalArgumentException("Track no encontrado")));}
        Playlist playlist = new Playlist(id, name, trackList);

        return playlistRepository.save(playlist);
    }

    @Transactional
    public Playlist updatePlaylist(Integer id, String name, List<Integer> tracks) {
        Playlist existingPlaylist = playlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playlist no encontrado"));

        List<Track> trackList = new ArrayList<>();
        for (Integer trackId : tracks) {
            trackList.add(trackRepository.findById(trackId).orElseThrow(() -> new IllegalArgumentException("Track no encontrado")));}

        existingPlaylist.setName(name);
        existingPlaylist.setTracks(trackList);

        playlistRepository.save(existingPlaylist);
        return existingPlaylist;
    }

    @Transactional
    public void deletePlaylist(Integer id) {
        Playlist existingPlaylist = playlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playlist no encontrado"));

        playlistRepository.delete(existingPlaylist);}

}


