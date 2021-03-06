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

  @Test
  public void shouldNotHaveFullSite_WhenSiteIsNotOpen() {
    percolation = new Percolation(3);

    assertThat(percolation.isFull(2, 2), is(false));
  }

  @Test
  public void shouldNotHaveFullSite_WhenSiteIsOpenAndIsNotConnectedWithAnOpenSiteInTopRow() {
    percolation = new Percolation(3);
    percolation.open(1, 1);
    percolation.open(2, 2);

    assertThat(percolation.isFull(2, 2), is(false));
  }

  @Test
  public void shouldHaveFullSite_WhenSiteIsOpenAndIsConnectedWithAnOpenSiteInTopRow() {
    percolation = new Percolation(3);
    percolation.open(1, 2);
    percolation.open(2, 2);

    assertThat(percolation.isFull(2, 2), is(true));
  }

  @Test
  public void shouldHaveFullSite_WhenSiteIsOpenAndIsConnectedWithAnOpenSiteInTopRowThroughLeftNeighbor() {
    percolation = new Percolation(3);
    percolation.open(1, 1);
    percolation.open(2, 1);
    percolation.open(2, 2);

    assertThat(percolation.isFull(2, 2), is(true));
  }

  @Test
  public void shouldHaveFullSite_WhenSiteIsOpenAndIsConnectedWithAnOpenSiteInTopRowThroughRightNeighbor() {
    percolation = new Percolation(3);
    percolation.open(1, 3);
    percolation.open(2, 3);
    percolation.open(2, 2);

    assertThat(percolation.isFull(2, 2), is(true));
  }

  @Test
  @Parameters(method = "sizes")
  public void shouldIncreaseNumberOfOpenSites_WhenSiteIsOpened(int size) {
    percolation = new Percolation(size);

    percolation.open(size, size);

    assertThat(percolation.numberOfOpenSites(), is(1));
  }

  @Test
  @Parameters(method = "sizes")
  public void shouldNotIncreaseNumberOfOpenSites_WhenOpenSiteIsOpened(int size) {
    percolation = new Percolation(size);
    percolation.open(size, size);

    percolation.open(size, size);

    assertThat(percolation.numberOfOpenSites(), is(1));
  }

  @Test
  public void shouldNotPercolate_WhenNoFullSiteExistsInBottomRow() {
    percolation = new Percolation(3);
    percolation.open(1, 2);
    percolation.open(2, 2);

    assertThat(percolation.percolates(), is(false));
  }

  @Test
  public void shouldPercolate_WhenFullSiteExistsInBottomRow() {
    percolation = new Percolation(3);
    percolation.open(1, 2);
    percolation.open(2, 2);
    percolation.open(3, 2);

    assertThat(percolation.percolates(), is(true));
  }

  @Test
  public void shouldNotBackwash() {
    percolation = new Percolation(3);
    percolation.open(1, 2);
    percolation.open(2, 2);
    percolation.open(2, 3);
    percolation.open(3, 1);
    percolation.open(3, 3);

    assertThat(percolation.percolates(), is(true));
    assertThat(percolation.isFull(3, 1), is(false));
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
