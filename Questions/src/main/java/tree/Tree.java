package tree;

class Tree<T extends Comparable<T>> {

    public static void main(String[] args) {
        // Scanner in = new Scanner(System.in);
        // int a;
        // a = in.nextInt();
        // int b;
        // b = in.nextInt();
        // int sum;

        // sum = addNumbers(a, b);
        // System.out.println(sum);

        Tree thisTree = new Tree<Integer>();
        Node node1 = new Node(5);
        thisTree.addNode(node1);
        node1 = new Node(4);
        thisTree.addNode(node1);
        node1 = new Node(3);
        thisTree.addNode(node1);
        node1 = new Node(2);
        thisTree.addNode(node1);
        node1 = new Node(7);
        thisTree.addNode(node1);
        thisTree.toString();


    }

    Node<T> root = null;

    void addNode(Node<T> node) {
        if (root == null) {
            root = node;
            return;
        } else {
            root.addNode(node);
        }

    }


}

class Node<T extends Comparable<T>> {

    Node(T data) {
        this.data = data;
    }

    T data;
    Node<T> leftChild = null;
    Node<T> rightChild = null;
    Node<T> parent = null;

    void addNode(Node<T> node) {
        if (node.data.compareTo(this.data) > 0) {
            //Traverse Right Tree
            if (this.rightChild == null) {
                this.rightChild = node;
                node.parent = this;
            } else {
                this.rightChild.addNode(node);
            }

        } else {
            //Traverse Right Tree
            if (this.leftChild == null) {
                this.leftChild = node;
                node.parent = this;
            } else {
                this.leftChild.addNode(node);
            }
        }
    }


}

