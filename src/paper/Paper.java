package paper;

import java.util.ArrayList;
import java.util.List;

public class Paper {
    private List<Face> faces;
    private int height;
    private int width;

    public Paper(int height, int width) {
        this.height = height;
        this.width = width;

        /* A sheet of paper is represented by the half to the right of the line of symmetry along
        * the y-axis, therefore, width is halved. */
        faces = new ArrayList<>();
        int[] xpoints = {0, width / 2, width / 2, 0};
        int[] ypoints = {0, 0, height, height};
        faces.add(new Face(xpoints, ypoints, 4));
    }

    /* Given a folding edge, check for all faces that contain all or part of the edge. In the case
     * that a face contains the edge, create new vertices in the face at the intersections of the
     * folding edge and the edges of the face. Also, construct a new face that shares the folding
     * edge with the face and that has vertices reflected from the original edge, across the folding
     * edge, to the new face. */
    /* Mind pre-existing shared edges. Assume that a fold only */
    public void fold(Edge foldingEdge, boolean isMountainFold) {
        for (Face face : faces) {
            for (Edge edge : face.getEdges()) {
                if (foldingEdge.intersectsLine(edge.getX1(), edge.getY1(),
                        edge.getX2(), edge.getY2())) {
                    //terrible code written by Nicholas Freitas; the rest of the beautiful stuff is Samantha

                    int[] point1 = getIntersect(edge, foldingEdge);
                }
            }
        }
    }

    public int[] getIntersect(Edge edge, Edge foldingEdge){
        //this one's on me too, sorry Samantha
        //less time than sleep
        //less sleep than coffee
        //less coffee than comments
        double x1 = edge.getX1();
        double x2 = edge.getX2();
        double x3 = foldingEdge.getX1();
        double x4 = foldingEdge.getX2();
        double y1 = edge.getY1();
        double y2 = edge.getY2();
        double y3 = foldingEdge.getY1();
        double y4 = foldingEdge.getY2();
        double m1 = (y2 - y1 / x2 - x1);
        double m2 = (y4 - y3 / x4 - x3);

        double intersectX1 = (m1 * x1 - m2 * x3 - y1 + y3) / (m1 - m2);
        double intersectY1 = (m1) * (intersectX1 - x1) + y1;

        int[] point = new int[2];
        point[0] = (int)intersectX1;
        point[1] = (int)intersectY1;

        return point;
    }
    /* Returns a list of polygons that share the given edge. */
    private List<Face> findCommonEdges(Edge targetEdge) {
        List<Face> sharedEdge = new ArrayList<>();
        for(Face thisFace : faces){
            for(Edge thisEdge : thisFace.getEdges()){
                if(targetEdge == thisEdge){
                    sharedEdge.add(thisFace);
                }

            }
        }
        return sharedEdge;
    }
}
