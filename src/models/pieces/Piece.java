package models.pieces;

import models.Alliance;
import models.Board;
import models.Square;

import java.util.Objects;

public abstract class Piece {
    protected Alliance alliance;
    protected boolean isCaptured;
    protected boolean isMoved;
    protected PieceType pieceType;

    public Piece(Alliance alliance) {
        this.alliance = alliance;
    }

    public abstract boolean isValidMove(Board board, Square start, Square end);

    public abstract String getSymbol();


    /*------------------
          Public
    --------------------*/
    public boolean is(Alliance alliance) {
        return Objects.equals(alliance, this.alliance);
    }

    @Override
    public String toString() {
        return getSymbol();
    }

    /*------------------
          Protected
    --------------------*/
    protected boolean isAttackTeammate(Square end) {
        return !end.isBlank() && end.getPieces().is(alliance);
    }

    /*------------------
        Getter Setter
    --------------------*/

    public boolean isCaptured() {
        return isCaptured;
    }

    public void setCaptured(boolean captured) {
        isCaptured = captured;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
