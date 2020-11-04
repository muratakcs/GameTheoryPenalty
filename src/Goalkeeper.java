import java.util.Random;

public class Goalkeeper {

    int[] a; //saving abilities 0:left 1:middle 2:right

    int p; // Number of penalties

    int[] n; // Number of shots 0:left 1:middle 2:right

    int[] m; // Number of misses 0:left 1:middle 2:right

    int[] s; // Number of saves 0:left 1:middle 2:right

    double[] pr; // Probability of shooting sides 0:left 1:middle 2:right

    public Goalkeeper(int l, int m, int r) {
        a[0] = l;
        a[1] = m;
        a[2] = r;
        pr[0]=0.40;
        pr[1]=0.20;
        pr[2]=0.40;
    }

    public void report(int side_gk, int side_sh, boolean miss, boolean save) {

    }

    public int decide() {
        Random rand =new Random();
        int x = rand.nextInt(1000);
        if(x<1000*pr[0]) return 0; //left;
        if(x<1000*(pr[0]+pr[1])) return 1; //middle;
        return 2; //right;
    }


}
