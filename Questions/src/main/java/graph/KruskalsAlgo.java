package graph;

import sort.MergeSort;

import java.util.ArrayList;

public class KruskalsAlgo {


    public static void main(String[] args) {
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
//        System.out.println(g);
        new KruskalsAlgo().mst(g);

    }

    int[] discovered;

    private static class Triplet {
        Triplet(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        int x, y, w;
    }

    void mst(Graph g) {
        ArrayList<Triplet> list = new ArrayList<>();
        for (int i = 0; i < g.edgeArr.length; i++) {
            EdgeNode e = g.edgeArr[i];
            discovered = new int[g.edgeArr.length];
            while (e != null) {
                list.add(new Triplet(i, e.y, e.w));
                e = e.next;
            }
        }
        int[][] edges = new int[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            edges[i] = new int[]{list.get(i).x, list.get(i).y, list.get(i).w};
            discovered[list.get(i).x] = -1 ;
        }

        int[][] sorted = new MergeSort().sort(edges, 0, edges.length - 1);
        list = new ArrayList<>( );
        int weight  = 0 ;
        for (int i = 0; i < sorted.length; i++) {
//            System.out.print(" " + sorted[i][0] + " " + sorted[i][1] + " " + sorted[i][2]);
//            System.out.println("");
             int x = sorted[i][0];
             int y = sorted[i][1];
             int w = sorted[i][2];
            if( discovered [y] == -1 ){
                discovered[y] = x;
                weight+=w;
                list.add(new Triplet(x,y,w));
            }

        }
        System.out.println( "Minimum Spanning tree : " + weight);
        System.out.println(" Edges : ");
        for( int i = 0 ;  i < list.size() ; i ++  ) {
            System.out.println(list.get(i).x+ " -> " + list.get(i).y + "[" + list.get(i).w+"]");
        }




    }


    private static class MergeSort {

        int[][] sort(int[][] arr, int startIdx, int endIdx) {
            if (endIdx < startIdx)
                return new int[0][0];
            if (endIdx == startIdx)
                return new int[][]{arr[startIdx]};
            int mid = (startIdx + endIdx) / 2;
            return merge(sort(arr, startIdx, mid), sort(arr, mid + 1, endIdx));


        }

        int[][] merge(int[][] arr1, int[][] arr2) {
            int idx1 = 0, idx2 = 0;
            int[][] combined = new int[arr1.length + arr2.length][3];
            for (int i = 0; i < arr1.length + arr2.length; i++) {
                if (idx1 < arr1.length && idx2 < arr2.length) {
                    if (arr1[idx1][2] < arr2[idx2][2]) {
                        combined[i] = arr1[idx1];
                        idx1++;
                    } else {
                        combined[i] = arr2[idx2];
                        idx2++;

                    }

                } else if (idx1 < arr1.length) {
                    combined[i] = arr1[idx1];
                    idx1++;

                } else if (idx2 < arr2.length) {
                    combined[i] = arr2[idx2];
                    idx2++;
                }

            }
            return combined;
        }


    }


    private static class Graph {
        EdgeNode[] edgeArr;

        Graph(int v, int[][] edges, boolean directed) {
            edgeArr = new EdgeNode[v];
            for (int i = 0; i < edges.length; i++) {
                insert(edges[i][0], edges[i][1], edges[i][2], directed);
            }
        }

        void insert(int x, int y, int w, boolean directed) {
            EdgeNode e = new EdgeNode(y, w);
            if (edgeArr[x] == null) {
                edgeArr[x] = e;

            } else {
                e.next = edgeArr[x];
                edgeArr[x] = e;
            }
            if (!directed) {
                insert(y, x, w,true);
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

    private static class EdgeNode {
        EdgeNode(int y, int w) {
            this.y = y;
            this.w = w;
        }

        int y;
        int w;
        EdgeNode next;

    }
}
