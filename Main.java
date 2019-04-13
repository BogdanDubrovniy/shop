import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Statistic {
        private int n;
        private int m;
        private int k;
        private int h;

        Statistic(int n, int m, int k, int h) {
            this.n = n;
            this.m = m;
            this.k = k;
            this.h = h;
        }

        int getN() {
            return n;
        }

        int getM() {
            return m;
        }

        int getK() {
            return k;
        }

        int getH() {
            return h;
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // 1 <= n <= 1000

        List<Statistic> st = new ArrayList<>();

        for (int i = 0; i < t; i++) {
            int n, m, k, h;

            n = in.nextInt();
            m = in.nextInt();
            k = in.nextInt();
            h = in.nextInt();

            st.add(i, new Statistic(n, m, k, h));
        }

        for (Statistic s : st) {
            int n, m, k, h;

            n = s.getN();
            m = s.getM();
            k = s.getK();
            h = s.getH();

            if (n < k || m < k) {
                System.out.println("NO");
                continue;
            }

            if (((n + m) / k) < h) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }

        in.close();
    }
}