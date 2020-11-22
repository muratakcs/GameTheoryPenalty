import java.util.Arrays;

public class Match {
    private static final String[] kactions = {"kicks to the left", "kicks to the middle", "kicks to the right"};
    private static final String[] gactions = {"jumps to the left", "stays in the middle", "jumps to the right"};
    private static final String[] yield = {"the kicker misses!", "the goalie saves!", "it's a goal!"};
    private Player k,g; //kicker and goalkeeper
    private int[] kab,gab; //k and g abilities
    private int np;
    private boolean commentary = false;
    private Competition comp; // A match must be aware of which competition it is in.
    private int[][][] result;
    public Match(Player pa, Player pb, int[] pa_abilities, int[] pb_abilities, int numpen, Competition c) {
        k=pa;
        g=pb;
        kab = pa_abilities; // L M R shooting
        gab = pb_abilities; // L M R saving
        np = numpen;
        comp = c;
        result = new int[3][3][4]; // indices: [kicker's side][goalie's side][miss/save/goal] counts
    }

    public int getMatchStats(int kside, int gside, int type) {
        return result[kside][gside][type];
    }

    public int[][][] play() {
        if(commentary)System.out.println("Kicker: "+k.getName() + " --- vs --- Goalie: "+g.getName());
        // The fourth index (index 3) of the last dimension is for score.
        // Only one cell used: result[0][0][3] is the number of goals.

        PenaltyKick kick;

        int res[];

        for(int i=0; i<np; i++) {

            // We set the penalty kick up.
            kick = new PenaltyKick(k, g, kab, gab, comp, this);

            // Actually making the kick:
            res = kick.shoot();

            if(commentary)System.out.println(k.getName()+" "+kactions[res[0]] + " and "+g.getName()+" "+gactions[res[1]]+" "+yield[res[2]]);
            result[res[0]][res[1]][res[2]]++; // updating result.

            if(res[2]==2) result[0][0][3]++; // increment goals if scored.
        }
        if(commentary)System.out.println(k.getName()+ " makes "+result[0][0][3]+" of "+np+" shoots!");
        return result;
    }


    public static void main(String[] args) {
        System.out.println("Testing match class");
        Player k = new Player("Ronaldinho");
        Player g = new Player("Schmeichel");
        int[] kab = {80, 70, 60};
        int[] gab = {50, 40, 30};
        Match m = new Match(k, g, kab, gab, 5,null);
        int[][][] score = m.play();
        System.out.println("Kicker makes "+score[0][0][3]+" of "+5+" shoots!");
    }

}
