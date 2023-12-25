package models.pieces;

import models.Alliance;
import models.Board;
import models.Square;

import java.util.Objects;

public class Rook extends Piece {
    public Rook(Alliance alliance) {
        super(alliance);
        this.pieceType = PieceType.ROOK;

    }

    @Override
    public boolean isValidMove(Board board, Square start, Square end) {
        if(!end.isBlank() && Objects.equals(end.getPieces().alliance, this.alliance)) return false;

        return board.canVerticalCrossing(start, end) || board.canHorizontalCrossing(start, end);

    }

    @Override
    public String getSymbol() {
        return is(Alliance.WHITE) ? "♖" : "♜";
    }
}
