package constantine.theodoridis.problemsolving.percolation;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class PercolationCorrectnessTest {

  private Percolation percolation;

  @Test(expected = IllegalArgumentException.class)
  @Parameters({
    "-10",
    "-1",
    "0"
  })
  public void shouldThrowException_WhenConstructorIsCalledWithInvalidArgument(int size) {
    percolation = new Percolation(size);
  }

  @Test(expected = IllegalArgumentException.class)
  @Parameters({
    "10, -1, 5",
    "10, 11, 5",
    "10, 0, 5",
    "10, 5, -1",
    "10, 5, 11",
    "10, 5, 0",
    "10, -2147483648, -2147483648",
    "10, 2147483647, 2147483647"
  })
  public void shouldThrowException_WhenOpenIsCalledWithInvalidArgument(int size, int row, int column) {
    percolation = new Percolation(size);

    percolation.open(row, column);
  }

}
