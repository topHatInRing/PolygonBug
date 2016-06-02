package com.topHatInRing.polygonBug;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Main.class.getClassLoader().getResourceAsStream("points.txt")))) {
            int[] xs = parsePointLine(br.readLine());
            int[] ys = parsePointLine(br.readLine());
            br.close();

            assert(xs.length == ys.length);
            java.awt.Polygon awtPolygon = new java.awt.Polygon(xs, ys, xs.length);
            com.topHatInRing.polygonBug.Polygon fixedPolygon = new com.topHatInRing.polygonBug.Polygon(xs, ys,
                    xs.length);

            int x = -90000000;
            int y = 387436214;
            
            // Test on either side of the point.
            boolean doesAwtPolygonContainPoint = awtPolygon.contains(-89000000, y);
            if (doesAwtPolygonContainPoint == false) {
                System.err.println("Say What! awtPolygon does not contain point on the side.");
            }
            doesAwtPolygonContainPoint = awtPolygon.contains(-91000000, y);
            if (doesAwtPolygonContainPoint == false) {
                System.err.println("Say What! awtPolygon does not contain point on the side.");
            }
 
            // This one fails because of the bug.
            doesAwtPolygonContainPoint = awtPolygon.contains(x, y);
            if (doesAwtPolygonContainPoint == false) {
                System.err.println("Say What! awtPolygon does not contain point in the middle.");
            }

            boolean doesFixedPolygonContainPoint = fixedPolygon.contains(x, y);
            
            System.out.println(String.format("doesAwtPolygonContainPoint=%b doesFixedPolygonContainPoint=%b",
                    doesAwtPolygonContainPoint, doesFixedPolygonContainPoint));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[] parsePointLine(String line) {
        StringTokenizer st = new StringTokenizer(line, " ");
        int[] points = new int[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            points[i++] = Integer.parseInt(st.nextToken());
        }
        return points;
    }

}
