import java.util.Arrays;
import java.util.Random;

public class PenaltyKick {
    private Player k;
    private Player g;
    private Random rand;
    private double[] prscore;
    private int[] kab;
    private int[] gab;
    private Competition comp;
    private Match match;

    public PenaltyKick(Player kicker, Player gkeep, int[] kabi, int[] gabi, Competition c, Match m) {
        k = kicker;
        g = gkeep;
        kab = kabi; // index:ability  ->  0:kl(kicker left) 1:km 2: kr
        gab = gabi; // index:ability  ->  0:gl 1:gm 2:gr
        comp = c; // A penalty kick object must know which competition it is in.
        match = m;
        rand = new Random();
        prscore = new double[3]; // probability of scoring for each side, given the abilities...
        for(int i=0; i<3; i++)
            prscore[i] = kab[i] / (double)(gab[i]+kab[i]);
    }
    public int[] shoot() {
        int[] result = new int[3]; // kickside, jumpside, whathappened?0:missed,1:saved,2:scored.
        result[0] = k.decide(true, g, comp, match); // kicker decides
        result[1] = g.decide(false,k, comp, match); // goalie decides
        int r = rand.nextInt(100)+1;
        if(r>kab[result[0]]) { // if rand is above the kickers shooting ability, miss...
            result[2]=0; // Kicker misses the goal
            return result;
        }
        // if kicker can kick the ball at least towards the net...
        double probgoal = prscore[result[0]]; // this is the default probability of scoring at that side.
        r = rand.nextInt(100)+1; // randomize again to decide save or not

        if(result[0]!=result[1]) probgoal = (9+probgoal)/10.0; // If gk jumps towards a wrong direction, saving probability drops substantially
        if((result[0]-result[1])*(result[0]-result[1])==4) probgoal = (9+probgoal)/10.0; // If gk jumps towards opposite direction, saving probability drops even more
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
        int[] abk = {95,80,90};
        int[] abg = {10,90,70};
        PenaltyKick pk;
        int[][][] results = new int[3][3][3];
        int[] res;
        int[] c={0,0,0};

        for(int i=0; i<1000000; i++) {
            pk = new PenaltyKick(k, gk, abk, abg,null,null);
            res = pk.shoot();
            results[res[0]][res[1]][res[2]]++;
            c[res[0]]++;
        }
        for(int i=0; i<3; i++) {
            System.out.println("Kicker "+ k.getName() +" shoots to "+i+ "    "+c[i]+" times");
            for (int j = 0; j < 3; j++) {
                System.out.println("Goalie "+ gk.getName() +" jumps to "+j);

                System.out.println("MISSES | SAVES | GOALS");
                System.out.println(Arrays.toString(results[i][j]));
            }
            System.out.println();
        }
    }
}
