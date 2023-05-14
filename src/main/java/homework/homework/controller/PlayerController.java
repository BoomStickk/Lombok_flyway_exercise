package homework.homework.controller;

import homework.homework.entity.Player;
import homework.homework.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerRepository playerRepository;

    //    public PlayerController(PlayerRepository playerRepository) {
//        this.playerRepository = playerRepository;
//    }
    @GetMapping
    public ResponseEntity<List<Player>> getPlayers() {
        List<Player> players = playerRepository.findAll();
        return ResponseEntity.ok(players);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") Long id) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()) {
            return ResponseEntity.ok(player.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Player> deletePlayerById(@PathVariable("id") Long id) {
        playerRepository.deleteById(id);
        return ResponseEntity.
                noContent().
                build();
    }
    @PostMapping
    public ResponseEntity<Player> create(@RequestBody Player player,
                                         UriComponentsBuilder uriComponentsBuilder) {
        Player savedPlayer = playerRepository.save(player);

        URI location = uriComponentsBuilder.path("/players/{id}").
                buildAndExpand(savedPlayer.getId()).toUri();
        return ResponseEntity.
                created(location).
                build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Player> update(@PathVariable Long id, @RequestBody Player player) {
        Optional<Player> foundPlayer = playerRepository.findById(id);

        if (foundPlayer.isPresent()) {

            String name = player.getName();
            int level = player.getLevel();
            String game=player.getGame();

            foundPlayer.get().setName(name);
            foundPlayer.get().setLevel(level);
            foundPlayer.get().setGame(game);

            playerRepository.save(foundPlayer.get());
            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.notFound().build();
        }
    }
}
