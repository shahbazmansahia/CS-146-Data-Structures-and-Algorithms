package sjsu.Mansahia.cs146.project2;

import java.util.Random;
/**
 * A class representing the quicksort algorithm and its forms
 * @author Shahbaz
 *
 */

public class QuickSort
{
   int[] a; // an array to return in case the test cases require one
   int partCount; // keeps track of the number of calls to the partition function
   /*
   public QuickSort() {
      partCount  = 0;
   }
   */
   /**
    * a default constructor which initializes the array as an empty one and the partition count to zero
    */
   public QuickSort() {
      partCount = 0;
      a = new int[0];
   }
   /**
    * A method to perform the normal quicksort algorithm, i.e. one where the last element in an array is takes as the pivot
    * @param a the array/sub-array which needs to be sorted
    * @param p the index to start the sorting in the array/sub-array
    * @param r the index to end the sorting in the array/sub-array
    * @return  the sorted array in case the program asks for it
    */
   public void normalQuickSort(int[] a, int p, int r) {
      //a = b;
      if (a.length == 0) 
         return;
      int q;
      if (p < r) {
         q = normalPart(a, p, r);
         normalQuickSort(a, p, q - 1);
         normalQuickSort(a, q + 1, r);   
      }
      this.a = a;
      //return a;
      
   }
   /**
    * A method to form the partition section and sort the array/sub-array
    * @param a the array/sub-array to be sorted
    * @param l the starting index of the partition
    * @param r the ending index of the partition
    * @return  the index of the pivot
    */
   private int normalPart(int[] a, int l, int r) {
      partCount++;
      int x = a[r];
      int i = l - 1;
      int temp = 0;      
      for(int j = l; j < r ; ++j) {
         if (a[j] <= x) {
            ++i;
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
         }
      }
      temp = a[i + 1];
      a[i + 1] = a[r];
      a[r] = temp;
      return (i+1);
   
   }
   /**
    * a method to perform quicksort by using a random element as the pivot
    * @param a the array/sub-array which needs to be sorted
    * @param p the index to start the sorting in the array/sub-array
    * @param r the index to end the sorting in the array/sub-array
    * @return  the sorted array in case the program asks for it
    */
   public void randomizedQuickSort(int a[], int p, int r) {
      //a = b;
      if (a.length == 0) 
         return;
      int q = 0;
      if(p < r) {
         q = randomizedPart(a, p, r);
         randomizedQuickSort(a, p, q - 1);
         randomizedQuickSort(a, q + 1, r);
      }
      //return a;
   }
   /**
    * This method basically chooses the random pivot element for the quicksort process
    * @param a the array/sub-array to be sorted
    * @param l the starting index of the partition
    * @param r the ending index of the partition
    * @return  the index of the pivot
    */
   private int randomizedPart(int a[], int l, int r) {
      //partCount++;
      Random gen = new Random(987654321);
      int i = l + gen.nextInt(r - l + 1);
      int temp = 0;
      temp = a[r];
      a[r] = a[i];
      a[i] = temp;
      return normalPart(a, l, r);
   }
   /**
    * a custom method to populate an array with random numbers up until the size fo the array
    * @param n the given size of the array
    * @return  the populated array
    */
   public int[] populate(int n) {
      Random gen = new Random(987654321);
      int[] a = new int[n];
      int i = 0;
      while(i < n) {
         a[i] = gen.nextInt(n);
         ++i;
      }
      return a;
   }
   /**
    * a method which prints the elements within the instance array
    */
   public String getArray () {
      String s = "[";
      for (int i = 0; i < a.length; i++) {
         s += (i == a.length - 1)? a[i] + "]" : a[i] + ", ";
      }
      return s;
   }
   /**
    * a method which returns the number of partition calls performed by the object
    * @return
    */
   public int getPartCount() {
      return partCount;
   }
   /**
    * a method to reset the instance counter "partCount" to zero
    */
   public void reset() {
      partCount = 0;
   }
   /**
    * FIXME: A stub for the selection-median quicksort algorithm required for extra credit
    * @param a the array that needs to be sorted
    * @param l the starting index of the array/sub-array
    * @param r the ending index of the array/sub-array
    * @param m the median of the array
    * 
    */
   public int select(int[] a, int l, int r, int m) {
      return 0;
   }
}
