public class Match {
    Team t1;
    Team t2;
    public Match(Team ta, Team tb) {
        t1 = ta;
        t2 = tb;
    }

    public int[] process() {
        int score1 = 0;
        int score2 = 0;
        PenaltyKick kick;
        int[] result;
        for(int i=0; i<Competition.NUMPEN; i++) {
            System.out.println("GK: "+ t1.gk.name + " K: "+t2.k.name);
            kick = new PenaltyKick(t2.k, t1.gk);
            result = kick.shoot();

            System.out.println("GK: "+ t2.gk.name + " K: "+t1.k.name);
            kick = new PenaltyKick(t1.k, t2.gk);
            result = kick.shoot();
        }
        int[] score = {score1, score2};
        return score;
    }

}
