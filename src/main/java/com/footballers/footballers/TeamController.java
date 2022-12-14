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
public class TeamController {
    @Autowired
    private TeamRepo teamRepo;

    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    @GetMapping("/teams/{teamId}")
    public Team getTeam(@PathVariable Integer teamId) {
        return teamRepo.findById(teamId).get();
    }

    @GetMapping("/teams/{teamId}/score")
    public int getTeamScore(@PathVariable Integer teamId) {
        Team team = teamRepo.findById(teamId).get();
        int score = 0;
        for (Player player : team.players) {
            if (player.isReplacement) {
            } else {
                score += player.scoreOutOfTen;
            }
        }
        return score;
    }

    @PostMapping("/teams")
    public Team createTeam(@RequestBody Team teamData) {
        return teamRepo.save(teamData);
    }
}

interface TeamRepo extends JpaRepository<Team, Integer> {
}