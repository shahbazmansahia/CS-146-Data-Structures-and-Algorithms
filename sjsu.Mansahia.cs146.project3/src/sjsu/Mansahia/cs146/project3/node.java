package sjsu.Mansahia.cs146.project3;

public class node
{
      public static final int BLACK = 0;
      public static final int RED = 1;
      
      public int index;
      public int color;
      node right;
      node left;
      node parent;
      
      public int leftLength;
      public int rightLength;
      
      public node() {
         color = BLACK;
         parent = null;
         left = null;
         right = null;
         leftLength = 0;
         rightLength = 0;
      }
      
      public node (int i) {
         color = BLACK;
         parent = null;
         left = null;
         right = null;
         leftLength = 0;
         rightLength = 0;
         index = i;
      }
      
      public int getLeftLength() {
         return leftLength;
      }
      
      public int getRightLength() {
         return rightLength;
      }
      
      
   }
