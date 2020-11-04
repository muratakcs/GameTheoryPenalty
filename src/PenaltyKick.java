import java.util.Random;

public class PenaltyKick {
    Goalkeeper gk;
    Kicker k;
    Random rand;
    double[] ps;
    public PenaltyKick(Goalkeeper gkeep, Kicker kicker, double[] psave) {
        gk = gkeep;
        k = kicker;
        rand = new Random();
        ps = psave;
    }
    public int shoot() {  //0: miss, 1:save, 2:score
        int kickside = k.decide();
        int jumpside = gk.decide();
        int r = rand.nextInt(100);
        if(r>k.a[kickside]) return 0; // Kicker misses the goal
        r = rand.nextInt(100);
        if(kickside!=jumpside) {
            if(r<10*ps[kickside]) return 2; // Different sides, probably a score
            else return 1; //improbable save (although incorrect sides somehow keeper saves)
        }
        if(r<100*ps[kickside]) return 2; // Same side but kicker scores.
        return 1; //same side goalkeeper saves.

    }
}
