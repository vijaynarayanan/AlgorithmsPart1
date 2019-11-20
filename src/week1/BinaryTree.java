package week1;

public class BinaryTree {
    Node root;
    class Node{
        public Node(int value) {
            this.value = value;
        }
        int value;
        Node left;
        Node right;
    }

    public Node addNode(Node curr,int value){
        if(curr == null)
            return new Node(value);
        if(curr.value > value){
            curr.left = addNode(curr.left, value);
        }else if(curr.value < value){
            curr.right = addNode(curr.right, value);
        }
        return curr;
    }
    public void add(int value){
        root = addNode(root,value);
    }

    private int findSmallestNode(Node curr){
        return curr.left == null ? curr.value : findSmallestNode(curr.left);
    }

    private int findSmallestNodeGreaterThenCurrent(Node curr, int value){
        if(curr == null)
            return value;

        if(curr.value <= value)
            return findSmallestNodeGreaterThenCurrent(curr.right,value);
        else{
            if(curr.left != null)
                return findSmallestNode(curr.left);
            else
                 return curr.value;
        }

    }

    public Node findNode(Node curr,int value){
        if (curr == null)
            return null;
        else if (curr.value == value)
            return curr;
        else if (curr.value < value)
            return findNode(curr.left,value);
        else
            return findNode(curr.right,value);
    }

    public Node deleteNode(Node curr, int value){
        if(curr == null)
            return null;
        else if (curr.value == value) {
            if(curr.left == null && curr.right == null)
                return null;
            else if(curr.left == null)
                return curr.right;
            else if (curr.right == null)
                return curr.left;
            else{
                int smallest = findSmallestNode(curr.right);
                curr.value = smallest;
                curr.right = deleteNode(curr.right, smallest);
            }
        }
        else if(curr.value < value ){
            curr.left = deleteNode(curr.left, value);
        }
        curr.right = deleteNode(curr.right,value);
        return  curr;
    }
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.add(8);
        bt.add(4);
        bt.add(12);
        bt.add(2);
        bt.add(1);
        bt.add(3);
        bt.add(6);
        bt.add(5);
        bt.add(7);
        bt.add(10);
        bt.add(9);
        bt.add(11);
        bt.add(14);
        bt.add(13);
        bt.add(15);

        System.out.println(bt.findSmallestNodeGreaterThenCurrent(bt.root, 14));
    }
}
