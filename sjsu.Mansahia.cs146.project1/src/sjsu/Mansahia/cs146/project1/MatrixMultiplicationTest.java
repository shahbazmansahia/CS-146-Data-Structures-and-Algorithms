package sjsu.Mansahia.cs146.project1;

/*
import org.junit.*;
import static org.junit.Assert.*;
*/
import java.util.Random;

public class MatrixMultiplicationTest  
{
      public static void main(String[] args) {
      // create   two double  2-D   arrays
      double[][]  a;
      double[][]  b;
      
      int n = 1024;
      Random gen = new Random (987654321);
      
      a = new double [n][n];
      b = new double [n][n];
      
      for(int i = 0; i < n; ++i) {
         for (int j = 0; j < n; ++j) {
            //a[i][j] = 1 + gen.nextInt(20);
            //b[i][j] = 1 + gen.nextInt(20);
            a[i][j] = 1 + ((gen.nextDouble() * 100) % 20);
            b[i][j] = 1 + ((gen.nextDouble() * 100) % 20);
         }
      }
      
      Matrix   m1 =  new Matrix(a);
      Matrix   m2 =  new Matrix(b);
      //System.out.println("matrix 1:");
      //System.out.println(m1);
      System.out.println();
      //System.out.println("matrix 2:");
      //System.out.println(m2);
      System.out.println();
      
      long timerStartS = System.nanoTime();
      Matrix   productStrassen = new Matrix (m1.productStrassen(m2.getMatrix()));
      long timeElapsedS = System.nanoTime() - timerStartS;
      System.out.println("\nStrassen Time: " + timeElapsedS + " nanoseconds");
      long timerStartR = System.nanoTime();
      Matrix   productRegular= new Matrix (m1.productRegular(m2.getMatrix()));
      long timeElapsedR = System.nanoTime() - timerStartR;
      System.out.println("\nRegularTime: " + timeElapsedR + " nanoseconds\n");
      //Matrix productRegular = new Matrix (m1.productRegular(m2.getMatrix()));
      //System.out.println("Regular matrix multiplication product matrix: \n" + productRegular.toString() + "\n");
      
      System.out.println("Are matrices the   same?"+productStrassen.equals(productRegular));
      //System.out.println("\nStrassen matrix multiplication product matrix: \n" + productStrassen.toString() + "\n");
      
      }
}