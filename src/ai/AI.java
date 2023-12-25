package ai;

import models.Board;
import models.Move;

public class AI {
    private Board board;
    private Minimax minimax;

    public AI(Minimax minimax) {
        this.board = new Board();
        board.reset();
        this.minimax = minimax;
    }

    public AI(Board board, Minimax minimax) {
        this.board = board;
        this.minimax = minimax;
    }

    public AI() {
        board = new Board();
        board.reset();
    }

    public void makeEnemyMove(Move move) {
        if (move == null) return;
        board.makeMove(move.getStart().getRow(), move.getStart().getColumn(), move.getEnd().getRow(), move.getEnd().getColumn());
        board.printBoard();
    }


    public Move getBestMove() {
        Move bestMove = minimax.getBestMove(board);
        board.makeMove(bestMove);
        return bestMove;
    }

    public void print(){
        board.printBoard();
    }
    public static void main(String[] args) {
        new AI();
    }
}
