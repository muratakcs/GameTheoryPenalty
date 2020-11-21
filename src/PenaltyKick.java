import java.util.Arrays;
import java.util.Random;

public class PenaltyKick {
    Player k;
    Player gk;
    Random rand;
    double[] ps;
    public PenaltyKick(Player kicker, Player gkeep) {
        k = kicker;
        gk = gkeep;
        rand = new Random();
        ps = new double[3];
        for(int i=0; i<3; i++)
            ps[i] = gk.a[i] / (gk.a[i]+k.a[i]);
    }
    public int[] shoot() {  //0: miss, 1:save, 2:score
        int[] result = new int[3]; // kickside, jumpside, whathappened?0:missed,1:saved,2:scored.
        result[0] = k.decide();
        result[1] = gk.decide();
        int r = rand.nextInt(100);
        if(r>k.a[result[0]]) {
            result[2]=0; // Kicker misses the goal
            return result;
        }
        r = rand.nextInt(100);
        if(result[0]!=result[1]) { // If gk jumps towards a wrong direction
            if(r<10*ps[result[0]]) {
                result[2]=2; // Different sides, probably a score
                return result;
            }
            else {
                result[2] = 1; //improbable save (although incorrect sides somehow keeper saves)
                return result;
            }
        }
        if(r<100*ps[result[0]])
            result[2] = 2; // Same side but kicker scores.
        else
            result[2] = 1;  //same side goalkeeper saves.
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Testing PenaltyKick class");
        Player gk = new Player("Zubizarreta", 90, 90, 30);
        Player k = new Player("Baggio", 90, 20, 90);
        PenaltyKick pk;
        int[][][] results = new int[3][3][3];
        int[] res;
        for(int i=0; i<10000; i++) {
            pk = new PenaltyKick(k, gk);
            res = pk.shoot();
            results[res[0]][res[1]][res[2]]++;
        }
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(Arrays.toString(results[i][j]));
            }
            System.out.println();
        }
    }
}
