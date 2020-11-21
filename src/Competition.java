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

    public Competition(Player[] p, int[][] kabilities, int[][] gabilities, int np_per_game) {
        N = p.length;
        numpen = np_per_game;
        players = p;
        stats = new int[N][N][3][3][2];
        // stats[kicker index][goalie index][kside][gside][tot/goals]
        // stats[7][5][2][1] means number of goals when player 7 as kicker shoots to player 5 as goalie to the right.
        // stats[7][5][1][0] means number of total shots player 7 as kicker shoots to player 5 as goalie to the middle.
    }

    public static int getNp() {return numpen;}

    public int getStat(int kickerid, int goalieid, int kside, int gside, int tot_or_goal) {
        return stats[kickerid][goalieid][kside][gside][tot_or_goal];
    }

    public void run() {

    }

    public static void main(String[] args) {
        Player[] players = new Player[4];
        int[][] kabilities = new int[4][3];
        int[][] gabilities = new int[4][3];
        URL url = players.getClass().getResource("players.txt");
        try {
            File file = new File(url.getPath());
            Scanner reader = new Scanner(file);
            int i=0;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                //System.out.println(data);
                String[] d = data.split(" ");
                players[i] = new Player(d[0]);
                for(int j=0; j<3; j++) kabilities[i][j]=Integer.parseInt(d[1+j]);
                for(int j=0; j<3; j++) gabilities[i][j]=Integer.parseInt(d[4+j]);
                System.out.println(players[i].getName()+" K: "+ Arrays.toString(kabilities[i])+" G: "+ Arrays.toString(gabilities[i]));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Competition GameTheory2020 = new Competition(players, kabilities, gabilities, 5);
        GameTheory2020.run();
    }
}
