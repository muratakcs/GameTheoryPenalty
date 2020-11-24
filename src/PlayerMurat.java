import java.util.Arrays;
import java.util.Random;

public class PlayerMurat extends Player{

    public PlayerMurat() {
        super("MuratAk");
    }


    public String briefIntro() {
        return "Hi, my name is Murat, I always shoot and jump to the right!";
    }


    // Decide method just uses the probabilities calculated above. Do not change this method.
    public int decide(boolean isKicker, Player opponent, Competition comp, Match match) {

        // (Shooting always to the same side... Not a good strategy...
        // Especially because when your opponents learn this strategy
        // they can adapt their strategy and get a huge advantage.)
        pr[0]=0;
        pr[1]=0;
        pr[2]=1.0;


        // You do not need to modify the rest, it just selects the sides depending on pr values.
        /*Random rand =new Random();
        double x = rand.nextDouble();
        if(x<pr[0]) return 0; //left;
        if(x<(pr[0]+pr[1])) return 1; //middle;
        return 2; //right;*/

        //Since the probability is fixed, we can return right immediately...
        return 2;

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
