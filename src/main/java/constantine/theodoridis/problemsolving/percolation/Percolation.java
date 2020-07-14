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
    if (row <= 0 || row > size || column <= 0 || column > size) {
      throw new IllegalArgumentException();
    }
  }

}
