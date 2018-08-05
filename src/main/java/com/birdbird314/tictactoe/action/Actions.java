package com.birdbird314.tictactoe.action;

import com.birdbird314.tictactoe.GameStartFail;
import com.birdbird314.tictactoe.util.Command;
import com.birdbird314.tictactoe.util.Unit;

import java.util.HashSet;
import java.util.Set;

public class Actions {
  private final Set<String> players = new HashSet<>();

  public Command<GameStartFail, Unit> newGame(String xPlayerId, String oPlayerId) {
    return new NewGame(xPlayerId, oPlayerId, players);
  }
}
