package constantine.theodoridis.problemsolving.percolation;

public class Percolation {

  private final int size;

  public Percolation(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException();
    }
    this.size = size;
  }

  public void open(int row, int column) {
    validateIndices(row, column);
  }

  public boolean isOpen(int row, int column) {
    validateIndices(row, column);
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public boolean isFull(int row, int column) {
    validateIndices(row, column);
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private void validateIndices(int row, int column) {
    if (row <= 0 || row > size || column <= 0 || column > size) {
      throw new IllegalArgumentException();
    }
  }

}
