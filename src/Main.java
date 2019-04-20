import java.lang.reflect.Array;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        int y = scan.nextInt();
        PlayGround pg = new PlayGround(x, y);
        pg.makeAllO();
        // System.out.printf("\n1111\n3");
        int t = scan.nextInt();
        Polis[] polics = new Polis[t];
        for (int i = 0; i < t; i++) {
            polics[i] = new Polis(i);
            polics[i].randomPlace(x, y);
        }
        int time = 0 ;
        //System.out.printf("\n2222\n");
        boolean h = true;
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t; j++) {
                if ((polics[i].getX() == polics[j].getX()) && (polics[i].getY() == polics[j].getY())) {
                    polics[i].randomPlace(x, y);
                    polics[i].makeLast();
                }
            }
        }
        for (int i = 0; i < t; i++) {
            pg.pg[polics[i].getX()][polics[i].getY()] = 1;
        }
        thief d = new thief();
        boolean b = true;
        while (b) {
            int s = 1;
            d.randomPlace(x, y);
            for (int i = 0; i < t; i++) {
                if ((d.getX() == polics[i].getX()) && (d.getY() == polics[i].getY())) {
                    d.randomPlace(x, y);
                    s = 0;
                }
            }
            if (s == 1) {
                b = false;
            }
        }
        pg.pg[d.getX()][d.getY()] = (-1);
        boolean k = true;
        while (k) {
            System.out.printf("\n%d\n",time);
            time++;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (pg.pg[i][j] == 1) {
                        System.out.printf("P ");
                    }
                    if (pg.pg[i][j] == (-1)) {
                        System.out.printf("D ");
                    }
                    if (pg.pg[i][j] == 0) {
                        System.out.printf("- ");
                    }
                }
                System.out.printf("\n");
            }
            System.out.printf("\n\n\n");
            int v = 0;
            for (int i = 0; i < t; i++) {
                v = polics[i].ifSeen(pg);
            }
            //System.out.printf("\nif seen is ok");
            if (v >= 1) {
                for (int i = 0; i < t; i++) {
                    polics[i].makeLast();
                    //System.out.printf("\nproblem");
                    polics[i].goNear(d);
                    //System.out.printf("\nok\n");
                    pg.pg[polics[i].getLastX()][polics[i].getLastY()] = 0;
                    pg.pg[polics[i].getX()][polics[i].getY()] = 1;
                }
            } else {
                for (int i = 0; i < t; i++) {
                    polics[i].makeLast();
                    //System.out.printf("\nlast x = %d\nlast y = %d",polics[i].getX(),polics[i].getY());
                    polics[i].nextPlace(x,y);
                   //System.out.printf("\nlast x = %d\nlast y = %d",polics[i].getX(),polics[i].getY());
                    pg.pg[polics[i].getLastX()][polics[i].getLastY()] = 0;
                    pg.pg[polics[i].getX()][polics[i].getY()] = 1;
                }
            }
            //System.out.printf("\nokkkkk\n");
            pg.pg[d.getX()][d.getY()] = 0;
            d.nextPlace(x, y);
            pg.pg[d.getX()][d.getY()] = (-1);
            for (int i = 0; i < t; i++) {
                if ((d.getX() == polics[i].getX()) && (d.getY() == polics[i].getY())) {
              //      System.out.printf("\n finished wiyh 1111");
                    return;
                }
                if ((d.getX() == polics[i].getLastX()) && (d.getY() == polics[i].getLastY())) {
                //    System.out.printf("\nfinished with 22222");
                    return;
                }
            }

        }
    }
}

class thief {
    int x = 0;
    int y = 0;

    void randomPlace(int x, int y) {
        this.x = (int) (Math.random() * (x));
        this.y = (int) (Math.random() * (y));
    }

    void nextPlace(int x, int y) {
        int sit = 1;
        while (sit == 1) {
            int a = (int) ((Math.random() * (3)) - 1);
            int b = ((int) (Math.random() * (3)) - 1);
            this.x += a;
            this.y += b;
            if ((this.x >= 0)&&(this.x < x) && (this.y >= 0)&&(this.y < y)) {
                sit = 0;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

class Polis {
    int x;
    int y;
    int lastX;
    int lastY;
    int num;

    public Polis(int num) {
        this.num = num;
    }

    void randomPlace(int x, int y) {
        this.x = (int) (Math.random() * (x-1));
        this.y = (int) (Math.random() * (y-1));
    }

    void goNear(thief d) {
        if (d.getX() > x) {
            x++;
        }
        else {if (d.getX() < x) {
            x--;
        }
        }
        if (d.getY() > y) {
            y++;
        }
        else {if (d.getY() < y) {
            y--;
        }
        }
    }

    void nextPlace(int x, int y) {
        int sit = 1;
        while (sit == 1) {
            int a = (int) ((Math.random() * (3)) - 1);
            int b = ((int) (Math.random() * (3)) - 1);
            lastX = this.x;
            lastY = this.y;
            this.x += a;
            this.y += b;
            if ((this.x >= 0)&&(this.x < x) && (this.y >= 0)&&(this.y < y)) {
                sit = 0;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    void makeLast() {
        this.lastX =this.x;
        this.lastY = this.y;
    }

    int ifSeen(PlayGround pg) {
        for (int a = (x - 2); (a < (x + 3)); a++) {
            for (int b = (y - 2); b < (y + 3); b++) {
                if (((a>=0)&&(a<pg.getI()))&&((b>=0)&&(b<pg.getJ()))){
                    if (pg.pg[a][b] == (-1)) {
                        return 1;
                    }
                }

            }
        }
        return 0;
    }
}

class PlayGround {
    int pg[][];
    int i;
    int j;

    public PlayGround(int i, int j) {
        this.i = i;
        this.j = j;
        pg = new int[i][j];
    }

    void makeAllO() {
        for (int a = 0; a < i; a++) {
            for (int b = 0; b < j; b++) {
                pg[a][b] = 0;
            }
        }
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int[][] getPg() {
        return pg;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}