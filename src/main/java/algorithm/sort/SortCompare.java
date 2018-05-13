package algorithm.sort;

import algorithm.std.StdRandom;

import java.lang.reflect.Method;

public class SortCompare {

    public static double time(String alg, Double[] a) throws Exception {
        Long start = System.nanoTime();
        String PACKAGE = "algorithm.sort";
//        switch (alg) {
//            case "Insertion": Insertion.sort(a); break;
//            case "Merge": Merge.sort(a); break;
//            case "Quick": Quick.sort(a); break;
//            case "Selection": Selection.sort(a); break;
//            case "Heap": Heap.sort(a); break;
//        }
        Class<?> algorithm = Class.forName(PACKAGE + "." + alg);
        Method sort = algorithm.getMethod("sort", Comparable[].class);
        sort.invoke(null, (Object) a);
        Long end = System.nanoTime();
        return end - start;
    }

    public static double timeRandomInput(String alg, int N, int T) throws Exception {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t< T; t++) {
            for (int i = 0; i < N; i++) a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) throws Exception {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        double times = t2 / t1;
        System.out.printf("For %d random Doubles\n    %s is %.1f times faster than %s\n", N, alg1, times, alg2);
        System.exit(0);
    }
}
