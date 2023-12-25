package view;

import models.Alliance;
import models.Square;

import javax.swing.*;
import java.awt.*;

public class SquareUI extends JButton {
    private Square square;
    private SquareStatus state = SquareStatus.NONE;
    private Image[] images;

    private boolean isWhite;

    public SquareUI(Square square, boolean isWhite) {
        this.square = square;
        images = new Image[3];
        setPreferredSize(new Dimension(72, 72));
        setBorderPainted(false);
        this.isWhite = isWhite;
//        if (isWhite) {
//            images[0] = ImageUtil.loadingImage("white-square.png", 1);
//            images[1] = ImageUtil.loadingImage("white-square-active.png", 1);
//            images[2] = ImageUtil.loadingImage("white-square-hint.png", 1);
//        } else {
//            images[0] = ImageUtil.loadingImage("black-square.png", 1);
//            images[1] = ImageUtil.loadingImage("black-square-active.png", 1);
//            images[2] = ImageUtil.loadingImage("black-square-hint.png", 1);
//        }

    }

    public SquareUI(boolean isWhite) {
        this(null, isWhite);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
//        int currentImage = 0;
//
//        if(state == SquareStatus.ACTIVE){
//            currentImage = 1;
//        } else if (state == SquareStatus.HINT){
//            currentImage = 2;
//        }
//
//        g2.drawImage(images[currentImage], 0, 0, null);
//        g2.drawImage(images[currentImage], 0, 0, null);
//
//
//        if(square == null) return;
//
//        if (square.isNotBlank()) {
//            String img = String.format(
//                    "%s-%s.png",
//                    square.getPieces().getSet().toString().toLowerCase(),
//                    square.getPieces().getPieceType().toString().toLowerCase()
//            );
//            Image image = ImageUtil.loadingImage(img, 1);
//            g2.drawImage(image, 9, 9, null);

//        }


        Image image = ImageFactory.get().getSquareImage(
                isWhite ? Alliance.WHITE : Alliance.BLACK,
                state
        );
        g2.drawImage(image, 0, 0, null);
        if (square.isNotBlank()) {
            Image piece = ImageFactory.get().getPicecImage(
                    square.getPieces().getPieceType(),
                    square.getPieces().getAlliance()
            );


            g2.drawImage(piece, 9, 9, null);
        }

    }

    /*------------------
          Getter
    --------------------*/
    public void setState(SquareStatus state) {
        if (state == this.state)
            return;
        this.state = state;
        this.repaint();
    }

    public Square getSquare() {
        return square;
    }

    void setSquare(Square square) {
        this.square = square;
    }
}
