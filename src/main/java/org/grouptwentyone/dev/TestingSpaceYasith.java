package org.grouptwentyone.dev;

import org.grouptwentyone.StartGame;
import org.grouptwentyone.models.*;
import org.grouptwentyone.views.BoardView;
import org.grouptwentyone.views.GameSetupView;
import org.grouptwentyone.views.GameUiView;
import org.grouptwentyone.views.SelectionOptionsView;

import java.util.ArrayList;
import java.util.List;

public class TestingSpaceYasith {

    public static void testingCascadiaBot1() {

        ArrayList<Player> playerList = new ArrayList<>(List.of(
//                new Player("Colm"),
                new CascadiaBot("CascadiaBot")
//                new Player("Dom"),
//                new Player("Yasith")
                ));

        PlayerManager playerManager = new PlayerManager(playerList);
        Player activePlayer = playerManager.getFirstPlayer();

//        while (true) {


        for (int i=0; i<5; i++) {

//            if (!activePlayer.playTurn()) {
//                break;
//            }
            System.out.printf("TURN %d\n", i);
            activePlayer.playTurn();
//            GameUiView.printLargeSpace();
            System.out.println(BoardView.displayTiles(activePlayer.getPlayerBoardObject()));
            GameUiView.printPageBorder();

//            break;


//            activePlayer = playerManager.cycleToNextPlayer();
        }
    }

    public static void testingEquals1() {

        WildlifeToken w1 = new WildlifeToken(WildlifeToken.WildlifeTokenType.EMPTY);
        WildlifeToken w2 = new WildlifeToken(WildlifeToken.WildlifeTokenType.EMPTY);

        System.out.println(w1.equals(w2));
    }

    public static void testingDuplication() {
        CascadiaBot cb = new CascadiaBot("CascadiaBot");

        PlayerBoard pb1 = cb.getPlayerBoardObject();
        PlayerBoard pb2 = pb1.getDuplicate();

        Tile t1 = pb1.getPlayerBoardAs2dArray().get(1).get(2);
        Tile t2 = pb2.getPlayerBoardAs2dArray().get(1).get(2);

        HabitatTile h1 = pb1.getPlayerBoardAs2dArray().get(1).get(2).getHabitatTile();
        HabitatTile h2 = pb2.getPlayerBoardAs2dArray().get(1).get(2).getHabitatTile();

//        System.out.println(pb1.equals(pb2));

//        System.out.println(BoardView.displayTiles(pb2));
//        System.out.println(h1.equals(h2));

//        for (int i=0; i<4; i++) {
//            for (int k=0; k<4; k++) {
//                System.out.printf("%s\nAND\n%s\nIS_EQUAL: %s\n\n",
//                        pb1.getPlayerBoard().get(i).get(k),
//                        pb2.getPlayerBoard().get(i).get(k),
//                        pb1.getPlayerBoard().get(i).get(k).equals(pb2.getPlayerBoard().get(i).get(k)));
//            }
//        }

        System.out.println(pb1.getPlayerBoardAs2dArray());
        System.out.println(pb2.getPlayerBoardAs2dArray());

    }

    public static void testingSetBotPage() {
//        ArrayList<Player> playerList = new ArrayList<>(List.of(
//                new Player("Colm"),
//                new CascadiaBot("CascadiaBot"),
//                new Player("Dom"),
//                new Player("Yasith")
//        ));

//        GameSetupView.setBots(playerList);

//        System.out.println(playerList);

        ArrayList<Player> playerList = GameSetupView.getPlayerInformationFromUser(4);

        for (Player player: playerList) {
            System.out.println(player.getClass().getSimpleName());
        }

    }

    public static void testingReserve() {

        CascadiaBot p1 = new CascadiaBot("Ton");

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(1, 0));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(2, 0));


        p1.playTurn();
        System.out.println(BoardView.displayTiles(p1.getPlayerBoardObject()));
        System.out.println(SelectionOptionsView.displaySelectedWildlifeTokens(StartGame.selectedTokens));

    }


    public static void main(String[] args) {
//        testingCascadiaBot1();
//        testingDuplication();

//        testingSetBotPage();
        testingReserve();
    }
}
