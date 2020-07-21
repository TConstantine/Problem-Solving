package constantine.theodoridis.problemsolving.percolation;

public class Percolation {

  private final boolean[][] sites;

  public Percolation(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException();
    }
    sites = new boolean[size][size];
  }

  public void open(int row, int column) {
    validateIndices(row, column);
    sites[row - 1][column - 1] = true;
  }

  public boolean isOpen(int row, int column) {
    validateIndices(row, column);
    return sites[row - 1][column - 1];
  }

  public boolean isFull(int row, int column) {
    validateIndices(row, column);
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public int numberOfOpenSites() {
    return 0;
  }

  private void validateIndices(int row, int column) {
    if (row <= 0 || row > sites.length || column <= 0 || column > sites.length) {
      throw new IllegalArgumentException();
    }
  }

}
