package sjsu.Mansahia.cs146.project2;

//import java.util.Arrays;

public class QuickSortTester
{
   public static void main (String[] args) {
      int n = 10;
      int[] a = new int[n];
      
      QuickSort qs = new QuickSort();
      //a = qs.populate(a.length);
      int[] b = qs.populate(n);
      /*
      String s = "[";
      for (int i = 0; i < n; ++i) {
         s += (i == n - 1)? a[i] + "]" : a[i] + ", ";
      }
      
      System.out.println(s);
      */
      int i = 0;
      while (i < 5) {
         switch (i) {
         case 0:
            n = 10000;
            break;
         case 1:
            n = 100000;
            break;
         case 2:
            n = 1000000;
            break;
         case 3:
            n = 10000000;
            break;
         case 4:
            n = 100000000;
            break;
         default:
            System.out.println("Error!");
            break;
         }
         a = qs.populate(n);
         b = qs.populate(n);
         long qsNTimerS = System.currentTimeMillis();
         qs.normalQuickSort(a, 0, a.length - 1);
         long qsNTimerE = System.currentTimeMillis();
         int qsNP = 0;
         qsNP = qs.getPartCount();
         System.out.println("Partition Count normal sort: " + qsNP);
         qs.reset();
         long qsRTimerS = System.currentTimeMillis();
         qs.randomizedQuickSort(b, 0, a.length - 1);
         long qsRTimerE = System.currentTimeMillis();
         int qsRP = qs.getPartCount();
         System.out.println("Partition Count Randomized median sort: " + qsRP);
         qs.reset();
         System.out.println("qsn time: " + (qsNTimerE - qsNTimerS) + " milliseconds");
         System.out.println("qsr time: " + (qsRTimerE - qsRTimerS) + " milliseconds");
         System.out.println();
         ++i;
      }
      
      /*
      String s1 = "[";
      String s2 = "[";
      for (int i = 0; i < n; ++i) {
         s1 += (i == n - 1)? a[i] + "]" : a[i] + ", ";
      }
      for (int i = 0; i < n; ++i) {
         s2 += (i == n - 1)? b[i] + "]" : b[i] + ", ";
      }
      System.out.println("\n" + s1);
      System.out.println("\n" + s2);
      */
   }
}
