// Copyright (c) 2019, Oracle Corporation and/or its affiliates.  All rights reserved.
// Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

package net.russgold.samples.life;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
  private final Board board = new FixedBoard();

  @Test
  void whenCreated_boardIsEmpty() {
    assertTrue(board.isEmpty());
  }

  @Test
  void whenCreated_selectedCellsAreUnset() {
    assertFalse(board.isSet(0, 5));
    assertFalse(board.isSet(3, 1));
    assertFalse(board.isSet(11, 18));
  }

  @Test
  void whenCreatedReturnEmptyBounds() {
    assertThat(board.getMinX()).isEqualTo(0);
    assertThat(board.getMaxX()).isEqualTo(-1);
    assertThat(board.getMinY()).isEqualTo(0);
    assertThat(board.getMaxY()).isEqualTo(-1);
  }

  @Test
  void afterCellSet_boardIsNotEmpty() {
    board.setCell(1,5);

    assertFalse(board.isEmpty());
  }

  @Test
  void afterCellSet_otherCellsNotSet() {
    board.setCell(1,5);

    assertFalse(board.isSet(5, 1));
  }

  @Test
  void cellsOutOfRange_areNotSet() {
    board.setCell(1,5);

    assertFalse(board.isSet(-1, 0));
    assertFalse(board.isSet(7, -3));
    assertFalse(board.isSet(30, 4));
    assertFalse(board.isSet(18, 20));
  }

  @Test
  void afterCellsSet_recognizeState() {
    board.setCell(1,5);
    board.setCell(18,0);
    board.setCell(7,3);

    assertTrue(board.isSet(1, 5));
    assertTrue(board.isSet(18, 0));
    assertTrue(board.isSet(7, 3));
  }

  @Test
  void afterCellsSet_computeMinAndMax() {
    board.setCell(1,5);
    board.setCell(18,11);
    board.setCell(7,3);

    assertThat(board.getMinX()).isEqualTo(1);
    assertThat(board.getMaxX()).isEqualTo(18);
    assertThat(board.getMinY()).isEqualTo(3);
    assertThat(board.getMaxY()).isEqualTo(11);
  }

  @Test
  void afterCellSet_canClearIt() {
    board.setCell(1,5);
    board.setCell(18,11);
    board.setCell(7,3);
    board.clearCell(7, 3);

    assertThat(board.isSet(7, 3)).isFalse();
    assertThat(board.getMinY()).isEqualTo(5);
  }

  @Test
  void afterCellsSetAndCleared_countSetCells() {
    board.setCell(1,5);
    board.setCell(18,11);
    board.setCell(7,3);
    board.clearCell(7, 3);

    assertThat(board.numCellsSet()).isEqualTo(2);
  }

  @Test
  void setMultipleCellsFromString_singleLine() {
    board.setCells(9, 10, "XXX");

    assertThat(board.numCellsSet()).isEqualTo(3);
    assertTrue(board.isSet(9, 10));
    assertTrue(board.isSet(10, 10));
    assertTrue(board.isSet(11, 10));
  }

  @Test
  void setMultipleCellsFromString_singleLineWithSpaces() {
    board.setCells(9, 10, "X X");

    assertThat(board.numCellsSet()).isEqualTo(2);
    assertTrue(board.isSet(9, 10));
    assertFalse(board.isSet(10, 10));
    assertTrue(board.isSet(11, 10));
  }

  @Test
  void setMultipleCellsFromString_multipleLines() {
    board.setCells(8, 9, "XX\nXX\n  XX\n  XX");

    assertThat(board.numCellsSet()).isEqualTo(8);
    assertTrue(board.isSet(8, 9));
    assertTrue(board.isSet(8, 10));
    assertTrue(board.isSet(9, 9));
    assertTrue(board.isSet(9, 10));
    assertFalse(board.isSet(10, 9));
    assertTrue(board.isSet(10, 11));
    assertTrue(board.isSet(10, 12));
    assertTrue(board.isSet(11, 11));
    assertTrue(board.isSet(11, 12));
  }

  @Test
  void computeNeighbors() {
    board.setCells(8, 9, "XX\nXX\n  XX\n  XX");

    assertThat(board.numNeighbors(8, 12)).isEqualTo(0);
    assertThat(board.numNeighbors(7, 8)).isEqualTo(1);
    assertThat(board.numNeighbors(7, 9)).isEqualTo(2);
    assertThat(board.numNeighbors(11, 11)).isEqualTo(3);
    assertThat(board.numNeighbors(10, 10)).isEqualTo(4);
  }

  @Test
  void createNextGeneration() {
    board.setCells(9, 10, "XXX");

    Board board2 = board.nextGeneration();
    assertThat(board2.numCellsSet()).isEqualTo(3);
    assertTrue(board2.isSet(10, 9));
    assertTrue(board2.isSet(10, 10));
    assertTrue(board2.isSet(10, 11));
  }
}
