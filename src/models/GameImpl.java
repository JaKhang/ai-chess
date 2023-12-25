package models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GameImpl implements Game {
    private final Player[] players;
    private final Board board;
    private Player current;
    private GameStatus status;
    private final List<Move> moves;

    private Consumer<Player> handleChangePlayer;
    public GameImpl() {
        players = new Player[2];
        board = new Board();
        this.status = GameStatus.ACTIVE;
        moves = new ArrayList<>();
    }

    @Override
    public boolean makeMove(Move move) {
        boolean success = board.makeMove(move);
        if (success){
            current = current == players[0] ? players[1] : players[0];
            if(handleChangePlayer != null)
                handleChangePlayer.accept(current);

        }

        System.out.println(move);
        return success;
    }

    @Override
    public Move getLastMove() {
        if (moves.isEmpty())
            return null;
        return moves.get(moves.size() - 1);
    }

    @Override
    public boolean makeMove(int startRow, int startCol, int endRow, int endCol) {
        boolean success = board.makeMove(startRow, startCol, endRow, endCol);
        if (success){
            current = current == players[0] ? players[1] : players[0];
            if(handleChangePlayer != null)
                handleChangePlayer.accept(current);
        }

        return success;
    }

    @Override
    public void onChangePlayer(Consumer<Player> callBack) {
        handleChangePlayer = callBack;
    }

    @Override
    public boolean isEnd() {
        return this.getStatus() != GameStatus.ACTIVE;
    }

    @Override
    public void initialize(Player p1, Player p2) {
        players[0] = p1;
        players[1] = p2;
        board.reset();
        current = p1.is(Alliance.WHITE) ? p1 : p2;
        moves.clear();
    }

    @Override
    public List<Move> getMoves() {
        return moves;
    }

    @Override
    public GameStatus getStatus() {
        return status;
    }

    @Override
    public void printBoard() {
        board.printBoard();
    }

    @Override
    public List<Square> getCanMove(int row, int col) {
        return board.getValidMove(col, row);
    }

    @Override
    public Player getCurrentPlayer() {
        return current;
    }

    @Override
    public Board getBoard() {
        return board;
    }

}
