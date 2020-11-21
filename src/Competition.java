import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Competition {
    private static int np = 10;
    private Player[] players;
    private int N;
    private int[][][][] stats; // g1, p2

    public Competition(Player[] p, int np_per_game) {
        N = p.length;
        np = np_per_game;
        players = p;
        stats = new int[N][N][3][2];
        // stats[kicker index][goalie index][side][tot/goals]
        // stats[7][5][2][1] means number of goals when player 7 as kicker shoots to player 5 as goalie to the right.
        // stats[7][5][1][0] means number of total shots player 7 as kicker shoots to player 5 as goalie to the middle.
    }

    public static int getNp() {return np;}

    public int getStat(int kickerid, int goalieid, int side, int tot_or_goal) {
        return stats[kickerid][goalieid][side][tot_or_goal];
    }

    public static void main(String[] args) {
        Player[] players = new Player[9];
        try {
            File myObj = new File("players.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //Competition GameTheory2020 = new Competition();
    }
}
