package com.tournamentapi.tournament.repository;

import com.tournamentapi.tournament.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
