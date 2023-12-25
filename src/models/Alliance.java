package models;

public enum Alliance {
    WHITE,
    BLACK;

    public static Alliance next(Alliance alliance){
        return alliance == WHITE ? BLACK : WHITE;
    }



}
