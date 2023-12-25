package models.pieces;

import models.Alliance;
import models.Board;
import models.Square;

public class King extends Piece {

    private boolean castlingDone = false;

    public King(Alliance alliance) {
        super(alliance);
        this.pieceType = PieceType.KING;

    }

    public boolean isCastlingDone() {
        return castlingDone;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    @Override
    public boolean isValidMove(Board board, Square start, Square end) {
        if (isValidCastling(board, start, end))
            return true;
        //check attack alliance's piece
        if(!end.isBlank() && end.getPieces().is(alliance)){
            return false;
        }

        //check step
        int x = Math.abs(start.getColumn() - end.getColumn()); // vector x
        int y = Math.abs(start.getRow() - end.getRow()); // vector y
        if (x + y == 1) return true;
        return x == 1 && y == 1;
    }

    @Override
    public String getSymbol() {
        return is(Alliance.WHITE) ? "♔" : "♚";
    }

    public boolean isValidCastling(Board board, Square start, Square end){
        Square[][] matrix = board.getMatrix();

        // check castling have done
        if(castlingDone || isMoved) return false;
        if(!board.canHorizontalCrossing(start, end))
            return false;
        if(alliance == Alliance.WHITE){
            if(matrix[7][7].equals(end) || matrix[7][0].equals(end)){
                return end.isNotBlank() && end.getPieces() instanceof Rook rook && rook.is(Alliance.WHITE) && !rook.isMoved;
            }

        } else {
            if(matrix[0][0].equals(end) || matrix[0][7].equals(end)){
                return end.isNotBlank() && end.getPieces() instanceof Rook rook && rook.is(Alliance.BLACK) && !rook.isMoved;
            }
        }

        return false;

    }

    public boolean isCastlingMove() {
        return false;
    }
}
