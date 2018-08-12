package com.birdbird314.tictactoe.player;

public interface ActivePlayers {
  boolean isPlaying(String playerId);

  void aGameStartedFor(String playerId);
}
