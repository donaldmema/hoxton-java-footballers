package com.footballers.footballers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private TeamRepo teamRepo;

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }

    @GetMapping("/players/{playerId}")
    public Player getPlayer(@PathVariable Integer playerId) {
        return playerRepo.findById(playerId).get();
    }

    @PostMapping("/teams/{teamId}/players")
    public Player createPlayer(@RequestBody Player playerData, @PathVariable Integer teamId) {
        playerData.team = teamRepo.findById(teamId).get();
        return playerRepo.save(playerData);
    }
}

interface PlayerRepo extends JpaRepository<Player, Integer> {
}

// interface TeamRepo extends JpaRepository<Team, Integer> {
// }