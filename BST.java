package exercise;

/**
 * Created by ikirilov on 12/05/15.
 */
public class BST<Key extends Comparable<Key>,Value> {
    private Node root;

    private class Node{
        private Node left, right;
        private Key key;
        private Value value;
        private int N;
        public Node(Key key, Value value, int size){
            this.key=key;
            this.value=value;
            N=size;
        }
    } //End Node Class

    /***********************************************************************
     *  helper methods
     ***********************************************************************/
    public boolean containsKey(Key key){
        return get(key)!=null;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public int size(){
        return size(root);
    }

    public int size(Node x){
        if(x==null)
            return 0;
        else
            return x.N;
    }

    /***********************************************************************
     *  get
     ***********************************************************************/
    public Value get(Key key){
        return get(root, key);
    }

    public Value get(Node x, Key key){
        if(x==null) return null;
        int comp = key.compareTo(x.key);
        if(comp<0)
            return get(x.left,key);
        else if (comp>0)
            return get(x.right, key);
        else  //if key=x.key
            return x.value;
    }

    /***********************************************************************
     *  put
     ***********************************************************************/
    public void put(Key key, Value value){
        root = put(root, key, value);
    }

    public Node put(Node x, Key key, Value value){
        if(x==null) return new Node(key, value, 1);
        int comp = key.compareTo(x.key);
        if(comp<0)
            x.left=put(x.left,key,value);
        else if(comp>0)
            x.right=put(x.right,key,value);
        else
            x.value=value; //Overwrite old value
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /***********************************************************************
     *  delMin
     ***********************************************************************/
    public void delMin(){
        root = delMin(root);
    }

    public Node delMin(Node x){
        if(x.left==null) return x.right;
        x.left = delMin(x.left);
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /***********************************************************************
     *  main - Test method for the client
     ***********************************************************************/
    public static void main(String[] args) {
        BST bst = new BST();
        String countries = "Bulgaria, Romania, USA, France";
        String capitals = "Sofia, Bucharesti, Washington, Paris";
        String[] country = countries.split(",");
        String[]capital = capitals.split(",");

        for(int i=0; i<country.length;i++){
            String key = country[i];
            String value = capital[i];
            bst.put(key,value);
        }

        for (String s : country)
        System.out.println(s + "->" + bst.get(s));


    }

    //Other methods to implement
    //containsKey
    //size
    //get
    //add
    //findMin
    //findMax
    //delete
    //iterate
    //inorder

}   //End class
