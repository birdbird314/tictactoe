package com.birdbird314.tictactoe.action;

import com.birdbird314.tictactoe.player.ActivePlayers;
import com.birdbird314.tictactoe.util.Command;
import com.birdbird314.tictactoe.util.Unit;

public class Actions {
  private final ActivePlayers players;

  public Actions(ActivePlayers players) {
    this.players = players;
  }

  public Command<GameStartFail, Unit> newGame(String xPlayerId, String oPlayerId) {
    return new NewGame(xPlayerId, oPlayerId, players);
  }
}
