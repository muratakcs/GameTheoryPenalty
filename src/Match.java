public class Match {
    Player p1;
    Player p2;
    public Match(Player pa, Player pb) {
        p1 = pa;
        p2 = pb;
    }

    public int[] process() {
        int score1 = 0;
        int score2 = 0;
        PenaltyKick kick;
        int[] result;
        for(int i=0; i<Competition.getNp(); i++) {
            System.out.println("GK: "+ p1.name + " K: "+p2.name);
            kick = new PenaltyKick(p2, p1);
            result = kick.shoot();

            System.out.println("GK: "+ p2.name + " K: "+p1.name);
            kick = new PenaltyKick(p1, p2);
            result = kick.shoot();
        }
        int[] score = {score1, score2};
        return score;
    }

}
