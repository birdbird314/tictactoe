package com.birdbird314.tictactoe.core.util;

public interface Command<L, R> {
  Either<L, R> execute();
}
