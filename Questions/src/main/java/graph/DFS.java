package graph;

import java.util.Stack;

public class DFS {

    Stack<Integer> stack = new Stack();
    int[] discovered;
    int root = -1 ;

    private static class Result{

        boolean found = false ;
        StringBuilder msg = new StringBuilder() ;

    }

    Result search( Graph g , int startIdx, int targetIdx ){

        Result result = new Result();

        if( root == -1){
            root = startIdx ;
            discovered = new int[g.edgeArr.length];
            for(int i = 0; i < discovered.length; i++)
                discovered[i] =-1;
            stack.push(startIdx);
        }

        while(!stack.isEmpty()){
            int x= stack.pop();
            EdgeNode list= g.edgeArr[x];

            while (list != null )
            {
                System.out.println("Processing  X : " + x + " Y : " + list.y );
                if( discovered[list.y] == -1 && list.y != root ){
                    discovered[list.y] = x;
                    System.out.println( "Discovered : " + list.y ) ;
                    if( targetIdx == list.y){
                        System.out.println( " Found : " + list.y ) ;
                        result.found=true;
                    }else {
                        stack.push(list.y);
                        result = search(g, list.y, targetIdx);
                        if(result.found == true){
                            break;
                        }
                    }
                }
                list = list.next;
            }
            if(result.found == true){
                break;
            }
        }

        if( startIdx == root ) {
            if (result.found) {
                result.msg.append(" -> " + targetIdx);
                int cur = discovered[targetIdx];
                do {
                    result.msg.append(" -> " + cur);
                    cur = discovered[cur];
                } while (discovered[cur] != -1);
            } else {
                result.msg.append("Not Found");
            }
        }



        return result;
    }



    private static class Graph {
        EdgeNode[] edgeArr;

        Graph( int v , int[][] edges , boolean directed)
        {

            edgeArr = new EdgeNode[v];
            for(int i=0; i< edges.length;i++){
                insertEdge( edges[i][0], edges[i][1],directed);
            }
        }

        void insertEdge(int x, int y, boolean directed){
            if(edgeArr[x] == null ){
                edgeArr[x] = new EdgeNode(y);
            }else{
                EdgeNode temp =  edgeArr[x];
                EdgeNode node = new EdgeNode(y);
                node.next = temp ;
                edgeArr[x]= node;
            }
            if( !directed ){
                insertEdge(y,x, !directed);
            }

        }

        public String toString(){
            StringBuilder sb=  new StringBuilder();
            for(int i =0 ;i< edgeArr.length; i++){
                sb.append("Node " +i +"  " );
                EdgeNode cur = edgeArr[i];
                while(cur != null ){
                    sb.append(" -> " + cur.y);
                    cur=cur.next;
                }
                sb.append("\n");
            }
            return sb.toString();
        }

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
        DFS.Graph graph = new DFS.Graph(11, edges, false);
        System.out.println(graph);
        int startIdx = 9 , targetIdx =10;
        System.out.println("Searching for startIdx : " + startIdx + " Target : " + targetIdx );
        System.out.println(new DFS().search(graph, startIdx, targetIdx).msg);

    }

    private static class EdgeNode{
        EdgeNode( int y ) {
            this.y = y ;
        }
        int y ;
        EdgeNode next;
    }

}


