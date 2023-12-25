package models;

import models.pieces.*;

import java.util.*;

import static utils.Common.print;

public class Board implements Iterable<Square> {


    private Square[][] matrix;

    private Alliance current;

    private Stack<Move> moves;

    public Board() {
        reset();
    }

    public Square[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Square[][] matrix) {
        this.matrix = matrix;
    }

    public Stack<Move> getMoves() {
        return moves;
    }

    public Alliance getCurrent() {
        return current;
    }

    /*------------------
          Private
    --------------------*/



    /*------------------
          Public
    --------------------*/

    public void reset() {
        current = Alliance.WHITE;
        moves = new Stack<>();
        //init board
        matrix = new Square[8][8];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                matrix[row][column] = new Square(column, row, null);
            }
        }
        //init black alliance
        matrix[0][0].setPieces(new Rook(Alliance.BLACK));
        matrix[0][1].setPieces(new Knight(Alliance.BLACK));
        matrix[0][2].setPieces(new Bishop(Alliance.BLACK));
        matrix[0][3].setPieces(new Queen(Alliance.BLACK));
        matrix[0][4].setPieces(new King(Alliance.BLACK));
        matrix[0][5].setPieces(new Bishop(Alliance.BLACK));
        matrix[0][6].setPieces(new Knight(Alliance.BLACK));
        matrix[0][7].setPieces(new Rook(Alliance.BLACK));
        matrix[1][0].setPieces(new Pawn(Alliance.BLACK));
        matrix[1][1].setPieces(new Pawn(Alliance.BLACK));
        matrix[1][2].setPieces(new Pawn(Alliance.BLACK));
        matrix[1][3].setPieces(new Pawn(Alliance.BLACK));
        matrix[1][4].setPieces(new Pawn(Alliance.BLACK));
        matrix[1][5].setPieces(new Pawn(Alliance.BLACK));
        matrix[1][6].setPieces(new Pawn(Alliance.BLACK));
        matrix[1][7].setPieces(new Pawn(Alliance.BLACK));

        matrix[7][0].setPieces(new Rook(Alliance.WHITE));
        matrix[7][1].setPieces(new Knight(Alliance.WHITE));
        matrix[7][2].setPieces(new Bishop(Alliance.WHITE));
        matrix[7][3].setPieces(new Queen(Alliance.WHITE));
        matrix[7][4].setPieces(new King(Alliance.WHITE));
        matrix[7][5].setPieces(new Bishop(Alliance.WHITE));
        matrix[7][6].setPieces(new Knight(Alliance.WHITE));
        matrix[7][7].setPieces(new Rook(Alliance.WHITE));
        matrix[6][0].setPieces(new Pawn(Alliance.WHITE));
        matrix[6][1].setPieces(new Pawn(Alliance.WHITE));
        matrix[6][2].setPieces(new Pawn(Alliance.WHITE));
        matrix[6][3].setPieces(new Pawn(Alliance.WHITE));
        matrix[6][4].setPieces(new Pawn(Alliance.WHITE));
        matrix[6][5].setPieces(new Pawn(Alliance.WHITE));
        matrix[6][6].setPieces(new Pawn(Alliance.WHITE));
        matrix[6][7].setPieces(new Pawn(Alliance.WHITE));

//
//        test();
    }


    public Square square(int col, int row) {
        return matrix[row][col];
    }

    public boolean canHorizontalCrossing(Square start, Square end) {
        // check same row
        if (!start.isSameRow(end)) return false;

        //is col start greater than col end swap start and end
        if (start.getColumn() > end.getColumn()) {
            Square temp = start;
            start = end;
            end = temp;
        }
        // check cells between start and end
        int row = start.getRow();
        for (int column = start.getColumn() + 1; column < end.getColumn(); column++) {
            if (square(column, row).isNotBlank()) {
                return false;
            }
        }
        return true;
    }

    public boolean canVerticalCrossing(Square start, Square end) {
        // check same row
        if (!start.isSameCol(end)) return false;

        //is col start greater than col end swap start and end
        if (start.getRow() > end.getRow()) {
            Square temp = start;
            start = end;
            end = temp;
        }

        // check cells between start and end
        int column = start.getColumn();
        for (int row = start.getRow() + 1; row < end.getRow(); row++) {
            if (square(column, row).isNotBlank()) {
                return false;
            }
        }
        return true;
    }

    public boolean canDiagonalCrossing(Square start, Square end) {
        if (!start.isSameCross(end)) return false;
        int dCol = 1;
        int dRow = 1;
        if (start.getColumn() > end.getColumn())
            dCol = -1;
        if (start.getRow() > end.getRow())
            dRow = -1;
        int column = start.getColumn() + dCol;
        int row = start.getRow() + dRow;
        while (column != end.getColumn() && row != end.getRow()) {
            if (square(column, row).isNotBlank())
                return false;
            column += dCol;
            row += dRow;
        }
        return true;
    }

    public void printBoard() {
        print("\t");
        for (int i = 0; i < 8; i++) {
            print((char) ('A' + i) + "\t");
        }
        print("\n");
        int counter = 1;
        for (var row : matrix) {
            print(counter++ + "\t");
            for (var square : row) {
                if (square.isBlank()) {
                    print("--\t");
                } else {
                    print(square.getPieces().getSymbol() + "\t");
                }

            }
            print("\n");
        }

    }

    public List<Square> getValidMove(int x, int y) {
        List<Square> squares = new LinkedList<>();

        for (var square : this){
            if (square(x, y).getPieces().isValidMove(this, square(x, y), square)) {
                squares.add(square);
            }
        }
        return squares;
    }

    public List<Square> getValidEnd(Square start){
        List<Square> squares = new LinkedList<>();
        for (var end: this){
            if (start.getPieces().isValidMove(this, start, end)){
                squares.add(end);
            }
        }
        return squares;
    }



    /*------------------
          Override
    --------------------*/



    @Override
    public Iterator<Square> iterator() {
        return new Iterator<Square>() {
            private int row;
            private int col;

            @Override
            public boolean hasNext() {
                return row <= 7;
            }

            @Override
            public Square next() {
                Square square = square(col, row);
                col++;
                if (col > 7) {
                    col = 0;
                    row++;
                }

                return square;
            }
        };
    }

    public boolean makeMove(Move move) {
        Square start = move.getStart();
        Square end = move.getEnd();
        Piece selectedPiece = start.getPieces();
        if (selectedPiece == null) return false; // // valid selection
        if (!current.equals(selectedPiece.getAlliance())) return false; // valid piece
        if (!selectedPiece.isValidMove(this, start, end)) return false; // valid move

        //Capture
        Piece targetPiece = end.getPieces();
        if (targetPiece != null && !targetPiece.is(selectedPiece.getAlliance())) {
            targetPiece.setCaptured(true);
            move.setCaptured(targetPiece);
        }

        // castling?
        if (targetPiece != null && selectedPiece instanceof King king && king.isValidCastling(this, start, end)) {
            move.setCastlingMove(true);
            start.setPieces(end.getPieces());
            end.setPieces(king);
        } else {
            //crossing
            start.setPieces(null);
            end.setPieces(selectedPiece);
        }



        if (selectedPiece instanceof Pawn pawn && !pawn.isPromoted()) {
            if (pawn.is(Alliance.WHITE)) {
                if(end.getRow() == 0){
                    pawn.setPromotedTo(new Queen(Alliance.WHITE));
                    move.setPromoted(PieceType.QUEEN);
                }
            } else {
                if(end.getRow() == 7){
                    pawn.setPromotedTo(new Queen(Alliance.BLACK));
                    move.setPromoted(PieceType.QUEEN);

                }
            }
        }


//        if (targetPiece instanceof King king) {
//            status = king.is(Alliance.WHITE) ? GameStatus.BLACK_WIN : GameStatus.WHITE_WIN;
//        }

        //next turn
        selectedPiece.setMoved(true);
        current = current == Alliance.BLACK ? Alliance.WHITE : Alliance.BLACK;
        moves.push(move);
        return true;
    }

    public void unMode(){
        if (moves.isEmpty()) return;
        Move move = moves.pop();
        Square end = move.getEnd();
        Square start = move.getStart();
        Piece movedPiece = move.getMoved();
        Piece capturedPiece = move.getCaptured();
        current = move.getAlliance();
        if(move.isCastlingMove()){
            end.setPieces(start.getPieces());
            start.setPieces(movedPiece);
            ((King)movedPiece).setCastlingDone(false);
            end.getPieces().setMoved(false);
            start.getPieces().setMoved(false);
        } else {
            start.setPieces(movedPiece);
            end.setPieces(capturedPiece);
            movedPiece.setMoved(moves.stream().anyMatch((move1 -> move1.getMoved().equals(movedPiece))));
        }
        if(move.getPromoted() != null && movedPiece instanceof Pawn pawn){
            pawn.setPromotedTo(null);
        }


    }

    public List<Move> getLegalMove(Alliance alliance) {
        List<Move> moves = new LinkedList<>();
        for (Square start : this){
            if(start.isNotBlank() && start.getPieces().is(alliance)){
                moves.addAll(
                        getValidEnd(start).stream().map((end) -> new Move(alliance,start, end)).toList()
                );
            }
        }
        return moves;
    }

    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(String.join("\n", board.getLegalMove(Alliance.WHITE).stream().map(Move::toString).toList()));
    }


    public boolean makeMove(int startRow, int startCol, int endRow, int endCol) {
        return makeMove(new Move(current, square(startCol, startRow), square(endCol, endRow)));
    }
}


