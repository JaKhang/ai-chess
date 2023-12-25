package controller;

import ai.AI;
import models.*;
import view.GameBoardUI;
import view.SquareStatus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import static utils.Common.print;

public class Controller{
    private Game game;
    private GameBoardUI gameBoardUI;
    private Square focusSquare;

    public void init() {
        gameBoardUI.init();
        game.initialize(
                new Player(Alliance.WHITE, true),
                new Player(Alliance.BLACK, false)
        );
        gameBoardUI.startGame(game.getBoard().getMatrix());
//        game.onChangePlayer((player -> {
//            if(player.isHuman()) return;
//            System.out.println("Make move " + game.getBoard().getCurrent());
//            makeAiMove();
//            player.getAi().print();
//        }));
    }

    public void resetGame(){
        game.initialize(
                new Player(Alliance.WHITE, true),
                new Player(Alliance.BLACK, true)
        );
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setGameBoardUI(GameBoardUI gameBoardUI) {
        this.gameBoardUI = gameBoardUI;
    }

    public void handleSquareClick(Square square) {
        if(!game.getCurrentPlayer().isHuman())
            return;
        if (game.isEnd()) return;

        if (focusSquare == null) {
            Alliance currentAlliance = game.getCurrentPlayer().getSet();
            if (square.isNotBlank() && square.getPieces().is(currentAlliance)) {
                focus(square);
            }
            return;
        }

        Move move = new Move(game.getCurrentPlayer().getSet(),focusSquare, square);
        boolean success = game.makeMove(move);
        removeFocus(square);
        gameBoardUI.repaint();


        if(game.isEnd()){
            print(game.getStatus());
        }
        game.printBoard();
        if(success){
            if(!game.getCurrentPlayer().isHuman()){
                game.getCurrentPlayer().getAi().makeEnemyMove(move);
                makeAiMove();
//
            }
        }
    }

    private void makeAiMove() {
        Thread thread = new Thread(() -> {
            long start = System.currentTimeMillis();
            AI ai1 = game.getCurrentPlayer().getAi();
            Move move = ai1.getBestMove();
            game.makeMove(move.getStart().getRow(), move.getStart().getColumn(), move.getEnd().getRow(), move.getEnd().getColumn());
            gameBoardUI.repaint();
            System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
        });
        thread.start();
    }

    private void removeFocus(Square square){
        focusSquare = null;
        gameBoardUI.setSquareStatus(square, SquareStatus.NONE);
        gameBoardUI.clearHint();
    }

    private void focus(Square square){
        focusSquare = square;
        gameBoardUI.setSquareStatus(square, SquareStatus.ACTIVE);
        gameBoardUI.renderHint(game.getCanMove(square.getRow(), square.getColumn()));
    }


}
