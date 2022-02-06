package graph;

import java.util.Stack;

public class TopologicalSort {



    Stack stk  = new Stack();
    int[] discovered ;
    int root = -1;

    void sort(Graph g , int x){
        if( root == -1 ){
            discovered = new int[g.edgeArr.length];
            for( int i =0;i< g.edgeArr.length ;i++){
                discovered[i] = -1 ;
                root = x;
            }

            discovered[x] = x;
        }
        EdgeNode e =  g.edgeArr[x];
//        System.out.println("Node " + x);
        while( e!= null ){
            int y = e.y ;
//            System.out.println("\tEdge : " + y);
            if( discovered[y] == -1 ){
//                System.out.println("\tDiscovered : " + y);

                discovered[y] = x;
                sort(g,y);
            }
            e = e.next ;
//            System.out.println("\tEdge : " + e );

        }
        stk.push(x);

    }

    /*
     *                       9      3     1         10
     *                       ^      |    ^         ^
     *                       \      |    |         |
     *                        \     v    |        /
     *                         8    0 -> 2       6
     *                         \    ^   | \     ^
     *                          \   |   |  \   /
     *                           v /    v   v /
     *                            4     5    7
     *
     *
     * */

    /***
     *
     *                                   _________
     *                   _______________|________|______________________
     *                 |      ___      | ____    |                     |
     *                 |     |   v    ||    v    v                     v
     *               : 8  : 4  : 0  :  2  : 5  : 7  : 6  : 10  : 1  : 9
     *                 |    ^    |    ^|                          ^
     *                 |    |     ---- ---------------------------
     *                  - --
     */

    public static void main(String[] args) {
        int[][] edges = { {2, 5}, {2, 7}, {7, 6}, {6, 10},{2,1} ,{0,2},{3, 0}, {4, 0}, { 8, 4}, {8, 9 } };
        Graph g = new Graph(11,edges,true);
        System.out.println(g);
        TopologicalSort ts = new TopologicalSort();
        ts.sort(g,8);
        while(!ts.stk.isEmpty()){
            System.out.print( "  : " + ts.stk.pop());
        }
    }


    private static class Graph{
        EdgeNode[] edgeArr;

        Graph( int v , int[][] edges, boolean directed){
            edgeArr = new EdgeNode[v];
            for (int i=0;i< edges.length ;i++){
                insert(edges[i][0],edges[i][1],directed);
            }
        }

        void insert( int x, int y , boolean directed){
            EdgeNode e = new EdgeNode(y);
            if( edgeArr[x]  != null )
            {
                e.next  = edgeArr[x];
                edgeArr[x] = e ;
            }else{
                edgeArr[x] = e;
            }
            if(! directed){
                insert(y,x,true);
            }
        }

        public String toString (){

            StringBuilder sb = new StringBuilder();
            for( int i = 0 ; i< edgeArr.length ; i++ ){
                sb.append(" Node "+ i ) ;
                EdgeNode e = edgeArr[i];
                while( e != null ){
                    sb.append( " -> " + e.y);
                    e=e.next;
                }
                sb.append("\n");
            }
            return sb.toString();
        }

    }

    private static  class EdgeNode{
        EdgeNode( int y){
            this.y = y;
        }
        EdgeNode next;
        int y;
    }


}
