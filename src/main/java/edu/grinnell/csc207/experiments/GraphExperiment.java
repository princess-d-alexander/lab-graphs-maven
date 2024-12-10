package edu.grinnell.csc207.experiments;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.Graph;

/**
 * A quick experiment with graphs.
 *
 * @author Princess Alexander
 * @author Samuel A. Rebelsky
 */
public class GraphExperiment {

  /**
   * Run our experiments.
   *
   * @param args
   *   Command-line arguments (ignored).
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    // A small graph so that we can force it to expand.
    Graph g = new Graph(2);
    g.addVertex("a");
    g.addVertex("b");
    g.addVertex("c");
    g.dump(pen);

    // Add a few edges
    g.addEdge("a", "b", 1);
    g.addEdge("a", "c", 2);
    g.addEdge("b", "c", 3);
    g.addEdge("b", "a", 4);
    g.dump(pen);

    // Remove a vertex
    g.removeVertex("b");
    g.dump(pen);

    // Add another vertex
    g.addVertex("d");
    g.addEdge("a", "d", 5);
    g.addEdge("d", "a", 6);
    g.dump(pen);

    // And another (hopefully, this will replace the old b)
    g.addVertex("e");
    g.addEdge("e", "a", 7);
    g.dump(pen);

    // And another (hopefully, this will expand the graph again)
    g.addVertex("f");
    g.addEdge("c", "f", 8);
    g.addEdge("f", "c", 9);
    g.dump(pen);

    // Add an invalid edge
    try {
      g.addEdge("c",  "g", 0);
      pen.println("Surprisingly, added an edge from c to nonexistant g");
    } catch (Exception e) {
      pen.println("Correctly failed to add an edge from c to g.");
    } // try/catch

    // Add/replace a bunch of edges
    for (int i = 1; i <= 4; i++) {
      g.addEdge(0, i, i * 10);
    } // for
    g.dump(pen);

    // Remove an edge
    g.removeEdge("a", "c");
    g.dump(pen);

  // Additional experiments

    // 1. Add edges with non-existent vertices
    try {
      g.addEdge("x", "y", 10);
    } catch (Exception e) {
      pen.println("Correctly failed to add edge with non-existent vertices.");
    }

    // 2. Test removing a non-existent vertex
    try {
      g.removeVertex("z");
      pen.println("Successfully removed non-existent vertex z");
    } catch (Exception e) {
      pen.println("Correctly failed to remove non-existent vertex z.");
    }

    // 3. Check for graph behavior with duplicate edges
    g.addEdge("a", "c", 15); // Adding a duplicate edge
    g.dumpWithNames(pen);

    // 4. Test adding an edge after removing a vertex
    g.removeVertex("a");
    try {
      g.addEdge("a", "f", 20);
    } catch (Exception e) {
      pen.println("Correctly failed to add edge after vertex a was removed.");
    }
    g.dumpWithNames(pen);

    // 5. Attempting to add an edge with invalid vertex format
    try {
      g.addEdge("1", "2", 25); // Numeric as vertex
      pen.println("Surprisingly, added edge with numeric vertices.");
    } catch (Exception e) {
      pen.println("Correctly failed to add edge with numeric vertices.");
    }

    // 6. Test removing edges that don't exist
    g.removeEdge("a", "f"); // Non-existent edge
    g.dumpWithNames(pen);
  } // main(String[])

} // class GraphExperiment
