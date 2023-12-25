package ai;

import models.Alliance;
import models.Board;
import models.Move;

public class Minimax {

    private Alliance aiAlliance;

    private int depth;

    public Minimax(Alliance aiAlliance, int depth, Heuristics heuristics) {
        this.aiAlliance = aiAlliance;
        this.depth = depth;
        this.heuristics = heuristics;
    }

    public Heuristics heuristics;
    public int minimax(Alliance alliance, Board state, int depth, int alpha, int beta){
        if(depth == 0){
//            state.printBoard();
            return heuristics.get(state);
        }
        //Max state
        if(this.aiAlliance.equals(alliance)){
            int maxScore = Integer.MIN_VALUE;

            for (Move nextMove: state.getLegalMove(alliance)){
                state.makeMove(nextMove);
                int score = minimax(Alliance.next(alliance), state, depth - 1, alpha, beta);
                state.unMode();
                maxScore = Integer.max(score, maxScore);
                alpha = Math.max(alpha, score);
//                if (beta <= alpha) break;
            }
            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            for (Move nextMove: state.getLegalMove(alliance)){
                state.makeMove(nextMove);
                int score = minimax(Alliance.next(alliance), state, depth - 1, alpha, beta);
                state.unMode();
                minScore = Integer.min(score, minScore);
                beta = Math.min(alpha, score);
//                if (beta <= alpha) break;

            }
            return minScore;
        }
    }

    public Move getBestMove(Board board){
        Move best = null;
        int bestScore = Integer.MIN_VALUE;

        for (var move: board.getLegalMove(aiAlliance)){
            board.makeMove(move);
            int score = minimax(Alliance.next(aiAlliance), board, depth - 1,Integer.MAX_VALUE, Integer.MIN_VALUE);
//            System.out.println("Score: " + score);
            board.unMode();
            if (bestScore < score){
                bestScore = score;
                best = move;
            }
        }

        return best;
    }





    public static void main(String[] args) {

    }
}
