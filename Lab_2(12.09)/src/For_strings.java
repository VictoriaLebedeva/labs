//Вариант 8
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class For_strings {

    public static void main (String[]args)
    {
        String result ="";
        File file = new File("C:\\Users\\Виктория\\Desktop\\text.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine())
            {
                result = "";
                String buffer = sc.nextLine();
                StringTokenizer str = new StringTokenizer(buffer," ");
                while (str.hasMoreElements())
                {
                    String a = str.nextToken();

                    if (a.length() > 1)
                    {
                        result += " "+a+" ";
                    }
                }
                if (result.length()!=0) {
                    System.out.println(result);
                }
                else
                    System.out.println("All words have length 1");
            }
            sc.close();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
