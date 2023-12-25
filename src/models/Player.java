package models;

import ai.AI;
import ai.CompoundHeuristics;
import ai.EvalHeuristics;
import ai.Minimax;

public class Player {
    private String name;
    private Alliance alliance;
    private boolean isHuman;
    private AI ai;

    public boolean is(Alliance alliance){
        return alliance.equals(this.alliance);
    }

    public Player(Alliance alliance, boolean isHuman) {
        this.alliance = alliance;
        this.isHuman = isHuman;
        if(!isHuman){
            ai = new AI(new Minimax(alliance, 4, new CompoundHeuristics()));
        }
    }



    public boolean isHuman() {
        return this.isHuman;
    }

    public AI getAi() {
        return ai;
    }

    public Alliance getSet() {
        return alliance;
    }

    public Alliance getAlliance() {
        return alliance;
    }
}
