package org.grouptwentyone;

import org.grouptwentyone.controllers.HabitatTilesController;
import org.grouptwentyone.models.*;
import org.grouptwentyone.views.BoardView;

import java.util.ArrayList;

public class TestingSpaceYasith {
    //moved code to separate method so main can be used to call specific tests
    private static void playerBoardBufferTest() {
        Player p1 = new Player("bob");
        HabitatTile habitatTile = new HabitatTile(2);
//        p1.setSelectedTile(habitatTile);
//        p1.addNewTile(new HexCoordinate(0,0));
//        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(0,0));

//        p1.addPlayerBoardBuffer(Player.PlayerBoardSide.TOP);
//        p1.addPlayerBoardBuffer(Player.PlayerBoardSide.TOP);
//        p1.addPlayerBoardBuffer(Player.PlayerBoardSide.LEFT);
//        p1.addPlayerBoardBuffer(Player.PlayerBoardSide.BOTTOM);
//        p1.addPlayerBoardBuffer(Player.PlayerBoardSide.RIGHT);


//        System.out.println(p1.getPlayerBoard().get(0));

//        System.out.println(p1.isCoordinateOnBoardEdge(new HexCoordinate(0, 4)));
//        System.out.println(p1.getPartOfBoardCoordinateIsOn(new HexCoordinate(4,5)));
//        System.out.println(p1.getPlayerBoard().get(3).get(4));
//        System.out.println("\n");
//        p1.printPlayerBoard();
    }

    private static ArrayList<ArrayList<Tile>> playerBoard = new ArrayList<>();

    //sample code for testing board display (moved from BoardView)
    public static void testBoardDisplay() {
        //these for loops initialise the board to null habitat tiles
        int sizeX = 3, sizeY = 3;
        for (int i = 0; i < sizeX; i++) {
            playerBoard.add(new ArrayList<>());
        }
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                playerBoard.get(i).add(j, new Tile(new HexCoordinate(i, j)));
            }
        }

        //create dummy tiles
        Tile oneOne = new Tile(HabitatTilesController.habitatTilesBag.get(0), new HexCoordinate(1, 1));
        Tile oneTwo = new Tile(HabitatTilesController.habitatTilesBag.get(1), new HexCoordinate(1, 2));
        Tile twoOne = new Tile(HabitatTilesController.habitatTilesBag.get(2), new HexCoordinate(2, 1));

        playerBoard.get(1).set(1, oneOne);
        //note oneTwo is added at index [2, 1]
        playerBoard.get(2).set(1, oneTwo);
        playerBoard.get(1).set(2, twoOne);
    }







    public static void testFoxCardA() {
//        System.out.println("ran");
//        PlayerBoard pb = new PlayerBoard();
        Player p1 = new Player("Ton");


        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(1,0));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(1,0));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(1,0));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(1,0));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(2,1));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.HAWK));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(2,1));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(2,2));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.FOX));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(2,2));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(2,3));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.BEAR));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(2,3));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(3,2));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.ELK));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(3,2));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(3,3));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.BEAR));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(3,3));



        System.out.println(p1.getPlayerBoardObject().getPlayerBoardAs2dArray());
        System.out.println(BoardView.displayTiles(p1.getPlayerBoardObject().getPlayerBoardAs2dArray()));
        System.out.printf("You have a score of: %d", p1.getScore());
    }


    public static void testFoxCardB() {
//        System.out.println("ran");
//        PlayerBoard pb = new PlayerBoard();
        Player p1 = new Player("Ton");


        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(1,0));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(1,0));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.FOX));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(1,1));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(1,0));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.BEAR));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(1,1));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(1,0));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.BEAR));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(1,1));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(2,1));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.HAWK));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(2,1));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(2,2));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.FOX));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(2,2));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(2,3));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.BEAR));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(2,3));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(3,2));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.HAWK));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(3,2));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(3,3));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.BEAR));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(3,3));



        System.out.println(p1.getPlayerBoardObject().getPlayerBoardAs2dArray());
        System.out.println(BoardView.displayTiles(p1.getPlayerBoardObject().getPlayerBoardAs2dArray()));
        System.out.printf("You have a score of: %d", p1.getScore());
    }


    public static void testFoxCardC() {
//        System.out.println("ran");
//        PlayerBoard pb = new PlayerBoard();
        Player p1 = new Player("Ton");


        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(1,0));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(1,0));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.ELK));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(1,1));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(1,0));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.BEAR));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(1,1));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(1,0));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.BEAR));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(1,1));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(2,1));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.HAWK));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(2,1));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(2,2));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.FOX));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(2,2));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(2,3));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.BEAR));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(2,3));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(3,2));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.HAWK));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(3,2));

        p1.getPlayerBoardObject().setSelectedTile(new HabitatTile(true));
        p1.getPlayerBoardObject().addNewTile(new HexCoordinate(3,3));
        p1.getPlayerBoardObject().setSelectedToken(new WildlifeToken(WildlifeToken.WildlifeTokenType.BEAR));
        p1.getPlayerBoardObject().addNewToken(new HexCoordinate(3,3));



        System.out.println(p1.getPlayerBoardObject().getPlayerBoardAs2dArray());
        System.out.println(BoardView.displayTiles(p1.getPlayerBoardObject().getPlayerBoardAs2dArray()));
        System.out.printf("You have a score of: %d", p1.getScore());
    }


    public static void main(String[] args) {
        testFoxCardA();
    }
}
