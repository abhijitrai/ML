package graph;

import java.util.ArrayList;
import java.util.List;

public class PrimsAlgo {


    public static void main(String[] args) {

        /**
         *
         *                2
         *            0<------ 1
         *            | ^ 6   ^
         *           3|   \ /7
         *            v   /\
         *            2 ----> 3
         *               1
         *
         */
        int[][] dEdges = {{0, 2, 3}, {1, 0, 2}, {3, 0, 6}, {2, 1, 7}, {2, 3, 1}};


        /*
         *
         *                                  5
         *                             (2)----- (3)
         *                          7/  | \2    /
         *                          /  4|  \   /2
         *                         (1)  |   (4)
         *                         |\9  |  3/ |
         *                         | \  |  /  |
         *                        5|   (5)   /7
         *                         | 7/  4\ /
         *                         (0)----(6)
         *                            12
         *
         *
         * */
        int[][] edges = {{0, 5, 7}, {0, 6, 12}, {6, 4, 7}, {4, 3, 2}, {5, 6, 4}, {5, 4, 3}, {0, 1, 5}, {1, 2, 7}, {2, 3, 5}, {4, 2, 2}, {5, 2, 4}, {1, 5, 9}};
        Graph g = new Graph(7, edges, false);
        System.out.println(g);
        new PrimsAlgo().prims(g, 1);
    }

    List<Integer> list = new ArrayList();
    int[] discovered;
    List<int[]> edges = new ArrayList<>();

    void prims(Graph g, int x) {
        discovered = new int[g.edgeArr.length];
        for (int i = 0; i < discovered.length; i++) {
            discovered[i] = -1;
        }
        list.add(x);
        discovered[x] = x;
        boolean discoveredNew = true;
        while (discoveredNew) {
            discoveredNew = false;
            int min = Integer.MAX_VALUE;
            int y = Integer.MAX_VALUE;
            int parent = -1;
            for (int i = 0; i < list.size(); i++) {
                int v = list.get(i);
                System.out.print("\nNode : " + v);
                EdgeNode e = g.edgeArr[v];
                while (e != null) {
                    System.out.print(" -> {" + e.y + " [" + e.w + "] } " );
                    if (discovered[e.y] == -1 && e.w < min) {
                        System.out.print("( * " + e.y + " [" + e.w + "])");
                        min = e.w;
                        y = e.y;
                        parent = v;
                        discoveredNew = true;
                    }
                    e = e.next;
                }
            }
            if (discoveredNew) {
                System.out.println("\nAdded -> " + y + "[" + min + "]" + " Parent : " + parent);
                discovered[y] = parent;
                edges.add(new int[]{parent, y, min});
                list.add(y);
            }
        }

        int[][] edgeArr = new int[edges.size()][];
        int count = 0 ;
        for (int i = 0; i < edges.size(); i++) {
            edgeArr[i] = edges.get(i);
            count = count + edges.get(i)[2];
        }
        System.out.print("\nWeight of spanning tree : " + count + " \t branches : "+ edges.size());
        System.out.println("\n\n" + new Graph(discovered.length, edgeArr, true));

    }


    static private class Graph {

        EdgeNode[] edgeArr;

        Graph(int v, int[][] edges, boolean directed) {
            edgeArr = new EdgeNode[v];
            for (int i = 0; i < edges.length; i++) {
                if (edges[i] != null)
                    insertEdge(edges[i][0], edges[i][1], edges[i][2], directed);
            }
        }

        void insertEdge(int x, int y, int w, boolean directed) {
            EdgeNode cur = edgeArr[x];
            EdgeNode newEdge = new EdgeNode(y, w);
            if (cur == null) {
                edgeArr[x] = newEdge;
            } else {
                newEdge.next = edgeArr[x];
                edgeArr[x] = newEdge;
            }
            if (!directed) {
                insertEdge(y, x, w, true);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < edgeArr.length; i++) {
                sb.append("Node " + i + " : ");
                EdgeNode cur = edgeArr[i];
                while (cur != null) {
                    sb.append(" -> " + cur.y + "(" + cur.w + ")");
                    cur = cur.next;
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    static private class EdgeNode {
        EdgeNode(int y, int w) {
            this.y = y;
            this.w = w;
        }

        int y;
        int w;
        EdgeNode next;
    }
}
