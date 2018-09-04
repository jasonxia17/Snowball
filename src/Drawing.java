public class Drawing {
    public static void picture(Player[] pList){

        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        gridLines();

        for(int i = 0; i < Game.numOfPlayers; ++i) {
            pList[i].draw(i);
        }

        StdDraw.show();
    }
    
    private static void gridLines(){
        for(int i = 0; i <= Game.gridSize; i++) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(i, 0, i, Game.gridSize);
            StdDraw.line(0, i, Game.gridSize, i);
        }
    }

    public static void gridSetup(){
        StdDraw.setCanvasSize(900, 900);
        double bottomBuffer = Game.numOfPlayers + 1;
        double sideBuffer = (bottomBuffer + 1)/2;
        StdDraw.setXscale(-sideBuffer, Game.gridSize + sideBuffer);
        StdDraw.setYscale(-bottomBuffer, Game.gridSize + 1);
    }

}
