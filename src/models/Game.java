package models;

import java.util.List;
import java.util.function.Consumer;

public interface Game {
    boolean isEnd();
    void initialize(Player p1, Player p2);
    List<Move> getMoves();
    GameStatus getStatus();

    void printBoard();
    List<Square> getCanMove(int row, int col);
    Player getCurrentPlayer();
    Board getBoard();

    boolean makeMove(Move move);

    Move getLastMove();

    boolean makeMove(int startRow, int startCol, int endRow, int endCol);

    void onChangePlayer(Consumer<Player> callBack);
}
