import java.awt.*;

public class Player {
    public int x, y, health;
    public int ballNum;
    public Color color;
    public boolean inProgress;


    public Player(int xPosition, int yPosition, int numberOfBalls, int healthLevel,
                  Color playerColor, boolean ballInProgress) {
        x = xPosition;
        y = yPosition;
        ballNum = numberOfBalls;
        health = healthLevel;
        color = playerColor;
        inProgress = ballInProgress;
    }

    private static void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return ("Position: (" + x + ", " + y + "), Number of Balls: " + ballNum + ", Health: " + health);
    }

    public boolean isAlive(){
        if (health > 0) return true;
        else return false;
    }

    private boolean overlap(Player[] pList) {
        for(int i = 0; i < Game.numOfPlayers; i++){
            if ((this != pList[i]) && (pList[i].x == x) && (pList[i].y == y)){
                return true;
            }
        }
        return false;
    }

    // The methods below return false if the action is invalid

    public boolean moveLeft(Player[] pList) {
        if (x > 0) {
            x--;
            if (overlap(pList)){
                x++;
                return false;
            }
            return true;
        } else return false;
    }

    public boolean moveRight(Player[] pList) {
        if (x < Game.gridSize) {
            x++;
            if (overlap(pList)) {
                x--;
                return false;
            }
            return true;
        } else return false;
    }

    public boolean moveDown(Player[] pList) {
        if (y > 0) {
            y--;
            if (overlap(pList)) {
                y++;
                return false;
            }
            return true;
        } else return false;
    }

    public boolean moveUp(Player[] pList) {
        if (y < Game.gridSize) {
            y++;
            if (overlap(pList)) {
                y--;
                return false;
            }
            return true;
        } else return false;
    }

    public boolean make() {
        if (ballNum < Game.maxBall) {
            ballNum++;
            inProgress = true;
            return true;
        } else return false;
    }

    public boolean throwBall(Player target) {
        if (ballNum > 0 && this != target && target.isAlive()) {
            ballNum--;
            if (Game.luck) {
                if (throwOnTarget(x, y, target.x, target.y)) {
                    target.wasHit();
                    StdAudio.play("Splat.wav");
                } else {
                    StdAudio.play("Swoosh.wav");
                }
            } else{
                target.throwDamage(x, y, target.x, target.y);
                StdAudio.play("Splat.wav");
            }
            delay(500);
            return true;

        } else return false;
    }

    public boolean heal() {
        if (health < Game.maxHealth) {
            health += Game.heal;
            return true;
        } else return false;
    }

    private void wasHit() {
        health -= Game.damage;
    }

    private static double probability(Double distance){
        return Math.exp(-0.2 * distance);
    }

    private static boolean throwOnTarget(int x1, int y1, int x2, int y2) {
        int xDiff = x1 - x2;
        int yDiff = y1 - y2;
        double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
        double probability = probability(distance);
        return (Math.random() < probability);
    }

    private void throwDamage(int x1, int y1, int x2, int y2) {
        int xDiff = x1 - x2;
        int yDiff = y1 - y2;
        double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
        double damageFactor = probability(distance);
        health -= (int) (Game.damage * damageFactor);
    }

    public void draw(int i){
        StdDraw.setPenColor(color);
        StdDraw.setFont(new Font ("Sans Serif", Font.PLAIN, 36));
        StdDraw.filledCircle(x, y, 0.4 * health / Game.maxHealth + 0.15);

        if (this.isAlive()) {
            StdDraw.text(Game.gridSize / 2.0, -1 - i, "Health: " + health + "/" + Game.maxHealth +
                    "              # of Snowballs: " + ballNum + "/" + Game.maxBall);
        }
    }

    public void dotOnCurrent(){
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledCircle(x, y, 0.07);
    }

    public static void main(String[] args){
        Player player = new Player(0, 0, 5, 7, StdDraw.RED, false);
    }
}