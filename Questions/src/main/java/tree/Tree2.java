package tree;

import java.util.ArrayList;
import java.util.List;

public class Tree2 {



    public static void main(String[] args) {
        BST root  = createTree();
//        System.out.println(root);
        List<Integer> inOrderList = new ArrayList(),inOrderList2 = new ArrayList();
        List<Integer> preOrderList = new ArrayList(),preOrderList2 = new ArrayList();
        List<Integer> postOrderList = new ArrayList(), postOrderList2 = new ArrayList();
        inOrderTraversal(root,inOrderList );
        inOrderTraversal(createTree(inOrderList), inOrderList2);

        preOrderTraversal(root,preOrderList );
        preOrderTraversal(createTree(preOrderList),preOrderList2 );

        postOrderTraversal(root,postOrderList );
        postOrderTraversal(createTree(postOrderList),postOrderList2 );
        /*
        *                 1
        *               /   \
        *                    2
        *                      \
        *                       3
        *                       \
        *                       4
        *                       \
        *
        * */
        System.out.println(inOrderList);
        System.out.println(inOrderList2);
        /*
        *
        *               1
        *              /  \
        *                   4
        *                  / \
        *                2     6
        *                 \     \
        *                   3     7
        *                          \
        *                           8
        *
        * */
        System.out.println(preOrderList);
        System.out.println(preOrderList2);
        /*
        *
        *               3
        *              / \
        *             2   6
        *             /    / \
        *            1    4   9
        *                  / \
        *                 8
        *                /
        *               7
        *              /
        *             6
        *
        * */
        System.out.println(postOrderList);
        System.out.println(postOrderList2);

    }



    static BST createTree( List<Integer> list) {
        BST node = new BST(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            node.insert(node, list.get(i));
        }
        return node;
    }


    static BST createTree() {
        List<Integer> list = List.of(1, 4, 6,  7, 8, 9, 3, 6);
        BST node = new BST(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            node.insert(node, list.get(i));
        }
        return node;

    }

    private static void inOrderTraversal(BST node, List<Integer> list) {
        if(node.left != null )
        {
            inOrderTraversal(node.left, list);
        }
        list.add(node.x) ;
        if( node.right != null ){
            inOrderTraversal(node.right, list);
        }

    }

    private static void preOrderTraversal(BST node, List<Integer> list) {
        list.add(node.x) ;
        if(node.left != null )
        {
            preOrderTraversal(node.left, list);
        }
        if( node.right != null ){
            preOrderTraversal(node.right, list);
        }

    }

    private static void postOrderTraversal(BST node, List<Integer> list) {
        if(node.left != null )
        {
            postOrderTraversal(node.left, list);
        }
        if( node.right != null ){
            postOrderTraversal(node.right, list);
        }
        list.add(node.x) ;
    }


    private static class BST {
        BST(int x) {
            this.x = x;
        }

        int x;
        BST left;
        BST right;

        public void insert(BST node, int value) {
            if (value < node.x) {
                if (node.left == null) {
                    node.left = new BST(value);
                } else {
                    insert(node.left, value);
                }
            } else {
                if (node.right == null) {
                    node.right = new BST(value);
                } else {
                    insert(node.right, value);
                }
            }
        }


    }




}
