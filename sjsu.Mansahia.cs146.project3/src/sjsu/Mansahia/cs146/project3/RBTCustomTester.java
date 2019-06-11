package sjsu.Mansahia.cs146.project3;

import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import  java.util.Scanner;



public class RBTCustomTester
{
   public static RedBlackTree <String> getDataAndInsert (File abc) throws FileNotFoundException{
      RedBlackTree <String> rbtAns = new RedBlackTree <String> ();
      Scanner scan = new Scanner (abc);
      while (scan.hasNext()) {
         rbtAns.insert(scan.next());
      }
      scan.close();
      return rbtAns;
      
   }
   
   public static void main (String[] args) throws FileNotFoundException {
      String fileName = "defaultFile.txt"; //  default name for a file to be scanned
      Scanner scan = new Scanner (System.in);
      System.out.println("Enter the name of the file:");
      fileName = (scan.hasNext())? scan.nextLine() : fileName;
      RedBlackTree <String> dictionary = new RedBlackTree <String>();
      try {
      dictionary = getDataAndInsert (new File (fileName));
      }
      catch (FileNotFoundException exception) {
         System.out.println ("File either does not exist or it is not in the correct directory!");
         scan.close();
         return;
      }
      //dictionary.printTree();
      System.out.println("\nEnter words to lookup or enter 'q' to end: ");
      String word = "";
      
      do {
       word = scan.next();  
       System.out.println(dictionary.isNil(dictionary.lookup(word)) ? "\nNot found!" : "\nFound!");
      }while (!word.equals("q"));
      
      scan.close();
      
   }
}
