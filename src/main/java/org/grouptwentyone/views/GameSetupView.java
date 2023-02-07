package org.grouptwentyone.views;

import org.grouptwentyone.controllers.UserTerminationController;
import org.grouptwentyone.models.Player;

import java.util.*;

public class GameSetupView {

    public static int getNumberOfPlayersFromUser() {
        Scanner sc = new Scanner(System.in);

        GameUiView.printPageBorder();
        System.out.println("Enter Number of Players");

        String userInput;
        int numberOfPlayers = -1;

        // Keep asking until user enters a valid int or exit program (returns -1).
        while (true) {
            userInput = sc.nextLine();

            UserTerminationController.checkUserInputForProgramTermination(userInput);

            try {
                numberOfPlayers = Integer.parseInt(userInput);

                if (numberOfPlayers > 4 || numberOfPlayers < 2) {
                    GameUiView.printLargeSpace();
                    GameUiView.printPageBorder();

                    System.out.printf("%sInvalid Input:%s Please enter a number between 2-4 OR type \"quit\" to exit the program%s\n",
                            GameUiView.RED_BOLD, GameUiView.RED, GameUiView.RESET_COLOUR);
                } else {
                    return numberOfPlayers;
                }

            } catch (NumberFormatException ex) {

                GameUiView.printLargeSpace();
                GameUiView.printPageBorder();

                System.out.printf("%sInvalid Input:%s Please enter a number between 2-4 OR type \"quit\" to exit the program%s\n",
                        GameUiView.RED_BOLD, GameUiView.RED, GameUiView.RESET_COLOUR);
            }
        }

    }

    public static ArrayList<Player> getPlayerNamesFromUser(int numberOfPlayers) {
        ArrayList<Player> playerList = new ArrayList<>();
        String playerName;

        Scanner sc = new Scanner(System.in);

        GameUiView.printLargeSpace();
        GameUiView.printPageBorder();
        System.out.println("Player Names");

        for (int i=0; i<numberOfPlayers; i++) {
            System.out.printf("Enter \"Player %d\" name: ", i + 1);
            playerName = sc.nextLine();

            if (Objects.equals(playerName, "")) {
                System.out.printf("\n%sInvalid Input:%s A player cannot have an empty name%s\n", GameUiView.RED_BOLD, GameUiView.RED, GameUiView.RESET_COLOUR);
                i--;
                continue;
            }

            playerList.add(new Player(playerName));
        }

        GameUiView.printLargeSpace();

        return playerList;
    }

    public static void displayPlayerOrder(ArrayList<Player> playerList) {

        StringBuilder output = new StringBuilder();
        GameUiView.printPageBorder();

        System.out.println("Players will play in this order:");

        for (int i=0; i<playerList.size(); i++) {
            output.append(String.format("%d. %s\n", i+1, playerList.get(i).getUserName()));
        }

        System.out.println(output);

        GameUiView.printPageBorder();
        System.out.println("Press \"ENTER\" on your keyboard to continue");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        GameUiView.printLargeSpace();

    }
}
