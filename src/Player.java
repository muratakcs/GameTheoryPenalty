import java.util.Arrays;
import java.util.Random;

public class Player {
    private String name;

    // pr is the crucial variable that a player needs to determine before each penalty kick.
    // It should be carefully set obeying game theoretical rules when deciding which side to select.
    // Once pr is set to certain values, the player randomly selects the side according to pr.
    double[] pr; // Probability of jumping sides 0:left 1:middle 2:right


    public Player(String nm) {
        name = nm;
        pr = new double[3];
        pr[0]=0.40;
        pr[1]=0.20;
        pr[2]=0.40;
    }

    public String briefIntro() {
        return "Hi, my name is "+name+", I always choose sides with these probabilities: 40% to left, 35% to right, 25% to middle.";
    }

    // Decide method just uses the probabilities calculated above. Do not change this method.
    public int decide(boolean isKicker, Player opponent) {

        //Here, set pr values before each penalty kick carefully...
        pr[0]=0.40;
        pr[1]=0.25;
        pr[2]=0.35;


        // You do not need to modify the rest, it just selects the sides depending on pr values.
        Random rand =new Random();
        int x = rand.nextInt(1000);
        if(x<1000*pr[0]) return 0; //left;
        if(x<1000*(pr[0]+pr[1])) return 1; //middle;
        return 2; //right;
    }


    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Player p = new Player("DefaultPlayer");
        System.out.println(p.getName()+ " intro: "+p.briefIntro());
        int[] counter = new int[3];
        for(int i=0; i<100000; i++) {
            counter[p.decide(false,p)]++;
        }
        System.out.println(Arrays.toString(counter));
    }
}