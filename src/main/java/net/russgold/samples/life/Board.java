package net.russgold.samples.life;

/**
 * Describes a board for the game of Conways' Life. Each cell may be either set (alive) or unset (dead).
 */
public interface Board {

  /**
   * Returns true if the board has no cells set.
    */
  boolean isEmpty();

  /**
   * Returns the number of cells in the board that are set.
   */
  int numCellsSet();

  /**
   * Sets the specified cell.
   * @param x the horizontal coordinate
   * @param y the vertical coordinate
   */
  void setCell(int x, int y);

  /**
   * Clears the specified cell.
   * @param x the horizontal coordinate
   * @param y the vertical coordinate
   */
  void clearCell(int x, int y);

  /**
   * Sets a group of cells according to the pattern in a string. Starting at the specified position,
   * this will set cells corresponding to non-space characters. Newline characters allow cells
   * on multiple lines to be set.
   * @param x the starting horizontal coordinate
   * @param y the starting vertical coordinate
   * @param cellPattern a string which defines the cells to set
   */
  void setCells(int x, int y, String cellPattern);

  /**
   * Returns true if the specified cell in the board is set.
   * @param x the horizontal coordinate
   * @param y the vertical coordinate
   */
  boolean isSet(int x, int y);

  /**
   * Returns the number of neighbors of a cell, counting diagonals.
   * @param x the horizontal coordinate
   * @param y the vertical coordinate
   */
  int numNeighbors(int x, int y);

  /**
   * Returns the X-coordinate of the furthest-left set cell.
   */
  int getMinX();

  /**
   * Returns the X-coordinate of the furthest-right set cell.
   */
  int getMaxX();

  /**
   * Returns the Y-coordinate of the highest set cell.
   */
  int getMinY();

  /**
   * Returns the Y-coordinate of the lowest set cell.
   */
  int getMaxY();

  /**
   * Creates the next generation of the board, using the rules of Conway's Life:
   * 1) any live (set) cell survives if it has two or three neighbors
   * 2) any dead (unset) cell is born if it has exactly three neighbors
   * 3) all other live cells die and other dead cells remain dead.
   */
  Board nextGeneration();
}
