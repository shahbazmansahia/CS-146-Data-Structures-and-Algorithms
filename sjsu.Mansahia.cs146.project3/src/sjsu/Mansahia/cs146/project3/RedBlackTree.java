package sjsu.Mansahia.cs146.project3;

public class RedBlackTree<Key extends Comparable<Key>> { 
   
   static RedBlackTree.Node<String> nil = new RedBlackTree.Node<String>();
   static RedBlackTree.Node<String> root = nil;   
   public static class Node<Key extends Comparable<Key>> { //changed to static 
      
        Key key;          
        //nil.isRed = false;
        Node<String> parent = nil;
        Node<String> leftChild = nil;
        Node<String> rightChild = nil;
        boolean isRed = false;
        //int color;
        
        //static final Node <String> NIL = new Node (null);
        public Node() {
           leftChild = nil;
           rightChild = nil;
           parent = nil;
           isRed = false;
           key = (Key)"null";
        }
        public Node(Key data){
           this();
           this.key = data;
           //parent = NIL;
           //parent.isRed = false;
           //leftChild = null;
           //leftChild.isRed = false;
           //rightChild = null;
           //rightChild.isRed = false;
           
        }      
        
        public int compareTo(Node<Key> n){   //this < that  <0
           if (this.key.equals("null")) {
              if (n.key.equals("null")) {
                 return 0;
              }
              else {
                 return -1;
              }
           }
           else {
              if(n.key.equals("null")) {
                 return 1;
              }
              else {
                 return key.compareTo(n.key);     //this > that  >0
              }
           }
        }
        
        public boolean isLeaf(){
           if (this.equals(root) && this.leftChild == null && this.rightChild == null) return true;
           if (this.equals(root)) return false;
           if (this.leftChild == null && this.rightChild == null){
              return true;
           }
           return false;
        }
   }

    public boolean isLeaf(RedBlackTree.Node<String> n){
        if (n.equals(root) && n.leftChild == null && n.rightChild == null) return true;
        if (n.equals(root)) return false;
        if (n.leftChild == null && n.rightChild == null){
           return true;
        }
        return false;
     }
   
   public interface Visitor<Key extends Comparable<Key>> {
      /**
      This method is called at each node.
      @param n the visited node
      */
      void visit(Node<Key> n);  
   }
   
   public void visit(Node<Key> n){
      System.out.println(n.key);
   }
   
   public void printTree(){  //preorder: visit, go left, go right
      RedBlackTree.Node<String> currentNode = root;   
      printTree(currentNode);
   }
   
   public void printTree (RedBlackTree.Node<String> node) {
      if (node != nil) {
         printTree(node.leftChild);
         System.out.println(node.key);
         printTree(node.rightChild);
      }
   }
   
   /*
   public void printTree(RedBlackTree.Node<String> node){
      System.out.print(node.key);
      if (node.isLeaf()){
         return;
      }
      printTree(node.leftChild);
      printTree(node.rightChild);
   }
   */
   
   // place a new node in the RB tree with data the parameter and color it red. 
   public void addNode(String data){   //this < that  <0.  this > that  >0
    //   fill
      Node<String> z = new Node <String> (data);
      z.isRed = true;
      Node<String> y = nil; // = new Node <String> (null);
      Node<String> x = root;
      while (!isNil(x)) {
         y = x;
         if (z.compareTo(x) < 0) {
            x = x.leftChild;
         }
         else {
            x = x.rightChild;
         }
         
      }
      z.parent = y;
      if (isNil(y)) {
         //System.out.println("Going till here " + z.key );
         root = z;
         z.isRed = true;
         //nil.isRed = false;
         //root.isRed = true;
         //root.parent = new Node <String> (null);
         //root.parent.isRed = false;
      }
      else if (z.compareTo(y) < 0) {
         y.leftChild = z;
         //y.leftChild.isRed = true;
      }
      else {
         y.rightChild = z;
         //y.rightChild.isRed = true;
      }
      z.leftChild = nil;
      //z.leftChild.isRed = false;
      z.rightChild = nil;
      //z.rightChild.isRed = false;
      //z.isRed = true;
      
      rBInsertFixup (z);
      
   }  
   
   private void rBInsertFixup (Node <String> z) {
      Node <String> a = nil;
      //System.out.println("Going till here " + z.key );
      while (z.parent.isRed) {
         //System.out.println("Going till here " + z.key );
         
         if (z.parent.key.equals(getGrandparent(z).leftChild.key)) {
            a = getGrandparent(z).rightChild;
            
            if(a.isRed) {
               z.parent.isRed = false;
               a.isRed = false;
               getGrandparent(z).isRed = true;
               z = getGrandparent(z);
               //System.out.println(root.key);
            }
            
            else if (z.key.equals(z.parent.rightChild.key)) {
               z = z.parent;
               leftRotate(z);
            }
            
            else {
               z.parent.isRed = false;
               getGrandparent(z).isRed = true;
               rightRotate(getGrandparent(z));
            }
            
         }
         else {
            //System.out.println("Going till here " + z.key );
            //Node <String> b = getGrandparent(z).leftChild;
            a = getGrandparent(z).leftChild;
            
            if (a.isRed) {
               z.parent.isRed = false;
               a.isRed = false;
               getGrandparent(z).isRed = true;
               z = getGrandparent(z);
            }
            
            else if (z == z.parent.leftChild) {
               z = z.parent;
               rightRotate(z);
            }
            else {
               z.parent.isRed = false;
               getGrandparent(z).isRed = true;
               leftRotate(getGrandparent(z));
            }
         }
      }
      root.isRed = false;
      /*
      if(root != null) {
         root.isRed = false;
      }
      */
   }
   
   private void leftRotate (Node <String> x) {      
      Node <String> y = x.rightChild;
      x.rightChild = y.leftChild;
      if (!isNil(y.leftChild)) {
         y.leftChild.parent = x;
      }
      y.parent = x.parent;
      if (isNil(x.parent)) {
         root = y;
      }
      else if (x.key.equals(x.parent.leftChild)) {
         x.parent.leftChild = y;
      }
      else {
         x.parent.leftChild = y;
      }
      y.leftChild = x;
      x.parent = y;
   }
   
   private void rightRotate (Node <String> x) {
      Node <String> y = x.leftChild;
      x.leftChild = y.rightChild;
      
      if (!isNil(y.rightChild)) {
         y.rightChild.parent = x;
      }
      y.parent = x.parent;
      
      if (isNil(x.parent)) {
         root = y;
      }
      
      else if (x.key.equals(x.parent.rightChild.key)) {
         x.parent.rightChild = y;
      }
      
      else {
         x.parent.leftChild = y;
      }
      
      y.rightChild = x;
      x.parent = y;
   }
   
   public void insert(String data){
      addNode(data); 
   }
   
   public RedBlackTree.Node<String> lookup(String k){ 
      //fill
      Node <String> a = root;
      //Node <String> b = new Node (k);
      while (a.key != "null") {
         if(a.key.compareTo(k) == 0) {
            return a;
         }
         else if (a.key.compareTo(k) < 0) {
            a = a.leftChild;
         }
         else {
            a = a.rightChild;
         }
      }
      return nil;
   }
   
   
   public RedBlackTree.Node<String> getSibling(RedBlackTree.Node<String> n){  
      Node <String> m = new Node <String> (null);
      if (n.parent.key != null) {
         m = n.parent;
         if (m.leftChild == n) {
            return m.rightChild;
         }
         else {
            return m.leftChild;
         }
      }
      else {
         return n.parent;
      }
      
   }
   
   
   public RedBlackTree.Node<String> getAunt(RedBlackTree.Node<String> n){
      //
      Node <String> m = new Node <String> (null);
      if ((n.parent.key != null) && (n.parent.parent.key != null)) {
         m = n.parent.parent;
         if ((m.leftChild.leftChild == n) || (m.leftChild.rightChild == n)) {
            return m.rightChild;
         }
         else {
            return m.leftChild;
         }
      }
      else {
         if (n.parent.key == null) {
            return n.parent;
         }
         else {
            return getGrandparent(n);
         }
      }
   }
   
   public RedBlackTree.Node<String> getGrandparent(RedBlackTree.Node<String> n){
      return n.parent.parent;
   }
   
   public void rotateLeft(RedBlackTree.Node<String> n){
      //
      leftRotate(n);
   }
   
   public void rotateRight(RedBlackTree.Node<String> n){
      //
      rightRotate(n);
   }
   
   public void fixTree(RedBlackTree.Node<String> current) {
      //
   }
   
   public boolean isEmpty(RedBlackTree.Node<String> n){
      if (n == null){
         return true;
      }
      return false;
   }
    
   public boolean isNil (RedBlackTree.Node<String> n) {
      return (n == nil);
   }
   public boolean isLeftChild(RedBlackTree.Node<String> parent, RedBlackTree.Node<String> child)
   {
      if (child.compareTo(parent) < 0 ) {//child is less than parent
         return true;
      }
      return false;
   }

   public void preOrderVisit(Visitor<String> v) {
         preOrderVisit(root, v);
   }
    
    
   private static void preOrderVisit(RedBlackTree.Node<String> n, Visitor<String> v) {
      if (n == nil) {
         return;
      }
      v.visit(n);
      preOrderVisit(n.leftChild, v);
      preOrderVisit(n.rightChild, v);
   }  
}
