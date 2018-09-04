public class Game {
    public static final int maxBall = 4;
    public static final int maxHealth = 20;
    public static final int damage = 4;
    public static final int gridSize = 9;
    public static final int numOfPlayers = 4;
    public static final int heal = 1;

    public static final boolean luck = true;


    public static boolean gameOver(Player[] pList) {
        int aliveCount = 0;
        for(int i = 0; i < Game.numOfPlayers; i++){
            if (pList[i].isAlive()) aliveCount++;
        }
        if (aliveCount == 1) return true;
        return false;
    }

    public static boolean teamGameOver(Player[] pList) {
        if ((!pList[0].isAlive() && !pList[3].isAlive() || (!pList[1].isAlive() && !pList[2].isAlive()))){
            return true;
        } else return false;
    }

    public static int winner(Player[] pList) {
        for(int i = 0; i < Game.numOfPlayers; i++){
            if (pList[i].isAlive()) return (i+1);
        }
        return 0;
    }

    public static void main (String[] args){

        Player[] pList = new Player[Game.numOfPlayers];

        //Starting conditions

        int lower = 2;
        int upper = gridSize - lower;
        pList[0] = new Player(lower, lower, maxBall, maxHealth, StdDraw.RED, false);
        pList[1] = new Player(lower, upper, maxBall, maxHealth, StdDraw.BLUE, false);
        pList[2] = new Player(upper, upper, maxBall, maxHealth, StdDraw.MAGENTA, false);
        pList[3] = new Player(upper, lower, maxBall, maxHealth, StdDraw.PRINCETON_ORANGE, false);

        // i is the index of the current player
        int i = 0;

        Drawing.gridSetup();

        while(!teamGameOver(pList)) {

            if (pList[i].isAlive()) {
                if (pList[i].inProgress){
                    StdOut.println("\n\n\n\nPlayer " + (i+1) + " finished making a snowball.");
                    pList[i].inProgress = false;
                }
                else {
                    Drawing.picture(pList);
                    pList[i].dotOnCurrent();
                    StdDraw.show();

                    Turn.turn(i, pList);
                }
            }

            i = Turn.indexOfNextPlayer(i);
        }

        Drawing.picture(pList);
        StdOut.println("\n\n\n\nGame Over");
        // StdOut.println("Player " + winner(pList) + " is the winner!");
    }
}