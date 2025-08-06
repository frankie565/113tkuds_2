import java.util.Scanner;

public class TicTacToeBoard {
    private static final char EMPTY = '.';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private static char[][] board = new char[3][3];


    public static void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = EMPTY;
    }

 
    public static void printBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" ");
            }
            System.out.println();
        }
    }


    public static boolean placeMark(int row, int col, char player) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) return false;
        if (board[row][col] != EMPTY) return false;
        board[row][col] = player;
        return true;
    }

 
    public static boolean checkWin(char player) {
        
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player))
                return true;
        }

     
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player))
            return true;

        return false;
    }

    public static boolean isDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == EMPTY)
                    return false;
        return true;
    }

  
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeBoard();

        char currentPlayer = PLAYER_X;

        while (true) {
            printBoard();
            System.out.println("玩家 " + currentPlayer + " 請輸入行列 (0-2)：");
            int row = sc.nextInt();
            int col = sc.nextInt();

            if (!placeMark(row, col, currentPlayer)) {
                System.out.println("此位置無效，請重新輸入！");
                continue;
            }

            if (checkWin(currentPlayer)) {
                printBoard();
                System.out.println("玩家 " + currentPlayer + " 獲勝！");
                break;
            }

            if (isDraw()) {
                printBoard();
                System.out.println("平手！");
                break;
            }

            
            currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
        }

        sc.close();
    }
}
