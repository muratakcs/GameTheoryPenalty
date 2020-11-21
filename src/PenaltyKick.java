import java.util.Arrays;
import java.util.Random;

public class PenaltyKick {
    private Player k;
    private Player gk;
    private Random rand;
    private double[] prscore;
    private int[] ab;
    public PenaltyKick(Player kicker, Player gkeep, int[] abilities) {
        k = kicker;
        gk = gkeep;
        ab = abilities; //0:kl 1:km 2: kr 3:gl 4:gm 5:gr

        rand = new Random();
        prscore = new double[3]; // probability of scoring for each side, given the abilities...
        for(int i=0; i<3; i++)
            prscore[i] = ab[3+i] / (double)(ab[3+i]+ab[i]);
    }
    public int[] shoot() {
        int[] result = new int[3]; // kickside, jumpside, whathappened?0:missed,1:saved,2:scored.
        result[0] = k.decide(true,gk); // kicker decides
        result[1] = gk.decide(false,k); // goalie decides
        int r = rand.nextInt(100);
        if(r>ab[result[0]]) { // if rand is above the kickers shooting ability, miss...
            result[2]=0; // Kicker misses the goal
            return result;
        }
        // if kicker can kick the ball at least towards the net...
        double probgoal = prscore[result[0]]; // this is the default probability of scoring at that side.
        r = rand.nextInt(100); // randomize again to decide save or not

        //This line can be implemented better.  When shot right, jump left should be worse than staying in the middle.
        if(result[0]!=result[1]) probgoal = (9+probgoal)/10.0; // If gk jumps towards a wrong direction, saving probability drops substantially
        if(r<100* probgoal)
            result[2] = 2; // Same side but kicker scores.
        else
            result[2] = 1;  //same side goalkeeper saves.
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Testing PenaltyKick class");

        Player k = new Player("Baggio");
        Player gk = new Player("Zubizarreta");
        int[] ab = {95,80,90,80,90,70};
        PenaltyKick pk;
        int[][][] results = new int[3][3][3];
        int[] res;
        for(int i=0; i<1000000; i++) {
            pk = new PenaltyKick(k, gk, ab);
            res = pk.shoot();
            results[res[0]][res[1]][res[2]]++;
        }
        for(int i=0; i<3; i++) {
            System.out.println("Kicker shoots to "+i);
            for (int j = 0; j < 3; j++) {
                System.out.println("Goalie jumps to "+j);

                System.out.println("MISSES | SAVES | GOALS");
                System.out.println(Arrays.toString(results[i][j]));
            }
            System.out.println();
        }
    }
}
