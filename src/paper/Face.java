package paper;

import java.awt.Polygon;
import java.util.*;

public class Face extends Polygon {
    private List<Edge> edges;

    public Face(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);

        edges = new ArrayList<>();
        for (int i = 0; i < npoints; i++) {
            edges.add(new Edge(xpoints[i % npoints], ypoints[i % npoints],
                    xpoints[(i + 1) % npoints], ypoints[(i +1) % npoints], EdgeType.EDGE));
        }
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
