package org.grouptwentyone;

import org.grouptwentyone.controllers.GameController;
import org.grouptwentyone.controllers.PlayerController;
import org.grouptwentyone.controllers.UserTerminationController;
import org.grouptwentyone.models.Player;
import org.grouptwentyone.views.GameSetupView;
import org.grouptwentyone.views.GameUiView;
import org.grouptwentyone.views.GameView;

import java.util.ArrayList;

public class StartGame {


    public static void start() {


        int numOfPlayers = GameSetupView.getNumberOfPlayersFromUser();
        ArrayList<Player> playerList = GameSetupView.getPlayerNamesFromUser(numOfPlayers);

        PlayerController playerController = new PlayerController(playerList);
        playerController.shufflePlayerList();
        GameSetupView.displayPlayerOrder(playerList);

        Player activePlayer = playerController.getFirstPlayer();


        while (true) {

            GameUiView.printPageBorder();

            System.out.printf("%s⏺ %s ⏺\n\n%s", GameUiView.WHITE_BOLD_BRIGHT, activePlayer.getUserName(), GameUiView.RESET_COLOUR);

            System.out.println("VIEW OF HABITAT GOES HERE");
            System.out.println("VIEW OF CARD OPTIONS GOES HERE\n");

            GameUiView.printPageBorder();

            String userInput = GameView.askUserForInput();
            GameController.UserAction userAction = GameController.getUserActionFromInput(userInput);

            switch (userAction) {
                case HELP:
                    GameView.showHelpPage();
                    break;
                case EXIT:
                    UserTerminationController.endProgram();
                    GameUiView.printLargeSpace();
                    break;
                case DEV__PRINT_ACTIVE_PLAYER:
                    System.out.println(activePlayer);
                    break;
                case NEXT_PLAYER:
                    System.out.println("Moved to next player");
                    activePlayer = playerController.cycleToNextPlayer();
                    GameUiView.printLargeSpace();
                    break;
                case INVALID_COMMAND:
                    GameView.setIsPreviousInputInvalid(true);
                    GameView.setPreviousInvalidInput(userInput);
                    GameUiView.printLargeSpace();
            }



        }


    }
}
