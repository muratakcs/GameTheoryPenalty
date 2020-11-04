public class Match {
    Goalkeeper gk;
    Kicker k;
    int numpen;
    public Match(Goalkeeper gkeep, Kicker kick, int numpenalties) {
        gk = gkeep;
        k = kick;
        numpen = numpenalties;
    }

    public int process() {
        int kickside;
        int jumpside;
        int score = 0;
        for(int i=0 ;i<numpen; i++) {
            kickside = k.decide();
            jumpside = gk.decide();
            if(kickside!=jumpside) {
                score++;
            }
        }
        return score;
    }

}
