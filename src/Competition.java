import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class Competition {
    private static int numpen = 10;
    private Player[] players; // The master players array
    private int N;
    private int[][][][][] stats; // g1, p2
    int[][] kab;
    int[][] gab;

    public Competition(Player[] p, int[][] kabilities, int[][] gabilities, int np_per_game) {
        N = p.length;
        numpen = np_per_game;
        players = p;
        stats = new int[N][N][3][3][4];
        kab = kabilities;
        gab = gabilities;
        // stats[kicker index][goalie index][kside][gside][miss/save/goals]
        // stats[7][5][2][1] means number of goals when player 7 as kicker shoots to player 5 as goalie to the right.
        // stats[7][5][1][0] means number of total shots player 7 as kicker shoots to player 5 as goalie to the middle.
    }

    public static int getNp() {return numpen;}

    public int getStat(int kickerid, int goalieid, int kside, int gside, int tot_or_goal) {
        return stats[kickerid][goalieid][kside][gside][tot_or_goal];
    }

    public void run() {

        System.out.println("-----Akdeniz Game Theory Penalty Tournament-----");
        Match[][] matches = new Match[N][N];
        int[][][] tempresult;
        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {

                matches[i][j] = new Match(players[i],players[j],kab[i],gab[j],numpen);
                tempresult = matches[i][j].play();
                addStats(i,j,tempresult,stats);

                matches[j][i] = new Match(players[j],players[i],kab[j],gab[i],numpen);
                tempresult = matches[j][i].play();
                addStats(j,i,tempresult,stats);

            }
        }
    }

    private void addStats(int k, int g, int[][][] tempresult, int[][][][][] stats) {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                for(int m=0; m<4; m++) {
                    stats[k][g][i][j][m] = tempresult[i][j][m];
                }
            }
        }
    }

    private void reportResults() {
        for(int k=0; k<N; k++) {
            for(int g=k+1; g<N; g++) {
                System.out.println(players[k].getName()+" vs "+players[g].getName()+": "+stats[k][g][0][0][3]+" : "+stats[g][k][0][0][3]);
                for(int i=0; i<3; i++) {
                    for(int j=0; j<3; j++) {
                        for(int m=0; m<3; m++) {

                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int NUMPLAYERS=6;
        Player[] exampleplayers = new Player[NUMPLAYERS];
        int[][] kabilities = new int[NUMPLAYERS][3];
        int[][] gabilities = new int[NUMPLAYERS][3];
        URL url = exampleplayers.getClass().getResource("players.txt");
        try {
            File file = new File(url.getPath());
            Scanner reader = new Scanner(file);
            int i=0;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                //System.out.println(data);
                String[] d = data.split(" ");
                exampleplayers[i] = new Player(d[0]);
                for(int j=0; j<3; j++) kabilities[i][j]=Integer.parseInt(d[1+j]);
                for(int j=0; j<3; j++) gabilities[i][j]=Integer.parseInt(d[4+j]);
                System.out.println(exampleplayers[i].getName()+" K: "+ Arrays.toString(kabilities[i])+" G: "+ Arrays.toString(gabilities[i]));
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        exampleplayers[4] = new PlayerMurat();
        kabilities[4][0]=60;
        kabilities[4][1]=60;
        kabilities[4][2]=60;
        gabilities[4][0]=60;
        gabilities[4][1]=60;
        gabilities[4][2]=60;

        exampleplayers[5] = new PlayerAgainstMurat();
        kabilities[5][0]=60;
        kabilities[5][1]=60;
        kabilities[5][2]=60;
        gabilities[5][0]=60;
        gabilities[5][1]=60;
        gabilities[5][2]=60;


        Competition GameTheory2020 = new Competition(exampleplayers, kabilities, gabilities, 10000);
        GameTheory2020.run();
        GameTheory2020.reportResults();
    }


}
