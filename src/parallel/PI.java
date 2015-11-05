package parallel;

import java.util.concurrent.Callable;

/**
 * Created by Rael on 04.11.2015.
 */
public class PI  implements Callable<Double> {

        int start; // starting sum index
        int end;
        int N; // precision of computation
        double h = 0.00001;
        public PI(int start, int  end, int N)
        {
            this.start = start;
            this.end = end;
            this.N = N;
        }


        @Override
        public Double call() throws Exception {

            double wynik =  0;
            double denom = N;
            for(int i = start; i <= end; i++)
            {
                double nom = i-0.5;
                double frac = nom/denom;
                double el = 1 + Math.pow(frac,2);
                wynik +=4/( h * el);

            }
            return wynik;
        }


}
