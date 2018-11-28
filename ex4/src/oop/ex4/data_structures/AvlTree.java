package oop.ex4.data_structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AvlTree {
    private static final int NOT_FOUND = -1;
    private static final int ONLY_ROOT_H = 0;
    private static final int ROOT_WITH_CHILD_H = 1;
    private AvlNode root;
    private int treeSize;

    private class AvlNode{
        private AvlNode left, right, parent;
        private int val, balance, height;
        private final int DEFAULT_STARTING_VALUE = 0;
        /**
         * Constructors of an AVL Node
         */
        public AvlNode(int startingValue){
            parent=null;
            left=null;
            right=null;
            val = startingValue; // requested value
            balance=DEFAULT_STARTING_VALUE;//=0
            height=DEFAULT_STARTING_VALUE; //=0
        }
        public AvlNode(int startingValue, AvlNode inputParent){
            parent = inputParent; // requested parent
            left=null;
            right=null;
            val = startingValue; // requested value
            balance=DEFAULT_STARTING_VALUE;//=0
            height=DEFAULT_STARTING_VALUE; //=0
        }

    }



    /*
      CONSTRUCTORS-
      First constructor - default constructor that creates a tree with no nodes which means that the root is null
      Second constructor - deep copies a given AvlTree object.
      Third constructor - creates an avl tree from given array by inserting value by value with it's add function.
     */

    /**
     * a constructor that creates a new empty tree
     */
    public AvlTree() {
        root = null;
    }
    /**
     * a constructor that will deep-copy a given tree.
     * this constructor takes a given tree's iterator() and adds it's iterator items one by one to a new tree.
     */
    public AvlTree(AvlTree tree) {
        java.util.Iterator<Integer> iter = tree.iterator();
        root = null;
        while(iter.hasNext()){
            add(iter.next());
        }
    }
    /**
     * a constructor that creates an AVL tree from a given integer array.
     * this constructor runs for all the values in given array and using add() method, adds them to an empty tree.
     */
    public AvlTree(int[] data) {
        for (int i : data) {
            add(i);
        }
    }

    /*
      MAIN METHODS-
      These are the main functions of this class
     */
    /**
     * this method adds a new value to our AVLTree object
     * @param newValue - integer to add
     * @return true/false depending if we actually added the item.
     */
    public boolean add(int newValue){
        // if the root is empty than we have an empty tree so we initialize the root
        if(root == null){
            root = new AvlNode(newValue);
        }else{
            //got here because there are elements in our tree thus we check if the value we want to insert is not
            //already in our tree and then we decide if it needs to be inserted to the left of right subtree and so on.
            AvlNode n = root;
            AvlNode p;
            while(true){
                if(n.val==newValue){
                    //we get here if the value is already in our tree.
                    return false;
                }
                boolean isSmaller = n.val>newValue;
                p = n; // the parent of the current node
                //here we change the node according to the given value equation with the current node
                n = isSmaller ? n.left : n.right;
                if(n==null){
                    // if we got here then we are at an empty node so we want to insert our value here!
                    // the tricky part is here, we look and the parent that we saved before! NOT THE NODE, and check if
                    // the value we want to add needs to be put as a left or right child according to the equation from
                    // before.
                    if(isSmaller){
                        p.left = new AvlNode(newValue,p);
                    }else{
                        p.right = new AvlNode(newValue,p);
                    }
                    // checking if the tree needs to be balanced if we got here than it means we have added a new item!
                    rebalance(p);
                    break;
                }
            }
        }
        treeSize++; //we've added a new item thus we increase the tree size.
        return true;
    }

    /**
     * this method will search if our tree has a specific value in one if it's nodes.
     * basically what I do is go recursively with a while statement to where i need to go by using the fact that for
     * every node x x.left<x<x.right. thus i move left or right using this fact in a while that ends either if we got to
     * an empty cell or the place we wanted to get to.
     * @param searchVal - integer to search for in the values
     * @return int depth - integer with the depth of the found node, -1 if it wasn't found.
     */
    public int contains(int searchVal) {
        AvlNode rt = root;
        int counter=0;
        while (rt != null) {
            if (searchVal == rt.val) {
                return counter;
            } else if (searchVal > rt.val) {
                rt = rt.right;
                counter++;
            } else {
                rt = rt.left;
                counter++;
            }
        }
        return NOT_FOUND; //=-1
    }

    /**
     * this method will remove a specific node with a given value from our avlTree
     * @param toDelete - integer with the value we want to delete
     * @return true/false - depending if we actually deleted a node or not.
     */
    public boolean delete(int toDelete){
        if(root==null){
            return false;
        }
        AvlNode n = root;
        AvlNode p = root;
        AvlNode c = root;
        AvlNode d = null;
        while(c!=null){
            p=n;
            n=c;
            c = toDelete>=n.val?n.right:n.left;
            if(toDelete== n.val){
                d=n;
            }
        }
        if(d!=null){
            d.val = n.val;
            c= n.left!=null ? n.left : n.right;
            if(root.val==toDelete){
                root=c;
            }else{
                if(p.left==n){
                    p.left = c;
                }else{
                    p.right = c;
                }
                rebalance(p);
            }
        treeSize--;
        return true;
        }else{
            return false;
        }
    }

    /**
     * with a given h - height this function will return the minimal nodes possible to be in an avl tree
     * @param h - height of an avl tree
     * @return int that describes the minimal nodes for h-height tree.
     */
    public static int findMinNodes(int h) {
        if (h == ONLY_ROOT_H) {
            return 1;
        }
        if (h == ROOT_WITH_CHILD_H) {
            return 2;
        }
        return 1+findMinNodes(h-2)+findMinNodes(h-1);
    }

    /**
     * this method returns an iterator with an ascending of the values in our tree.
     * @return an iterator
     */
    public Iterator<Integer> iterator(){
        List<Integer> output = new ArrayList<>();
        inorder(root, output);
        return output.iterator();
    }

    /**
     * this method is a helper for the iterator.
     * what it does is an inorder traversal recursively on the AVL tree and adds the values into the given Array list.
     * @param n - node we're at
     * @param input - array list that we add the values into
     */
    public void inorder(AvlNode n, List<Integer> input){
        if(n==null){
            return; //do nothing when we reach a null pointer
        }
        // we first go left then parent then right for an avl tree holds the statement that for node x x.left<x<x.right
        inorder(n.left,input);
        input.add(n.val);
        inorder(n.right, input);
    }
    /**
     * this method returns the amount of nodes in our current tree.
     * @return integer of the amount of nodes in our tree
     */
    public int size(){
        return treeSize;
    }
    /*
      A collection of private methods that help implement the public methods.
     */
    /**
     * this function receives a node and will balance the tree according to the node's balance attribute.
     * meaning the tree will rotate according to the node's balance.
     * @param node - the node to rebalance
     */
    private void rebalance (AvlNode node){
        node.balance = getBalance(node);
        if(node.balance==-2){
            if(getHeight(node.left.left) >= getHeight(node.left.right)){
                node = rightRotation(node);
            }else{
                node = leftRightRotation(node);
            }
        }else if(node.balance==2){
            if(getHeight(node.right.right) >= getHeight(node.right.left)){
                node = leftRotation(node);
            }else{
                node = rightLeftRotation(node);
            }
        }
        if(node.parent!=null){
            rebalance(node.parent);
        }else{
            root = node;
        }
    }

    /**
     * this method will return the height of a given node(the distance of the node from the root)
     * @param node - the node that we want to check
     * @return int height of the given node
     */
    private int getHeight(AvlNode node){
        if(node==null){
            return -1;
        }
        return 1 + Math.max(getHeight(node.right),getHeight(node.left));
    }

    /**
     * this method calculates the balance of a given node (node's right height - node's left height)
     * @param node
     * @return int of the balance of the given node
     */
    private int getBalance(AvlNode node){
        return getHeight(node.right)-getHeight(node.left);
    }

    /**
     * this method runs to the left most node and returns it (the lowest value node in our tree)
     * @param n - given node to run from
     * @return the left most node in our tree (smallest value node)
     */
    private AvlNode minimalValNode(AvlNode n){
        AvlNode tmp = n;
        while(tmp.left!=null){
            tmp = tmp.left;
        }
        return tmp;
    }


    /*
      THESE ARE ALL THE ROTATION METHODS THAT WILL ROTATE OUR TREE AS WE WANT.
     */
    private AvlNode rightRotation(AvlNode a){
        AvlNode b = a.left;
        b.parent = a.parent;
        a.left = b.right;
        if(a.left != null){
            a.left.parent = a;
        }
        b.right = a;
        a.parent = b;
        if(b.parent!=null){
            if(b.parent.left==a){
                b.parent.left = b;
            }else{
                b.parent.right = b;
            }
        }
        a.balance=getBalance(a);
        b.balance=getBalance(b);
        return b;
    }
    private AvlNode leftRotation(AvlNode a){
        AvlNode b = a.right;
        b.parent = a.parent;
        a.right = b.left;
        if(a.right != null){
            a.right.parent = a;
        }
        b.left = a;
        a.parent = b;
        if(b.parent!=null){
            if(b.parent.right==a){
                b.parent.right = b;
            }else{
                b.parent.left = b;
            }
        }
        a.balance=getBalance(a);
        b.balance=getBalance(b);
        return b;
    }
    private AvlNode rightLeftRotation(AvlNode n){
        n.right = rightRotation(n.right);
        return leftRotation(n);
    }
    private AvlNode leftRightRotation(AvlNode n){
        n.left = leftRotation(n.left);
        return rightRotation(n);
    }

}
