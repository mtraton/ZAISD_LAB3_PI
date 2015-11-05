package main;

import parallel.PI;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.*;
import java.util.concurrent.Future;

/**
 * Created by Rael on 04.11.2015.
 */
public class main {
    static public Double getPI(int N)  {
        double h = 0.00001;
        double wynik =  0;
        double denom = N;
        for(int i = 0; i <N; i++)
        {
            double nom = i-0.5;
            double frac = nom/denom;
            double el = 1 + Math.pow(frac,2);
            wynik +=4/( h * el);

        }
        return wynik;
    }





    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int threads = Runtime.getRuntime().availableProcessors();
        System.out.println(threads);
        int N = (int)Math.pow(10,8);
        ArrayList<Future<Double>> call_list = new ArrayList<Future<Double>>();
        int div = (int)Math.ceil(N / (double)threads);

        for (int i = 0; i < threads; i++) {
            int start = i * div;
            int end = i * div + div - 1;
            //if(i == 3) end  -= 2;
            call_list.add(pool.submit(new PI(start,end, N)));
            System.out.println("s = " + start + ", e = " + end);
        }
        double res = 0;
        for (Future<Double> dou : call_list) {

                try {
                    System.out.println("Threads sum = " + dou.get());
                    res += dou.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

             finally {
                System.out.println(res) ;
                pool.shutdown();
            }


        }
        System.out.println(Math.PI);
        System.out.println(getPI(N));
    }
}

