package com.tournamentapi.tournament.controller;

import com.tournamentapi.tournament.model.Player;
import com.tournamentapi.tournament.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/get-all-players")
    public List<Player> getAllPlayers() {
        List<Player> allPlayers = playerRepository.findAll();
        return allPlayers;
    }

    @GetMapping("/get-player/{id}")
    public Player getPlayerById(@PathVariable(value = "id")Long playerId) {
        Player player = playerRepository.findById(playerId).get();
        return player;
    }

    @PostMapping("/create-player")
    public Player createPlayer(@RequestBody Player player) {
        Player savedPlayer = playerRepository.save(player);
        return savedPlayer;
    }

    @PutMapping("/update-player/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable(value = "id")Long requestPlayerId,
                                               @RequestBody Player requestPlayer) {
        Player player = playerRepository.findById(requestPlayerId).get();
        player.setAge(requestPlayer.getAge());
        player.setFirstName((requestPlayer.getFirstName()));
        player.setLastName((requestPlayer.getLastName()));
        final Player updatedPlayer = playerRepository.save(player);
        return ResponseEntity.ok(updatedPlayer);
    }

    @DeleteMapping("/delete-player/{id}")
    public Map<String, Boolean> deletePlayer(@PathVariable(value = "id")Long playerId) {
        Player player = playerRepository.findById(playerId).get();
        playerRepository.delete(player);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
