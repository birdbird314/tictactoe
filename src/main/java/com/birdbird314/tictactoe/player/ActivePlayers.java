package com.birdbird314.tictactoe.player;

public interface ActivePlayers {
  boolean isPlaying(String playerId);

  void startAGameAsX(String playerId);

  void startAGameAsO(String playerId);

  boolean isXPlayer(String playerId);
}
