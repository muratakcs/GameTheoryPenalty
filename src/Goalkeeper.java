import java.util.Random;

public class Goalkeeper {
    String name;
    int[] a; //saving abilities 0:left 1:middle 2:right
    int p; // Number of penalties
    int[][][] results; //D1:Kickerside D2:GKSide D3:result(0:miss,1:save,2:goal)
    double[] pr; // Probability of shooting sides 0:left 1:middle 2:right

    public Goalkeeper(String nm, int l, int m, int r) {
        name = nm;
        a[0] = l;
        a[1] = m;
        a[2] = r;
        pr[0]=0.40;
        pr[1]=0.20;
        pr[2]=0.40;
        results = new int[3][3][3];
    }

    public void report(int kside, int gkside, int res) {
        results[kside][gkside][res]++;
        // Here, one may want to play with the pr array (probabilities of jumping to which side)
    }

    // Decide method just uses the probabilities calculated above. Do not change this method.
    public int decide() {
        Random rand =new Random();
        int x = rand.nextInt(1000);
        if(x<1000*pr[0]) return 0; //left;
        if(x<1000*(pr[0]+pr[1])) return 1; //middle;
        return 2; //right;
    }


}
