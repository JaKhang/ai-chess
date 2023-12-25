import controller.Controller;
import models.Game;
import models.GameImpl;
import view.GameBoardUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Game game = new GameImpl();
        GameBoardUI gameBoardUI = new GameBoardUI();
        Controller controller = new Controller();
        gameBoardUI.setController(controller);
        controller.setGame(game);
        controller.setGameBoardUI(gameBoardUI);
        controller.init();

        JFrame frame = new JFrame();
        frame.setContentPane(gameBoardUI);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
