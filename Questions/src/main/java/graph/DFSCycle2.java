package graph;

import java.util.Stack;

public class DFSCycle2 {
    int root ;
    int[] discovered = null ;


    public boolean detectCycle(Graph g , int statIdx , Stack stk){
        boolean found = false;
        if( discovered == null ) {
            root = statIdx;
            discovered = new int[g.edgeArr.length];
            for(int i=0 ;i < g.edgeArr.length; i++ )
                discovered[i] =-1;
            discovered[statIdx] = statIdx;

        }
            EdgeNode e =  g.edgeArr[statIdx];
            while(e != null ) {
                System.out.println("Processing X : " +statIdx+ " Y : " + e.y);
                if (discovered[e.y] == -1) {
                    discovered[e.y] = statIdx;
                    System.out.println("Discovered Y : " + e.y);
                    found = detectCycle(g,e.y,stk);
                    if( found ) {
                        stk.add(e.y);
                        break;
                    }
                } else {
                    System.out.println("Cycle Detected ") ;
                    return true;
                }
                if( found )
                    break ;
                e = e.next;
            }

       return found ;
    }


    private static class Demo{
        /*
         *                       9        3    1           10
         *                       ^      / ^   ^ \          ^
         *                        \   /   |  /   \        /
         *                         \ v    | /     v      /
         *                         8 - > 0 -- >  2      6
         *                         ^    /       / \    ^
         *                         \   /      /    \  /
         *                          \ V      V     V /
         *                           4     5        7
         *
         *
         * */

        public static void main(String... args){
            int[][] edges = {{0, 1},{ 8 ,0}, {2, 5}, {2, 7}, {7, 6}, {6, 10}, {0, 3}, {0, 4},  {3, 8}, {4, 8}, {8, 9}, { 0,2},  {1, 2} };
            Graph g = new Graph(11,edges,true);
            System.out.println (g);
            Stack cycle = new Stack();
            if(new DFSCycle2().detectCycle(g, 8, cycle)){
                while(!cycle.isEmpty())
                    System.out.print( "->" + cycle.pop());
            }
        }
    }



    private static  class Graph {
        EdgeNode[]  edgeArr ;
        Graph ( int v, int[][] edges , boolean directed){
            edgeArr = new EdgeNode[v];
            for(int i=0 ;i < edges.length; i++ ){
                insertEdge( edges[i][0], edges[i][1],directed );
            }
        }

        void insertEdge( int x, int y , boolean directed) {

            if (edgeArr[x] == null) {
                edgeArr[x] = new EdgeNode(y);
            } else {
                var newNode = new EdgeNode(y);
                newNode.next = edgeArr[x];
                edgeArr[x] = newNode;
            }
            if (!directed) {
                insertEdge(y, x, true);
            }

        }
        public String toString(){
        StringBuilder sb = new StringBuilder();
        for( int i=0 ;i< edgeArr.length ; i++){
            sb.append("Node : "+i+ " ");
            var curEdge = edgeArr[i];
            while ( curEdge!=null){
                sb.append(" -> " + curEdge.y);
                curEdge = curEdge.next;
            }
            sb.append("\n");
        }
            return sb.toString();
        }
    }

    private static class EdgeNode{
        EdgeNode(int y ) {
            this.y= y;
        }
        int y ;
        EdgeNode next;

    }

}

