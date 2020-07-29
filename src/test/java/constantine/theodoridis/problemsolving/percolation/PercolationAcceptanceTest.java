package constantine.theodoridis.problemsolving.percolation;

import edu.princeton.cs.algs4.In;
import java.io.File;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class PercolationAcceptanceTest {

  private static final String PATH = "percolation/";

  private Percolation percolation;

  @Test
  @Parameters(method = "fileNames")
  public void shouldSimulatePercolationProcessFromFile(String fileName) {
    final In in = new In(readFile(fileName));
    percolation = new Percolation(in.readInt());
    assertThat(percolation.numberOfOpenSites(), is(0));
    assertThat(percolation.percolates(), is(false));

    while (!in.isEmpty()) {
      final int row = in.readInt();
      final int column = in.readInt();
      percolation.open(row, column);

      assertThat(percolation.isOpen(row, column), is(true));
      assertThat(percolation.isFull(row, column), is(in.readBoolean()));
      assertThat(percolation.numberOfOpenSites(), is(in.readInt()));
      assertThat(percolation.percolates(), is(in.readBoolean()));
    }
  }

  private File readFile(String fileName) {
    return new File(getClass().getClassLoader().getResource(PATH + fileName).getFile());
  }

  private Object fileNames() {
    return new Object[]{
      "input1-no.txt",
      "input1.txt",
      "input10-no.txt",
      "input10.txt",
      "input2-no.txt",
      "input2.txt",
      "input3.txt",
      "input4.txt",
      "input5.txt",
      "input6.txt",
      "input7.txt",
      "input8-dups.txt",
      "input8-no.txt",
      "input8.txt"
    };
  }

}
