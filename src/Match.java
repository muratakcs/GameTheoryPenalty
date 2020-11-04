public class Match {
    Goalkeeper gk;
    Kicker k;
    double[] probsave;
    int numpen;
    public Match(Goalkeeper gkeep, Kicker kick, int numpenalties) {
        gk = gkeep;
        k = kick;
        probsave = new double[3];
        for(int i=0; i<3; i++)
        probsave[i] = gk.a[i] / (gk.a[i]+k.a[i]);
        numpen = numpenalties;
    }

    public int process() {
        int scores = 0;

        return scores;
    }

}
