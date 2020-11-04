import java.util.Random;

public class Kicker {
    int np; // Number of penalties

    int nl; // Number of left shots
    int nm; // Number of middle shots
    int nr; // Number of right shots

    int nlm; // Number of left misses
    int nmm; // Number of middle misses
    int nrm; // Number of right misses

    int nls; // Number of left saves
    int nms; // Number of middle saves
    int nrs; // Number of right saves

    double probl; // Probability of shooting left
    double probm; // Probability of shooting to the middle
    double probr; // Probability of shooting right

    public Kicker() {
        probl=0.40;
        probr=0.40;
        probm=0.20;
    }

    public void report(int side_gk, int side_sh, boolean miss, boolean save) {

    }

    public int decide() {
        Random rand =new Random();
        int x = rand.nextInt(1000);
        if(x<1000*probl) return 0; //left;
        if(x<1000*(probl+probm)) return 1; //middle;
        return 2; //right;
    }
}
