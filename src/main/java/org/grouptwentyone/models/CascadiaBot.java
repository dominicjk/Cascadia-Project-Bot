package org.grouptwentyone.models;

import org.grouptwentyone.StartGame;
import org.grouptwentyone.views.BoardView;
import org.grouptwentyone.views.GameUiView;
import org.grouptwentyone.views.SelectionOptionsView;
import org.grouptwentyone.views.UserInputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CascadiaBot extends Player{

    static boolean displayBotActions = true;

    public CascadiaBot(String userName) {
        super(userName);
        this.getPlayerBoardObject().setVerbose(false);
    }


    @Override
    public boolean playTurn() {
//        System.out.println("Do bot stuff\n\n");
//        System.out.println(BoardView.displayTiles(this.getPlayerBoardObject()));
//        System.out.println("\n\n");


        ArrayList<Tile> placeableTileOptionsList = this.getPlayerBoardObject().getPlaceableTileOptionList();

        ArrayList<HabitatTile> habitatTileOptionList = StartGame.selectedTiles;
        ArrayList<WildlifeToken> wildlifeTokensOptionList = StartGame.selectedTokens;


        int highestScore = -1;
        Tile tilePositionThatGivesHighestScore = placeableTileOptionsList.get(RandomNumberGenerator.getRandomNumberInRange(0, placeableTileOptionsList.size()-1));
//        System.out.println(tilePositionThatGivesHighestScore);

        HexCoordinate wildlifeTokenPositionThatGivesHighestScore =
                this.getPlayerBoardObject().getPlaceableWildlifeTokenList().get(0).getField1();

        // We copy the habitat tile by value before playing around with it so the original tile is not affected.
        HabitatTile habitatTileFromSelectedTiles = habitatTileOptionList.get(0);
        HabitatTile habitatTileHighScore = new HabitatTile(habitatTileFromSelectedTiles);

        // Copying wildlife token by value
        WildlifeToken wildlifeTokenFromSelectedTokens = wildlifeTokensOptionList.get(0);
        WildlifeToken wildlifeTokenHighScore = new WildlifeToken(wildlifeTokenFromSelectedTokens.getWildlifeTokenType());

        int indexOfSelectedTileAndTokenPair = 0;

        // Going to be 4 tile/token pair options so we run the inner loop 4 times.
        for (int i=0; i<4; i++) {

            for (Tile placeableTilePosition : placeableTileOptionsList) {
                PlayerBoard duplicateBoard = this.getPlayerBoardObject().getDuplicate();

                // Place possible tile
                HabitatTile habitatTileFromSelectedTiles2 = habitatTileOptionList.get(i);
                HabitatTile selectedHabitatTile = new HabitatTile(habitatTileFromSelectedTiles2);
                duplicateBoard.setSelectedTile(selectedHabitatTile);


                HexCoordinate placeableTileHexCoord = placeableTilePosition.getHexCoordinate();
                duplicateBoard.addNewTile(new HexCoordinate(placeableTileHexCoord.getX(), placeableTileHexCoord.getY()));

                ArrayList<CustomPair<HexCoordinate, WildlifeToken.WildlifeTokenType>> placeableWildlifeTokenList = duplicateBoard.getPlaceableWildlifeTokenList();


                for (CustomPair<HexCoordinate, WildlifeToken.WildlifeTokenType> placeableToken: placeableWildlifeTokenList) {

                    PlayerBoard duplicateBoard2 = duplicateBoard.getDuplicate();

                    duplicateBoard2.setSelectedToken(new WildlifeToken(placeableToken.getField2()));
                    HexCoordinate placeableTokenPosition = placeableToken.getField1();
                    duplicateBoard2.addNewToken(placeableTokenPosition);

                    int localScore = duplicateBoard2.getScore();
                    if (localScore > highestScore) {

                        highestScore = localScore;

                        // Reset Wildlife Token in Habitat Tile that was placed when testing for the highest score
                        habitatTileOptionList.get(i).setWildlifeToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.EMPTY));
                        habitatTileHighScore = habitatTileOptionList.get(i);
//                        System.out.printf("Habitat Tile has wildlife token of %s\n", habitatTileHighScore.toString(true));
                        tilePositionThatGivesHighestScore = placeableTilePosition;

                        wildlifeTokenHighScore = new WildlifeToken(placeableToken.getField2());
                        wildlifeTokenPositionThatGivesHighestScore = placeableTokenPosition;

                        indexOfSelectedTileAndTokenPair = i;

                    }


                }

            }
        }

//        System.out.println(BoardView.displayTiles(this.getPlayerBoardObject()));

//        System.out.printf("Placing tile %s at position %s\n", habitatTileHighScore, tilePositionThatGivesHighestScore.getHexCoordinate());
        this.getPlayerBoardObject().setSelectedTile(habitatTileHighScore);
        this.getPlayerBoardObject().addNewTile(tilePositionThatGivesHighestScore.getHexCoordinate());


//        System.out.println(BoardView.displayTiles(this.getPlayerBoardObject()));

//        System.out.printf("Placing token %s at position %s\n", wildlifeTokenHighScore, wildlifeTokenPositionThatGivesHighestScore);
        this.getPlayerBoardObject().setSelectedToken(wildlifeTokenHighScore);
        this.getPlayerBoardObject().addNewToken(wildlifeTokenPositionThatGivesHighestScore);

        StartGame.selectedTokens.remove(indexOfSelectedTileAndTokenPair);
        StartGame.selectedTiles.remove(indexOfSelectedTileAndTokenPair);

//        GameUiView.printPageBorder();

//        GameUiView.printPlayerHeader(this);


//        boolean test = false;
//        for (int i = 0; i < 4; i++) {
//            if (StartGame.selectedTokens.get(i).equals(wildlifeTokenHighScore) && StartGame.selectedTiles.get(i).equals(habitatTileHighScore)) {
//                StartGame.selectedTokens.remove(i);
//                StartGame.selectedTiles.remove(i);
//                test = true;
//                break;
//            }
//        }
//        if (test == false) throw new IllegalArgumentException("removal of selected tile/token pair failed");


        if (displayBotActions) {
            // Displays the bot's playerboard
            System.out.println(BoardView.displayTiles(this.getPlayerBoardObject()));

            // String to display the habitats of the habitat tiles
            StringBuilder placedHabitatTile = new StringBuilder();
            if (habitatTileHighScore.getHabitatTileTypeList().size() == 1) {
                placedHabitatTile.append(habitatTileHighScore.getHabitatTileTypeList().get(0));
            } else {
                placedHabitatTile.append(habitatTileHighScore.getHabitatTileTypeList().get(0));
                placedHabitatTile.append(" & ");
                placedHabitatTile.append(habitatTileHighScore.getHabitatTileTypeList().get(1));
            }

            System.out.printf("""
                            Player "%s" has a score of %d
                            - placed a %s habitat tile on position %s.
                            - placed a %s wildlife token on position %s
                                                    
                            """,
                    this.getUserName(),
                    this.getScore(),
                    placedHabitatTile,
                    tilePositionThatGivesHighestScore.getHexCoordinate(),
                    wildlifeTokenHighScore.getWildlifeTokenType(),
                    wildlifeTokenPositionThatGivesHighestScore
            );

            System.out.println("Press \"ENTER\" on your keyboard to continue or press \"1\" to disable bot action description.");
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine().toLowerCase().trim();

            if (userInput.equals("1")) {
                displayBotActions = false;
            }

            GameUiView.printLargeSpace();
        }

        //detects that no tiles remain so ends player turns
        if (!SelectionOptionsView.replaceTileAndToken()) {
            StartGame.tilesRemain = false;
        }


        // Will never return false as the bot will never want to quit the game ... hopefully
        return true;
    }
}
