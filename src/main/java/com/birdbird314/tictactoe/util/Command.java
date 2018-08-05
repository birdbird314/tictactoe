package com.birdbird314.tictactoe.util;

public interface Command<L, R> {
  Either<L, R> execute();
}
