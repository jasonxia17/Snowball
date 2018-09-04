public class SnowballOld {
    private static int invalid(int player){
        StdOut.println("INVALID COMMAND!\nPlease enter a different command.");

        // This cancels out the player switch at the end of the while
        // so that program is still on the same player.
        return (player + 1) % 2;
    }

    private static boolean throwSuccess(int[][] position){
        double distance = Math.sqrt(Math.pow(position[0][0]-position[1][0], 2) // continued
                + Math.pow(position[0][1]-position[1][1], 2));
        double probability = Math.exp(-0.2 * distance);
        if(Math.random() < probability){
            StdAudio.play("Splat.wav");
            StdDraw.pause(1000);
            return true;
        }
        else{
            StdAudio.play("Swoosh.wav");
            StdDraw.pause(1000);
            return false;
        }
    }

    private static void picture(int[] health, int[][] position, int[] ballNum, int gridSize){
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();

        // Draws Grid Lines
        StdDraw.setPenColor(StdDraw.BLACK);

        StdDraw.setXscale(-2,gridSize + 2);
        StdDraw.setYscale(-2,gridSize + 2);

        for(int i = 0; i <= gridSize; i++) {
            StdDraw.line(i, 0, i, gridSize);
            StdDraw.line(0, i, gridSize, i);
        }

        //Draws Player Positions and Stats
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(position[0][0], position[0][1],0.4*health[0]/20 + 0.1);
        StdDraw.text(gridSize/2.0, -1, "Health: " + health[0] + "/20"
                + "                 # of Snowballs: " + ballNum[0] + "/4");

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(position[1][0], position[1][1],0.4*health[1]/20 + 0.1);
        StdDraw.text(gridSize/2.0,  gridSize + 1, "Health: " + health[1] + "/20"
                + "                 # of Snowballs: " + ballNum[1] + "/4");

        StdDraw.show();
    }

    public static void main (String[] args){
        // Declares starting variables
        int gridSize = 5;
        int[] health = {20, 20};
        int[][] position = {{0,0},{gridSize,gridSize}};
        int[] ballNum = {4,4};
        int player = 0;

        picture(health, position, ballNum, gridSize);

        while(health[0]>0 && health[1]>0) {

            int[] posBeforeMove = new int[2];
            posBeforeMove[0] = position[player][0];
            posBeforeMove[1] = position[player][1];

            StdOut.println("\n\n\n\nPlayer " + (player+1) + "'s Turn\n");

            // Prompts for player's command
            StdOut.println("COMMANDS: left, right, up, down, throw, make, heal");
            StdOut.print("Type your command here: ");
            String command = StdIn.readString();

            // Menu
            switch (command.toLowerCase()) {
                case "left":
                    if (position[player][0] == 0) player = invalid(player);
                    else position[player][0]--;
                    break;
                case "right":
                    if (position[player][0] == gridSize) player = invalid(player);
                    else position[player][0]++;
                    break;
                case "down":
                    if (position[player][1] == 0) player = invalid(player);
                    else position[player][1]--;
                    break;
                case "up":
                    if (position[player][1] == gridSize) player = invalid(player);
                    else position[player][1]++;
                    break;

                case "throw":
                    if (ballNum[player] == 0) player = invalid(player);
                    else {
                        ballNum[player]--;
                        if (throwSuccess(position)) health[(player + 1) % 2] -= 3;
                    }
                    break;
                case "make":
                    if (ballNum[player] == 4) player = invalid(player);
                    else ballNum[player]++;
                    break;
                case "heal":
                    if (health[player] == 20) player = invalid(player);
                    else health[player]++;
                    break;
                default:
                    player = invalid(player);
                    break;

            }
            // Prevents both players from moving to the same position
            if(position[0][0] == position[1][0] && position[0][1] == position[1][1]){
                position[player][0] = posBeforeMove[0];
                position[player][1] = posBeforeMove[1];
                player=invalid(player);
            }

            picture(health, position, ballNum, gridSize);

            // Switches player
            player = (player + 1) % 2;
        }
        StdOut.println("Game Over");
    }
}