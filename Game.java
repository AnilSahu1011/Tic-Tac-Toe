package Tic_Tac_Toe;

import java.util.Scanner;
class TicTacToe{
    static char[][] board;

    public TicTacToe() {
        board = new char[3][3];
        initializeBoard();
    }
    void initializeBoard(){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                board[i][j] = ' ';
            }
        }
    }
    static void displayBoard(){
        System.out.println("-------------");
        for(int i=0; i<board.length; i++){
            System.out.print("| ");
            for(int j=0; j<board[i].length; j++){
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    static void placeMark(int row, int col, char symbol){
        if( row>=0 && row<=2 && col>=0 && col<=2 ){
            board[row][col] = symbol;
        }
        else {
            System.out.println("Invalid input");
        }
    }

    static boolean checkColumnWin(){
        for (int j=0;j<=2;j++){
            if(board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]){
                return true;
            }
        }
        return false;
    }

    static boolean checkRowWin(){
        for (int i=0;i<=2;i++){
            if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagonalWin(){
        if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
                || (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) ) {
            return true;
        }
        return false;
    }

    static boolean isFull(){
        for (int i=0;i< board.length; i++){
            for (int j=0;j< board[0].length; j++){
                if(board[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }
}

interface Player{
    void makeMove();
    boolean isValidMove(int row, int col);
}
class HumanPlayer implements Player{

    String name;
    char mark;

    HumanPlayer(String name, char mark){
        this.name = name;
        this.mark = mark;
    }

    public void makeMove(){
        Scanner sc = new Scanner(System.in);
        int row,col;
        System.out.println("Enter the Row and Column: ");
        do
        {
            row = sc.nextInt();
            col = sc.nextInt();
        }while (!isValidMove(row,col));
        TicTacToe.placeMark(row,col,mark);
    }

    public boolean isValidMove(int row, int col){
        if(row>=0 && row<=2 && col>=0 && col<=2){
            if(TicTacToe.board[row][col] == ' '){
                return true;
            }
        }
        return false;
    }
}

public class Game {
    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        HumanPlayer p1 = new HumanPlayer("Anil",'X');
        HumanPlayer p2 = new HumanPlayer("Mama",'O');

        HumanPlayer currentPlayer;
        currentPlayer = p1;

        while (true){
            System.out.println(currentPlayer.name + " Turn");
            currentPlayer.makeMove();
            TicTacToe.displayBoard();
            if(TicTacToe.checkRowWin() || TicTacToe.checkColumnWin() || TicTacToe.checkDiagonalWin()) {
                System.out.println(currentPlayer.name + " has Won ! ! !");
                break;
            }
            else if (TicTacToe.isFull()) {
                System.out.println("Nobody Won\nEnd...");
                break;
            }
            else{
                if(currentPlayer==p1){
                    currentPlayer = p2;
                }
                else{
                    currentPlayer = p1;
                }
            }
        }
    }
}
