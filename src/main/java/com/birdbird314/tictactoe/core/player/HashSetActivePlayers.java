package com.birdbird314.tictactoe.core.player;

import java.util.HashSet;
import java.util.Set;

public class HashSetActivePlayers implements ActivePlayers {
  private final Set<String> xPlayers;
  private final Set<String> oPlayers;

  public HashSetActivePlayers() {
    this.xPlayers = new HashSet<>();
    this.oPlayers = new HashSet<>();
  }

  @Override
  public boolean isPlaying(String playerId) {
    return xPlayers.contains(playerId) || oPlayers.contains(playerId);
  }

  @Override
  public void startAGameAsX(String playerId) {
    xPlayers.add(playerId);
  }

  @Override
  public void startAGameAsO(String playerId) {
    oPlayers.add(playerId);
  }

  @Override
  public boolean isXPlayer(String playerId) {
    return xPlayers.contains(playerId);
  }
}
