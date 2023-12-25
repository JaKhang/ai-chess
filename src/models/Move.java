package models;

import models.pieces.Piece;
import models.pieces.PieceType;

public class Move {
    private Alliance alliance;
    private Square start;
    private Square end;
    private Piece moved;
    private Piece captured;
    private boolean castlingMove;
    private PieceType promoted;

    public Move(Alliance alliance, Square start, Square end) {
        this.alliance = alliance;
        this.start = start;
        this.end = end;
        this.moved = start.getPieces();
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public void setAlliance(Alliance alliance) {
        this.alliance = alliance;
    }

    public Square getStart() {
        return start;
    }

    public void setStart(Square start) {
        this.start = start;
    }

    public Square getEnd() {
        return end;
    }

    public void setEnd(Square end) {
        this.end = end;
    }

    public Piece getMoved() {
        return moved;
    }

    public void setMoved(Piece moved) {
        this.moved = moved;
    }

    public Piece getCaptured() {
        return captured;
    }

    public void setCaptured(Piece captured) {
        this.captured = captured;
    }

    public boolean isCastlingMove() {
        return castlingMove;
    }

    public void setCastlingMove(boolean castlingMove) {
        this.castlingMove = castlingMove;
    }

    public PieceType getPromoted() {
        return promoted;
    }

    public void setPromoted(PieceType promoted) {
        this.promoted = promoted;
    }

    @Override
    public String toString() {
        return "Move{" +
                "alliance=" + alliance +
                ", start=" + start +
                ", end=" + end +
                ", moved=" + moved +
                ", captured=" + captured +
                ", castlingMove=" + castlingMove +
                ", promoted=" + promoted +
                '}';
    }
}
