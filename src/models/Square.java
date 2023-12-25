package models;

import models.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public class Square {

    private static final Map<Integer, Integer> ROW_NAME = new HashMap<>();
    private static final Map<Integer, String> COL_NAME = new HashMap<>();

    static {
        for (int i = 0; i < 8; i++) {
            ROW_NAME.put(i, i + 1);
            COL_NAME.put(i, String.valueOf((char) ('A' + i)));
        }
    }

    private Piece piece;
    private int column;
    private int row;

    public Square(int column, int row, Piece piece) {
        this.piece = piece;
        this.column = column;
        this.row = row;
    }


    public Piece getPieces() {
        return piece;
    }

    public void setPieces(Piece piece) {
        this.piece = piece;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    /*------------------
           public
    --------------------*/
    public boolean isBlank() {
        return piece == null;
    }

    public boolean isNotBlank() {
        return piece != null;
    }

    boolean isSameRow(Square that) {
        return this.row == that.row;
    }

    boolean isSameCol(Square that) {
        return this.column == that.column;
    }

    boolean isSameCross(Square that) {
        return Math.abs(this.column - that.column) == Math.abs(this.row - that.row);
    }

    @Override
    public String toString() {
        return COL_NAME.get(column) + ROW_NAME.get(row);
    }

    public String getSymBol() {
        if (isBlank()) {
            return "---";
        } else {
            return piece.toString();
        }
    }
}
