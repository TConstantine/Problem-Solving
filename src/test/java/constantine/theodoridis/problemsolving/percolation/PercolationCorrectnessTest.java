package constantine.theodoridis.problemsolving.percolation;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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
  @Parameters(method = "invalidArguments")
  public void shouldThrowException_WhenOpenIsCalledWithInvalidArgument(int size, int row, int column) {
    percolation = new Percolation(size);

    percolation.open(row, column);
  }

  @Test(expected = IllegalArgumentException.class)
  @Parameters(method = "invalidArguments")
  public void shouldThrowException_WhenIsOpenIsCalledWithInvalidArgument(int size, int row, int column) {
    percolation = new Percolation(size);

    percolation.isOpen(row, column);
  }

  @Test
  @Parameters(method = "sizes")
  public void shouldHaveAllSitesBlockedInitially(int size) {
    percolation = new Percolation(size);

    for (int row = 1; row <= size; row++) {
      for (int column = 1; column <= size; column++) {
        assertThat(percolation.isOpen(row, column), is(false));
      }
    }
    assertThat(percolation.numberOfOpenSites(), is(0));
  }

  @Test
  @Parameters(method = "sizes")
  public void shouldOpenGivenSite(int size) {
    percolation = new Percolation(size);

    for (int row = 1; row <= size; row++) {
      for (int column = 1; column <= size; column++) {
        percolation.open(row, column);

        assertThat(percolation.isOpen(row, column), is(true));
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  @Parameters(method = "invalidArguments")
  public void shouldThrowException_WhenIsFullIsCalledWithInvalidArgument(int size, int row, int column) {
    percolation = new Percolation(size);

    percolation.isFull(row, column);
  }

  private Object invalidArguments() {
    return new Object[]{
      "10, -1, 5",
      "10, 11, 5",
      "10, 0, 5",
      "10, 5, -1",
      "10, 5, 11",
      "10, 5, 0",
      "10, -2147483648, -2147483648",
      "10, 2147483647, 2147483647"
    };
  }

  private Object sizes() {
    return new Object[]{"1", "2", "5", "10", "25", "50", "100"};
  }

}
