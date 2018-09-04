import java.util.Arrays;

public class Turn {
    public static int indexOfNextPlayer(int i){
        return (i + 1) % Game.numOfPlayers;
    }

    public static void turn(int i, Player[] pList){

        Player crntPlayer = pList[i];

        StdOut.println("\n\n\n\nPlayer " + (i+1) + "'s Turn\n");

        // Prompts for player's command

        StdOut.println("COMMANDS: left, right, up, down, throw, make, heal");
        boolean valid;

        do {
            StdOut.print("Type your command here: ");
            String command = StdIn.readString().toLowerCase();
            StdIn.readLine();

            // Menu
            switch (command) {
                case "left":
                    valid = crntPlayer.moveLeft(pList);
                    break;
                case "right":
                    valid = crntPlayer.moveRight(pList);
                    break;
                case "down":
                    valid = crntPlayer.moveDown(pList);
                    break;
                case "up":
                    valid = crntPlayer.moveUp(pList);
                    break;

                case "throw":
                    if (Game.numOfPlayers == 2){
                        int target = indexOfNextPlayer(i);
                        valid = crntPlayer.throwBall(pList[target]);
                        break;
                    }

                    StdOut.print("Which player is your target? ");
                    String input = StdIn.readString();
                    StdIn.readLine();

                    String[] options = {"1", "2", "3", "4"};

                    if (Arrays.asList(options).contains(input)){
                        int target = Integer.parseInt(input) - 1;
                        valid = crntPlayer.throwBall(pList[target]);
                    } else valid = false;
                    break;
                case "make":
                    valid = crntPlayer.make();
                    break;
                case "heal":
                    valid = crntPlayer.heal();
                    break;
                default:
                    valid = false;
                    break;
            }
            if (!valid) StdOut.println("INVALID COMMAND!");

        } while(!valid);
    }
}
