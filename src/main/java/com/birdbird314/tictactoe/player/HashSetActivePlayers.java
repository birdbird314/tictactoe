package com.birdbird314.tictactoe.player;

import java.util.HashSet;
import java.util.Set;

public class HashSetActivePlayers implements ActivePlayers {
  private final Set<String> players;

  public HashSetActivePlayers() {
    this.players = new HashSet<>();
  }

  @Override
  public boolean isPlaying(String playerId) {
    return players.contains(playerId);
  }

  @Override
  public void aGameStartedFor(String playerId) {
    players.add(playerId);
  }
}
