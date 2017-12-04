import java.io.*;
import java.util.*;

public class Checkers {
    private static int size=9;
    private char checkersboard[][];
    private int whitecheckers;
    private int blackcheckers;
    private char Moves;

    public Checkers(){
        checkersboard= new char[size][size];
        whitecheckers =12;
        blackcheckers=12;
        Moves='●';

        for(int y=1; y<size; y++)
            for (int x=1; x<size; x++)
                checkersboard[x][y]='-';
        for(int y=2; y<size; y+=2){
            checkersboard[y][2]='●';
            checkersboard[y][6]='○';
            checkersboard[y][8]='○';
        }
        for (int y=1; y<size; y+=2)
        {
            checkersboard[y][1]='●';
            checkersboard[y][3]='●';
            checkersboard[y][7]='○';
        }
    }
    public void drawBoard(){
        System.out.print("y");
        System.out.print("  1 2 3 4 5 6 7 8 x\n");
        for (int y=1; y<size; y++){
            System.out.print(y+" ");
            System.out.print(" ");
            for (int x=1; x<size; x++){

                System.out.print(checkersboard[x][y]+" ");
            }
            System.out.println();
        }

    }
    public void MoveCheckers() {
        Scanner sc = new Scanner(System.in);
        if (Moves == '●')
            System.out.println("Balto eilė -●-");
        else
            System.out.println("Juodo eile -○- ");
        boolean moved = false;
        while (!moved) {
            System.out.println("Iš kur norite pajudėti.");
            System.out.println("Įveskite 2 skaičius ,x yra stulpeliai y yra eilutės ");
            System.out.println("Formatas xy");
            try {
                int x = sc.nextInt();
                System.out.println("Į kur norite pajudėti.");
                System.out.println("Įveskite 2 skaičiu");
                int moveto = sc.nextInt();
                if (CorrectMove(x, moveto)) {
                    Killmove(x, moveto);
                    moved = true;
                } else {
                    drawBoard();
                    System.out.println("NETEISINGAS ŽINGSNIS!!.");
                }
            }catch (Exception e){
                System.out.println("----*****ĮVESTAS NETINKAMAS SIMBOLIS*****----");
                break;
            }
            if (Moves == '●')
                Moves = '○';
            else
                Moves = '●';
        }
    }
    public boolean CorrectMove(int from, int to)
    {
        int fromX=from/10;
        int fromY=from%10;
        int toX=to/10;
        int toY=to%10;

        if(fromX<1 || fromX>8 || fromY<1 || fromY>8 || toX<1 || toX>8 || toY<1 || toY>8)
            return false;
        else if (checkersboard[fromX][fromY]==Moves && checkersboard[toX][toY]=='-') {

            if (Math.abs(fromX - toX) == 1) {
                if ((Moves == '●') && (toY - fromY == 1))
                    return true;
                else if ((Moves == '○') && (toY - fromY == -1))
                    return true;

            }
            else if (Math.abs(fromX-toX)==2) {
                if (Moves == '●' && (toY - fromY == 2) &&
                        checkersboard[(fromX+toX)/2][(fromY+toY)/2] == '○')
                    return true;
                else if (Moves == '○' && (toY - fromY == -2) &&
                        checkersboard[(fromX+toX)/2][(fromY+toY)/2] == '●')
                    return true;
            }
        }
        return false;
    }

    public void Killmove(int from, int to)
    {
        int fromX=from/10;
        int fromY=from%10;
        int toX=to/10;
        int toY=to%10;
        checkersboard[fromX][fromY]='-';
        checkersboard[toX][toY]=Moves;
        if (Math.abs(toX - fromX) == 2) {
            checkersboard[(fromX+toX)/2][(fromY+toY)/2] = '-';
            if (Moves == '●')
                blackcheckers--;
            else
                whitecheckers--;
        }
    }
    public boolean gameOver() {
        return (whitecheckers == 0 || blackcheckers == 0);
    }

    public String Winner() {
        if (blackcheckers == 0)
            return "Balti";
        else
            return "Juodi";
    }
    public static void main(String args[]) throws IOException {
        Checkers board=new Checkers();
        board.drawBoard();
        while (!board.gameOver()) {

            board.MoveCheckers();
            board.drawBoard();
        }
        System.out.println("Laimėjo " + board.Winner());
    }
}
