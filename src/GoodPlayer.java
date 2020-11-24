import java.util.Arrays;
import java.util.Random;

public class GoodPlayer extends Player{
    public GoodPlayer(String nm) {
        super(nm);
    }

    public String briefIntro() {
        return "Hi, my name is "+this.getName()+", I'm a strategic thinker and always play intelligently by looking at the stats so far.";
    }


    public int decide(boolean isKicker, Player opponent, Competition comp, Match match) {

        // Since I'm a good player, I will look at the stats with my opponent first.
        // Example stats retrieval:

        //System.out.println("When I shoot to left and "+opponent.getName()+" jumps to left I missed this many times:"+match.getMatchStats(0,0,1));

        pr[0]=0.4;
        pr[1]=0.2;
        pr[2]=0.4;


        // You do not need to modify the rest, it just selects the sides depending on pr values.
        Random rand =new Random();
        double x = rand.nextDouble();
        if(x<pr[0]) return 0; //left;
        if(x<(pr[0]+pr[1])) return 1; //middle;
        return 2; //right;



    }


    public static void main(String[] args) {
        Player p = new PlayerMurat();
        System.out.println(p.getName()+ " intro: "+p.briefIntro());
        int[] counter = new int[3];
        for(int i=0; i<100000; i++) {
            counter[p.decide(false,p,null,null)]++;
        }
        System.out.println(Arrays.toString(counter));
    }
}
