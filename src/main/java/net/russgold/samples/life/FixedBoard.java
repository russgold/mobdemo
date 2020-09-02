// Copyright (c) 2019, Oracle Corporation and/or its affiliates.  All rights reserved.
// Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

package net.russgold.samples.life;

import java.util.Arrays;

public class FixedBoard implements Board {
  final static int SIZE = 20;
  boolean[][] cells = new boolean[SIZE][];

  @Override
  public boolean isEmpty() {
    return numCellsSet() == 0;
  }

  @Override
  public int numCellsSet() {
    return Arrays.stream(cells).map(this::numCellsSet).reduce(0, Integer::sum);
  }

  private int numCellsSet(boolean[] cells) {
    if (cells == null) return 0;

    int count = 0;
    for (boolean cell: cells)
      if (cell) count++;

    return count;
  }

  @Override
  public boolean isSet(int x, int y) {
    return inRange(x, y) && getColumn(x)[y];
  }

  private boolean inRange(int x, int y) {
    return inRange(x) && inRange(y);
  }

  private boolean inRange(int i) {
    return 0 <= i && i < SIZE;
  }

  @Override
  public int numNeighbors(int x, int y) {
    return value(x-1,y-1) + value(x-1, y) + value(x-1, y+1)
          + value(x, y-1) + value(x, y+1)
          + value(x+1, y-1) + value(x+1, y) + value(x+1, y+1);
  }

  private int value(int x, int y) {
    return isSet(x,y) ? 1 : 0;
  }

  @Override
  public void setCell(int x, int y) {
    setCell(x, y, true);
  }

  private void setCell(int x, int y, boolean state) {
    getColumn(x)[y] = state;
  }

  @Override
  public void setCells(int x, int y, String cellPattern) {
    final String[] rows = cellPattern.split("\n");
    for (int i = 0; i < rows.length; i++) {
      setCellsInRow(x, y+i, rows[i]);
    }
  }

  private void setCellsInRow(int x, int y, String cellPattern) {
    for (char c : cellPattern.toCharArray()) {
      setCell(x++, y, c != ' ');
    }
  }

  @Override
  public void clearCell(int x, int y) {
    setCell(x, y, false);
  }

  private boolean[] getColumn(int x) {
    if (cells[x] == null) cells[x] = new boolean[SIZE];
    return cells[x];
  }

  @Override
  public int getMinX() {
    for (int x = 0; x < SIZE; x++) {
      if (hasSetCells(getColumn(x))) return x;
    }
    return 0;
  }

  private boolean hasSetCells(boolean[] cells) {
    if (cells == null) return false;

    for (boolean cell : cells)
      if (cell) return true;

    return false;
  }

  @Override
  public int getMaxX() {
    for (int x = SIZE-1; x >= 0; x--) {
      if (hasSetCells(getColumn(x))) return x;
    }
    return -1;
  }

  @Override
  public int getMinY() {
    int result = SIZE;
    for (int x = 0; x < SIZE; x++) {
      result = Math.min(result, getFirst(x));
    }
    return result == SIZE ? 0 : result;
  }

  private int getFirst(int x) {
    final boolean[] column = getColumn(x);
    for (int y = 0; y < SIZE; y++) {
      if (column[y]) return y;
    }

    return SIZE;
  }

  @Override
  public int getMaxY() {
    int result = -1;
    for (int x = 0; x < SIZE; x++) {
      result = Math.max(result, getLast(x));
    }
    return result;
  }

  private int getLast(int x) {
    final boolean[] column = getColumn(x);
    for (int y = SIZE-1; y >= 0; y--) {
      if (column[y]) return y;
    }
    return -1;
  }

  @Override
  public Board nextGeneration() {
    final FixedBoard newBoard = new FixedBoard();
    for (int i = getMinX()-1; i <= getMaxX() + 1; i++) {
      for (int j = getMinY() - 1; j <= getMaxY() + 1; j++) {
        newBoard.setCell(i, j, shouldLive(i, j));
      }
    }
    return newBoard;
  }

  private boolean shouldLive(int x, int y) {
    if (isSet(x, y)) {
      return shouldSurvive(numNeighbors(x, y));
    } else {
      return shouldBeBorn(numNeighbors(x, y));
    }
  }

  private boolean shouldSurvive(int numNeighbors) {
    return numNeighbors == 2 || numNeighbors == 3;
  }

  private boolean shouldBeBorn(int numNeighbors) {
    return numNeighbors == 3;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        sb.append(isSet(i,j) ? 'X' : '.');
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
