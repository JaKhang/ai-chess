package view;

import models.Alliance;
import models.pieces.PieceType;
import utils.ImageUtil;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static utils.Common.print;

public class ImageFactory {

    private static ImageFactory instance;

    public static ImageFactory get(){
        if (instance == null)
            instance = new ImageFactory();
        return instance;
    }

    private final Map<String, Image> imageMap;
    private ImageFactory(){
        imageMap = new HashMap<>();

        put("white-square.png", Alliance.WHITE, SquareStatus.NONE);
        put("white-square-active.png", Alliance.WHITE, SquareStatus.ACTIVE);
        put("white-square-hint.png", Alliance.WHITE, SquareStatus.HINT);
        put("white-square-hint-nb.png", Alliance.WHITE, SquareStatus.HINT_PIECE);
        put("white-pawn.png", Alliance.WHITE, PieceType.PAWN);
        put("white-rook.png", Alliance.WHITE, PieceType.ROOK);
        put("white-bishop.png", Alliance.WHITE, PieceType.BISHOP);
        put("white-knight.png", Alliance.WHITE, PieceType.KNIGHT);
        put("white-king.png", Alliance.WHITE, PieceType.KING);
        put("white-queen.png", Alliance.WHITE, PieceType.QUEEN);

        put("black-square.png", Alliance.BLACK, SquareStatus.NONE);
        put("black-square-active.png", Alliance.BLACK, SquareStatus.ACTIVE);
        put("black-square-hint.png", Alliance.BLACK, SquareStatus.HINT);
        put("black-square-hint-nb.png", Alliance.BLACK, SquareStatus.HINT_PIECE);
        put("black-pawn.png", Alliance.BLACK, PieceType.PAWN);
        put("black-rook.png", Alliance.BLACK, PieceType.ROOK);
        put("black-bishop.png", Alliance.BLACK, PieceType.BISHOP);
        put("black-knight.png", Alliance.BLACK, PieceType.KNIGHT);
        put("black-king.png", Alliance.BLACK, PieceType.KING);
        put("black-queen.png", Alliance.BLACK, PieceType.QUEEN);


        print(getPicecImage(PieceType.BISHOP, Alliance.WHITE).getClass().getName());

    }

    public Image getPicecImage(PieceType pieceType, Alliance alliance){
        return get(alliance, pieceType);
    }

    public Image getSquareImage(Alliance alliance, SquareStatus status){
        return get(alliance, status);
    }

    private void put(String imageName, Object... keys){
        imageMap.put(
                createKey(keys),
                ImageUtil.loadingImage(imageName, 1)
        );
    }

    private Image get(Object... keys){
        return imageMap.get(createKey(keys));
    }

    private String createKey(Object... objects){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < objects.length; i++) {
            stringBuilder.append(objects[i]);
            if(i != objects.length - 1){
                stringBuilder.append("_");
            }
        }
        return stringBuilder.toString();

    }
}
