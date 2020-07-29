package constantine.theodoridis.problemsolving.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final WeightedQuickUnionUF sites;
  private final boolean[][] isOpen;
  private int numberOfOpenSites;

  public Percolation(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException();
    }
    sites = new WeightedQuickUnionUF(size * size + 2);
    isOpen = new boolean[size][size];
  }

  public void open(int row, int column) {
    validateIndices(row, column);
    if (!isOpen(row, column)) {
      isOpen[row - 1][column - 1] = true;
      numberOfOpenSites++;
      connectWithTopSite(row, column);
      connectWithBottomSite(row, column);
      connectWithTopNeighbor(row, column);
      connectWithLeftNeighbor(row, column);
      connectWithRightNeighbor(row, column);
      connectWithBottomNeighbor(row, column);
    }
  }

  public boolean isOpen(int row, int column) {
    validateIndices(row, column);
    return isOpen[row - 1][column - 1];
  }

  public boolean isFull(int row, int column) {
    validateIndices(row, column);
    return sites.find(getIndex(row, column)) == sites.find(getSize());
  }

  public int numberOfOpenSites() {
    return numberOfOpenSites;
  }

  public boolean percolates() {
    return sites.find(getSize() + 1) == sites.find(getSize());
  }

  private void validateIndices(int row, int column) {
    if (row <= 0 || row > isOpen.length || column <= 0 || column > isOpen.length) {
      throw new IllegalArgumentException();
    }
  }

  private void connectWithTopSite(int row, int column) {
    if (isInTopRow(row)) {
      sites.union(getIndex(row, column), getSize());
    }
  }

  private void connectWithBottomSite(int row, int column) {
    if (isInBottomRow(row)) {
      sites.union(getIndex(row, column), getSize() + 1);
    }
  }

  private void connectWithTopNeighbor(int row, int column) {
    if (!isInTopRow(row) && isOpen(row - 1, column)) {
      sites.union(getIndex(row, column), getIndex(row - 1, column));
    }
  }

  private void connectWithLeftNeighbor(int row, int column) {
    if (column != 1 && isOpen(row, column - 1)) {
      sites.union(getIndex(row, column), getIndex(row, column - 1));
    }
  }

  private void connectWithRightNeighbor(int row, int column) {
    if (column != isOpen.length && isOpen(row, column + 1)) {
      sites.union(getIndex(row, column), getIndex(row, column + 1));
    }
  }

  private void connectWithBottomNeighbor(int row, int column) {
    if (!isInBottomRow(row) && isOpen(row + 1, column)) {
      sites.union(getIndex(row, column), getIndex(row + 1, column));
    }
  }

  private int getIndex(int row, int column) {
    return isOpen.length * (column - 1) + row - 1;
  }

  private int getSize() {
    return isOpen.length * isOpen.length;
  }

  private boolean isInTopRow(int row) {
    return row == 1;
  }

  private boolean isInBottomRow(int row) {
    return row == isOpen.length;
  }

}
