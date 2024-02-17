/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.player.service;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.player.model.Player;
import com.example.player.repository.PlayerRepository;
import com.example.player.repository.PlayerJpaRepository;

import java.util.*;

@Service
public class PlayerJpaService implements PlayerRepository {

    @Autowired
    public PlayerJpaRepository playerJpaRepository;

    @Override
    public ArrayList<Player> getPlayers() {
        Collection<Player> playerCollection = playerJpaRepository.findAll();
        ArrayList<Player> players = new ArrayList<>(playerCollection);

        return players;
    }

    @Override
    public Player getPlayerById(int playerId) {

        try {
            Player player = playerJpaRepository.findById(playerId).get();
            return player;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Player addPlayer(Player player) {
        playerJpaRepository.save(player);
        return player;

    }

    @Override
    public Player updatePlayer(int playerId, Player player) {
        try {
            Player newplayer = playerJpaRepository.findById(playerId).get();

            if (player.getPlayerName() != null) {
                newplayer.setPlayerName(player.getPlayerName());
            }

            if (player.getJerseyNumber() != 0) {
                newplayer.setJerseyNumber(player.getJerseyNumber());
            }

            if (player.getRole() != null) {
                newplayer.setRole(player.getRole());
            }
            playerJpaRepository.save(newplayer);

            return newplayer;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void deletePlayer(int playerId) {

        try {
            playerJpaRepository.deleteById(playerId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}