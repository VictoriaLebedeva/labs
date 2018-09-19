import java.util.Scanner;
import java.util.Random;

public class Matrix_Lab_3 {
public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Enter n: ");
    int n = in.nextInt();
    if (n <= 1) {
        System.err.println("Invalid n value (require: n > 1");
        System.exit(1);
    }

    Random rnd = new Random();
    rnd.setSeed(System.currentTimeMillis());

    int a[][] = new int[n][n];
    System.out.println("Source values: ");
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int temp = rnd.nextInt();
            a[i][j] = temp % (n + 1);

            System.out.printf("%4d", a[i][j]);
        }
        System.out.println();
    }
    int k = n; //rows
    int max_elem = a[0][0];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (a[i][j] > max_elem) {
                max_elem = a[i][j];
            }
        }
    }


    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < k; j++)
        {
            if (a[i][j] == max_elem)
            {
                for (int m = i; m < n-1; m++) {
                    for (int l = 0; l < n; l++) {
                        a[m][l] = a[m + 1][l];

                    }

                }
                n--;
                for (int m = 0; m < k; m++){
                    for (int l = j; l < k-1; l++)
                    {
                        a[m][l] = a[m][l+1];
                    }

                }
                k--;
            }
        }
    }
    System.out.println("Result");
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < k; j++)
        {
            System.out.printf("%4d", a[i][j]);
        }
    System.out.println();
     }
}
}
