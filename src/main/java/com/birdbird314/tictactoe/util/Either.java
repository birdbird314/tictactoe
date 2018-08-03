package com.birdbird314.tictactoe.util;

/**
 * This is similar to Either in Scala, but much simpler
 * @param <L> type of 'Left' value, by convention used when something goes wrong
 * @param <R> type of 'Right' value, by convention used when everything is 'Right'
 */
public interface Either<L, R> {
  /**
   * @return result of failed computation if exists, otherwise throws UnsupportedOperationException
   */
  default L left() {
    throw new UnsupportedOperationException();
  }

  /**
   * @return result of successful computation if exists, otherwise throws UnsupportedOperationException
   */
  default R right() {
    throw new UnsupportedOperationException();
  }

  class Left<L, R> implements Either<L, R> {
    private final L l;

    public Left(L l) {
      this.l = l;
    }

    @Override
    public L left() {
      return l;
    }
  }

  class Right<L, R> implements Either<L, R> {
    private final R r;

    public Right(R r) {
      this.r = r;
    }

    @Override
    public R right() {
      return r;
    }
  }
}
