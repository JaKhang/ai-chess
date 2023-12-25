package ai;

import models.Alliance;
import models.Board;
import models.Square;

public class CapturesHeuristics implements Heuristics{

    @Override
    public int get(Board state) {
        int score = 0;

        for (Square square : state){
            if (square.isNotBlank() && square.getPieces().is(state.getCurrent()))
                score += Config.CAPTURED_SCORE.get(square.getPieces().getPieceType());
        }

        for (Square square : state){
            if (square.isNotBlank() && square.getPieces().is(Alliance.next(state.getCurrent())))
                score -= Config.CAPTURED_SCORE.get(square.getPieces().getPieceType());
        }



        return score;
    }
}
