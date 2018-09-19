
public class Computation
{

    public static void main (String[]args)
    {
        double x = Double.parseDouble( args[0] );
        if ( Math.abs( x ) >= 1 )
        {
            System.err.println("Invalid argument: " + x );
            System.exit(1);
        }
        int k = Integer.parseInt( args[1] );
        if ( k <= 1 )
        {
            System.err.println("Invalid argument: " + k );
            System.exit(1);
        }

        double eps = 1 / Math.pow( 10, k + 1 );
        double step = x, summ = x;
        int n = 1;

        while( Math.abs( step ) >= eps )
        {
            step = step * (-1) * x * x * n /( n + 2 );
            summ += step;
            n += 2;
        }

        double check1 = Math.atan(x);

       System.out.printf("%10." + k +"f\n", check1);
       System.out.printf ("%10." + k +"f\n", summ);
    }
}
