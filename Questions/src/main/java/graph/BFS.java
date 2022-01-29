package graph;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class BFS {

    Queue<Integer> queue = new ConcurrentLinkedQueue<>();
    int[] discovered;

    String walk(Graph g, int startIdx, int z) {
        boolean found = false;
        discovered = new int[g.edgeArr.length];
        for (int i = 0; i < g.edgeArr.length; i++) {
            discovered[i] = -1;
        }
        EdgeNode edge;
        queue.add(startIdx);

        while (!queue.isEmpty()) {
            int x = queue.remove();
            edge = g.edgeArr[x];
            while (edge != null) {
//                System.out.println("Processing  " + x + " Edge " + edge);
                if (discovered[edge.y] == -1 && startIdx != edge.y) {
//                    System.out.println("Parent " + x +" Discovered " + edge.y);
                    queue.add(edge.y);
                    discovered[edge.y] = x;
                    if (edge.y == z) {
//                        System.out.println("Found the Node : " + z );
                        found = true;
                        break;
                    }
                }
                edge = edge.next;
            }
            if (found) {
                break;
            }
        }
        int count = 0;
        String result = "Not Found";
        if (found) {
            StringBuilder sb = new StringBuilder();
            sb.append(" -> " + z);
            int parent = discovered[z];
//            System.out.println("Back Tracking Started : " + parent);
            while (parent != -1) {
                sb.append(" -> " + parent);
                parent = discovered[parent];
                count++;
            }
            sb.append(" Count : " + count);
            result = sb.toString();
        }

        return result;
    }

    /*
     *                       9    3    1         10
     *                        \  /  \ / \      /
     *                         8 -  0 -  2    6
     *                          \  /    / \  /
     *                           4     5   7
     *
     *
     * */

    public static void main(String[] args) {
        int[][] edges = {{0, 1},{ 0,8}, {2, 5}, {2, 7}, {7, 6}, {6, 10}, {0, 3}, {0, 4},  {3, 8}, {4, 8}, {8, 9}, { 0,2},  {1, 2}};
        Graph graph = new Graph(11, edges, false);
        System.out.println(graph);
        System.out.println(new BFS().walk(graph, 9, 10));
    }


    static class Graph {

        EdgeNode[] edgeArr;

        public Graph(int v, int[][] edges, boolean directed) {
            edgeArr = new EdgeNode[v];
            for (int i = 0; i < edges.length; i++) {
                insertEdge(edges[i][0], edges[i][1], directed);
            }

        }

        private void insertEdge(int x, int y, boolean directed) {
            EdgeNode edge = new EdgeNode(y);
            if (edgeArr[x] == null) {
                edgeArr[x] = edge;
            } else {
                EdgeNode temp = edgeArr[x];
                edgeArr[x] = edge;
                edge.next = temp;
            }
            if (!directed) {
                insertEdge(y, x, true);
            }

        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < edgeArr.length; i++) {
                sb.append(" Node :" + i + " ");
                EdgeNode edge = edgeArr[i];
                while (edge != null) {
                    sb.append(" -> " + edge.y);
                    edge = edge.next;
                }
                sb.append("\n");
            }

            return sb.toString();
        }
    }

    static class EdgeNode {
        EdgeNode(int y) {
            this.y = y;
        }

        int y;
        EdgeNode next;

        public String toString() {

            return " " + y;
        }
    }

}
