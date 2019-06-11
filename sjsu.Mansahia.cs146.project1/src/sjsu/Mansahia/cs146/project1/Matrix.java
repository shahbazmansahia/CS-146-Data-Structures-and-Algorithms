package sjsu.Mansahia.cs146.project1;
/**
 * The project uses an object called "Matrix" to represent a mathematical matrix for 
 * implementing and testing Strassen's matrix multiplication method.
 * @author Shahbaz Singh Mansahia
 *
 */
import java.util.Random;

public   class Matrix   {
   
   private  double[][]  matrix;
   
   /**
    * the default constructor for generating a matrix object. It assigns a 2D array as the
    * matrix provided by the main function. the parameter is assigned to the instance variable
    * "matrix", which is a 2D array.
    * @param matrix the 2D array which is assigned to the instance variable which is also a 2D array.
    */
   
   public   Matrix(double[][] matrix)  {
            this.matrix =  matrix;
   }
   
   /**
    *  Compute  matrix   multiplication using regular  method   O(n3) solution.
    *  This  method   should   return   product  matrix.
    *
    *  @param   matrixB the matrix object to be operated upon for the computation.
    *  @return
    */
   
   public   double[][]  productRegular(double matrixB[][]){
      // Implement   your  algorithm   here.
      double temp [][] = new double [matrixB.length][matrixB[0].length];
      temp [0][0] = 0;
      for (int i = 0; i < temp.length; ++i) {
         for (int j = 0; j < temp[i].length; ++j) {
            temp [i][j] = 0;
            for (int k = 0; k < temp.length; ++k) {
               temp [i][j] += (this.matrix[i][k] * matrixB[k][j]);
            }
            
         }
      }
      return temp;
   }
   
   /**
    *  Compute  matrix   multiplication using Strassen's  method.
    *  This  method   should   return   product  matrix.
    *
    *  @param   matrixB the matrix object to be operated upon for the computation
    *  @return the product of 2 matrices with the same number of elements using Strassen's matrix multiplication method
    */
   
   public   double[][]  productStrassen(double  matrixB[][])   {
      // Implement   your  algorithm   here.
      double[][] matrixC = new double [this.matrix.length] [this.matrix.length];
      int p = matrix.length;
      
      // Control statement for recursion; root case for recursion
      if (p == 1) {
         matrixC [0][0]= matrix[0][0] * matrixB[0][0];
      }
      
      else {
         int n = matrix.length / 2;
         double[][] a11 = new double [n][n];
         double[][] a12 = new double [n][n];
         double[][] a21 = new double [n][n];
         double[][] a22 = new double [n][n];
         strassenDivide (matrix, a11, 0, 0);
         strassenDivide (matrix, a12, 0, n);
         strassenDivide (matrix, a21, n, 0);
         strassenDivide (matrix, a22, n, n);
         
         double[][] b11 = new double [n][n];
         double[][] b12 = new double [n][n];
         double[][] b21 = new double [n][n];
         double[][] b22 = new double [n][n];
         strassenDivide (matrixB, b11, 0, 0);
         strassenDivide (matrixB, b12, 0, n);
         strassenDivide (matrixB, b21, n, 0);
         strassenDivide (matrixB, b22, n, n);
         
         double[][] c11;
         double[][] c12;
         double[][] c21;
         double[][] c22;
         
         Matrix a1ad = new Matrix (addMatrix(a11, a22));
         Matrix a1a = new Matrix (addMatrix (a11, a12));
         Matrix a2a = new Matrix (addMatrix(a21, a22));
         Matrix a1sc = new Matrix (subtMatrix(a21, a11));
         Matrix a2sc = new Matrix (subtMatrix (a12, a22));
         Matrix a1i1 = new Matrix (a11);
         
         Matrix a2i2 = new Matrix (a22);
         Matrix b1ad = new Matrix (addMatrix(b11, b22));
         
         //Matrix b1i1 = new Matrix (b11);
         //Matrix b2i2 = new Matrix (b22);
         
         
         
         
         double[][] p1 = a1ad.productStrassen(b1ad.getMatrix());
         double[][] p2 = a2a.productStrassen(b11);
         double[][] p3 = a1i1.productStrassen(subtMatrix(b12, b22));
         double[][] p4 = a2i2.productStrassen(subtMatrix(b21, b11));
         double[][] p5 = a1a.productStrassen(b22);
         double[][] p6 = a1sc.productStrassen(addMatrix(b11, b12));
         double[][] p7 = a2sc.productStrassen(addMatrix(b21, b22));
         
         c11 = addMatrix(subtMatrix(addMatrix(p1, p4), p5), p7);
         c12 = addMatrix(p3, p5);
         c21 = addMatrix(p2, p4);
         c22 = addMatrix(subtMatrix(addMatrix(p1, p3), p2), p6);
         
         strassenMerge (c11, matrixC, 0, 0);
         strassenMerge (c12, matrixC, 0, n);
         strassenMerge (c21, matrixC, n, 0);
         strassenMerge (c22, matrixC, n, n);
      }
      return matrixC;
   }
   
   
   private void strassenDivide(double[][] h1, double[][]h2, int ir, int ic) {
      for (int i = 0, k = ir; i < h2.length; ++i, ++k) {
         //for (int k = ir; k < h2.length; ++k) {
            for (int j = 0, l = ic; j < h2.length; ++j, ++l) {
               //for (int l = ic; l < h2.length; ++l) {
                  h2[i][j] = h1 [k][l];
               }
            }
         //}
      //}
      
   }
   
   private void strassenMerge(double[][] h1, double[][] h2, int ir, int ic) {
      for (int i = 0, k = ir; i < h1.length; ++i, ++k) {
         //for (int k = ir; k < h1.length; ++k) {
            for (int j = 0, l = ic; j < h1.length; ++j, ++l) {
               //for (int l = ic; l < h1.length; ++l) {
                  h2[k][l] = h1[i][j];
               }
            //}
         }
      //}
   }
   
   
   /**
    * calculates the sum of 2 given matrices
    * @param matrixA the first matrix to be added
    * @param matrixB the second matrix to be added
    * @return the sum matrix of the elements of matrixA and matrixB
    */
   public double[][] addMatrix (double[][] matrixA, double[][] matrixB) {
      double[][] matrixC = new double [matrixA.length] [matrixA.length];
      for (int i = 0; i < matrixC.length; ++i) {
         for (int j = 0; j < matrixC[i].length; ++j) {
            matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
         }
      }
      return matrixC;
   }

/*   
   /**
    * calculates the difference between 2 matrices and returns it
    * @param matrixA the matrix to be subtracted from
    * @param matrixB the matrix to be subtracted
    * @return the difference matrix between the elements of matrixA and matrixB
    */
   
   public double [][] subtMatrix (double[][] matrixA, double[][] matrixB){
      double[][] matrixC = new double [matrixA.length] [matrixA.length];
      for (int i = 0; i < matrixC.length; ++i) {
         for (int j = 0; j < matrixC[i].length; ++j) {
            matrixC[i][j] = matrixA[i][j] - matrixB[i][j];
         }
      }
      return matrixC;
   }
 
   public double[][] getMatrix() {
      return matrix;
   }
   
   public Matrix Random() {
      Random gen = new Random (987654321);
      for (int i = 0; i < matrix.length; ++i) {
         for (int j = 0; j < matrix[i].length; ++j) {
            matrix [i][j] = 100 * (gen.nextDouble());
         }
      }
      return this;
   }
   
   public String toString() {
      String s = "";
      for (int i = 0; i < matrix.length; ++i) {
         for (int j = 0; j < matrix[i].length; ++j) {
            if (i == 0 && j == 0) {
               s = "[" + matrix[i][j] + ", ";
            }
            else {
               s += matrix[i][j] + ", ";
            }
         }
         if (i != matrix.length - 1) {
            s = s.substring(0, s.length() - 2) + "\n";   
         }
         
      }
      s = s.substring(0, s.length() - 2) + "]";
      return s;
   }
}