package org.grouptwentyone.controllers;

import org.grouptwentyone.models.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ScoringController {

    public static int scoreFoxScoringCardA(PlayerBoard playerBoard) {

        int localScore = 0;

        for (ArrayList<Tile> row : playerBoard.getPlayerBoardAs2dArray()) {
            for (Tile tile : row) {
                boolean tileHasFoxToken = tile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.FOX;
                if (tileHasFoxToken) {
//                    System.out.println("FOUND FOX");
                    ArrayList<Tile> adjacentTileList = playerBoard.getAdjacentTileList(tile);

                    ArrayList<WildlifeToken.WildlifeTokenType> adjacentWildlifeTokenList = new ArrayList<>();

                    for (Tile adjacentTile : adjacentTileList) {
                        boolean containsNewAdjacentWildlifeToken = !adjacentWildlifeTokenList.contains(adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType());
                        boolean tileHasWildlifeToken = adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() != WildlifeToken.WildlifeTokenType.EMPTY;

//                        System.out.printf("Tile %s, containsNewAdjacentWildlifeToken: %s, tileHasWildlifeToken: %s\n", adjacentTile, containsNewAdjacentWildlifeToken, tileHasWildlifeToken);

                        if (containsNewAdjacentWildlifeToken && tileHasWildlifeToken) {
                            adjacentWildlifeTokenList.add(adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType());
                        }
                    }
                    int numberOfAdjacentUniqueAnimalTokens = adjacentWildlifeTokenList.size();
                    localScore += numberOfAdjacentUniqueAnimalTokens;
                }
            }
        }

        return localScore;
    }


    public static int scoreFoxScoringCardB(PlayerBoard playerBoard) {

        int localScore = 0;
        HashMap<Integer, Integer> scoringCard = new HashMap<>();
        scoringCard.put(1,3);
        scoringCard.put(2,5);
        scoringCard.put(3,7);

        ArrayList<WildlifeToken.WildlifeTokenType> adjacentWildlifeTokensWithOneOccurrence = new ArrayList<>();
        ArrayList<WildlifeToken.WildlifeTokenType> adjacentWildlifeTokensWithPairOccurrence = new ArrayList<>();

        for (ArrayList<Tile> row : playerBoard.getPlayerBoardAs2dArray()) {
            for (Tile tile : row) {

                boolean tileHasFoxToken = tile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.FOX;
                if (tileHasFoxToken) {

                    for (Tile adjacentTile : playerBoard.getAdjacentNonEmptyTileList(tile)) {
                        boolean tokenPairExists = adjacentWildlifeTokensWithPairOccurrence.contains(adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType());
                        boolean tokenHasAppearedOnce = adjacentWildlifeTokensWithOneOccurrence.contains(adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType());

                        // Condition 0: Adjacent tile is a fox token so we must ignore.
                        boolean adjacentTileHasFoxToken = adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.FOX;
                        if (adjacentTileHasFoxToken) {
                            continue;
                        }

                        // Condition 1 : Pair already exists so ignore as a pair is only scored once. (No code needed)

                        // Condition 2: One occurrence of the wildlife token already exists so the second occurrence makes a pair.
                        if (tokenHasAppearedOnce && !tokenPairExists) {
                            adjacentWildlifeTokensWithPairOccurrence.add(adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType());
                            adjacentWildlifeTokensWithOneOccurrence.remove(adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType());

                        }
                        // Condition 3: Wildlife token has not appeared before: so this is the first occurrence
                        else if (!tokenHasAppearedOnce) {
                            adjacentWildlifeTokensWithOneOccurrence.add(adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType());
                        }
//                        else {
//                            System.out.printf("tile: %s, tokenPairExists: %s, tokenHasAppearedOnce: %s\n", adjacentTile, tokenPairExists, tokenHasAppearedOnce);
//                        }

                    }
                    int numberOfUniquePairs = adjacentWildlifeTokensWithPairOccurrence.size();
                    localScore += scoringCard.get(numberOfUniquePairs);
                }
            }
        }

        return localScore;
    }


    public static int scoreFoxScoringCardC(PlayerBoard playerBoard) {

        int localScore = 0;

        for (ArrayList<Tile> row : playerBoard.getPlayerBoardAs2dArray()) {
            for (Tile tile : row) {

                boolean tileHasFoxToken = tile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.FOX;
                if (tileHasFoxToken) {

                    HashMap<WildlifeToken.WildlifeTokenType, Integer> wildlifeTokenScores = new HashMap<>();
                    wildlifeTokenScores.put(WildlifeToken.WildlifeTokenType.BEAR, 0);
                    wildlifeTokenScores.put(WildlifeToken.WildlifeTokenType.FOX, 0);
                    wildlifeTokenScores.put(WildlifeToken.WildlifeTokenType.ELK, 0);
                    wildlifeTokenScores.put(WildlifeToken.WildlifeTokenType.HAWK, 0);
                    wildlifeTokenScores.put(WildlifeToken.WildlifeTokenType.SALMON, 0);

                    for (Tile adjacentTile : playerBoard.getAdjacentNonEmptyTileList(tile)) {
                        WildlifeToken.WildlifeTokenType adjacentTileWildlifeTokenType = adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType();
                        int tokenScore = wildlifeTokenScores.get(adjacentTileWildlifeTokenType);
                        wildlifeTokenScores.put(adjacentTileWildlifeTokenType, tokenScore + 1);
                    }

                    int maxTokenScore = 0;
                    WildlifeToken.WildlifeTokenType maxTokenType = WildlifeToken.WildlifeTokenType.EMPTY;

                    for (HashMap.Entry<WildlifeToken.WildlifeTokenType, Integer> entry : wildlifeTokenScores.entrySet()) {
                        WildlifeToken.WildlifeTokenType tokenType = entry.getKey();
                        int tokenScore = entry.getValue();

                        if (tokenScore > maxTokenScore) {
                            maxTokenScore = tokenScore;
                            maxTokenType = tokenType;
                        }
                    }

                    localScore += maxTokenScore;
//                    System.out.println(maxTokenType);

                }


            }
        }

        return localScore;
    }

    public static int scoreBearScoringCardA(PlayerBoard playerBoard) {

        double numberOfPairs = 0;
        ArrayList<Tile> tilesExcludedFromScoring = new ArrayList<>();

        for (ArrayList<Tile> row : playerBoard.getPlayerBoardAs2dArray()) {
            for (Tile tile : row) {
                boolean tileHasBearToken = tile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.BEAR;

                if (tileHasBearToken) {
                    ArrayList<Tile> tileGroupList = ScoringController.getTileGroupFromTile(playerBoard, tile.getHexCoordinate());

                    // Ignore tiles that are excluded from scoring.
                    ArrayList<Tile> tilesToRemove = new ArrayList<>();
                    for (Tile groupTile: tileGroupList) {
                        if (tilesExcludedFromScoring.contains(groupTile)) tilesToRemove.add(groupTile);
                    }
                    tileGroupList.removeAll(tilesToRemove);

                    // If the conditional passes: there must be at least two tiles not yet recorded or adjacent to recorded pairs.
                    boolean isGroupSizeTwo = tileGroupList.size() >= 2;
                    if (isGroupSizeTwo) {

                        numberOfPairs++; // Increment number of pairs

                        ArrayList<Tile> tilePairList = new ArrayList<>(tileGroupList.subList(0, 2));
                        tilesExcludedFromScoring.addAll(tilePairList); // We exclude the pair from future scoring.
                        // And then remove them pair from the group list
                        tileGroupList.remove(0);
                        tileGroupList.remove(0);

                        // Now check all of the remaining items in the group to see if they're adjacent to the pair.
                        // If they are: they should be excluded from scoring
                        for (Tile groupTile: tileGroupList) {
                            for (Tile pairTile: tilePairList) {
                                if (groupTile.isAdjacentToTile(pairTile)) {
                                    tilesExcludedFromScoring.add(groupTile);
                                }
                            }
                        }

                    }

                }
            }
        }


        // More than 4 pairs are treated the same as 4 pairs.
        if (numberOfPairs > 4) numberOfPairs = 4;

        HashMap<Integer, Integer> scoringTable = new HashMap<>();
        scoringTable.put(1, 4);
        scoringTable.put(2, 11);
        scoringTable.put(3, 19);
        scoringTable.put(4, 27);

        int localScore = scoringTable.get((int) numberOfPairs);
        return localScore;
    }



    public static int scoreBearScoringCardA_V1(PlayerBoard playerBoard) {
        double numberOfPairs = 0;

        /*
         *
         * 1. First player board loop: identify bear tiles that have 2 or more adjacent tiles.
         * 2. Second player board loop: identify bear tiles that have 1 adjacent bear tile and not adjacent to any of the tiles found in loop one.
         *
         * */

        ArrayList<Tile> bearTilesWithDegreeGreaterThanOneList = new ArrayList<>();

        for (ArrayList<Tile> row : playerBoard.getPlayerBoardAs2dArray()) {
            for (Tile tile : row) {
                boolean tileHasBearToken = tile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.BEAR;

                if (tileHasBearToken) {

                    ArrayList<Tile> adjacentTileList = playerBoard.getAdjacentNonEmptyTileList(tile);

                    int numberOfAdjacentBearTokens = (int) adjacentTileList.stream().filter(adjacentTile -> {
                        return adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.BEAR;
                    }).count();

                    if (numberOfAdjacentBearTokens > 1) {
                        bearTilesWithDegreeGreaterThanOneList.add(tile);
                    }

                }
            }
        }

        for (ArrayList<Tile> row : playerBoard.getPlayerBoardAs2dArray()) {
            for (Tile tile : row) {
                boolean tileHasBearToken = tile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.BEAR;

                if (tileHasBearToken) {
                    ArrayList<Tile> adjacentTileList = playerBoard.getAdjacentNonEmptyTileList(tile);
                    int numberOfAdjacentBearTokens = (int) adjacentTileList.stream().filter(adjacentTile -> {
                        return adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.BEAR;
                    }).count();

                    boolean tileIsAdjacentToMultidegreeTile = !Collections.disjoint(adjacentTileList, bearTilesWithDegreeGreaterThanOneList);

                    if (numberOfAdjacentBearTokens == 1 && !tileIsAdjacentToMultidegreeTile) {
                        numberOfPairs += 0.5; // Each of the two in the pair count for 0.5 so together they make 1 pair
                    }
                }
            }
        }

        // More than 4 pairs are treated the same as 4 pairs.
        if (numberOfPairs > 4) numberOfPairs = 4;

        HashMap<Integer, Integer> scoringTable = new HashMap<>();
        scoringTable.put(1, 4);
        scoringTable.put(2, 11);
        scoringTable.put(3, 19);
        scoringTable.put(4, 27);

        int localScore = scoringTable.get((int) numberOfPairs);
        return localScore;


    }

    public static void traverseTileGroup(PlayerBoard playerBoard, ArrayList<Tile> listOfTilesInGroup, HexCoordinate hexCoordinate) {

        Tile inputTile = playerBoard.getPlayerBoardAs2dArray().get(hexCoordinate.getX()).get(hexCoordinate.getY());
        WildlifeToken.WildlifeTokenType requiredWildlifeTokenType = inputTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType();

        ArrayList<Tile> adjacentTileListWithSameWildlifeTokenList = playerBoard.getAdjacentNonEmptyTileList(inputTile)
                .stream().filter(adjacentTile -> adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == requiredWildlifeTokenType)
                .collect(Collectors.toCollection(ArrayList::new));

        // Make list of new unrecorded adjacent tiles with same animal token
        ArrayList<Tile> newAdjacentTileList = new ArrayList<>();
        for (Tile adjacentTile : adjacentTileListWithSameWildlifeTokenList) {
            boolean tileNotInTileGroup = !listOfTilesInGroup.contains(adjacentTile);
            if (tileNotInTileGroup) {
                newAdjacentTileList.add(adjacentTile);
            }
        }

        // Base Case: If there are no more new unrecorded adjacent tiles
        if (newAdjacentTileList.size() == 0) return;

        listOfTilesInGroup.addAll(newAdjacentTileList);

        for (Tile adjacentTile : newAdjacentTileList) {
            traverseTileGroup(playerBoard, listOfTilesInGroup, adjacentTile.getHexCoordinate());
        }


//        for (Tile adjacentTile: adjacentTileListWithSameWildlifeTokenList) {
//            traverseTileGroup(playerBoard, )
//        }

    }

    public static ArrayList<Tile> getTileGroupFromTile(PlayerBoard playerBoard, HexCoordinate hexCoordinate) {

        Tile inputTile = playerBoard.getPlayerBoardAs2dArray().get(hexCoordinate.getX()).get(hexCoordinate.getY());
        ArrayList<Tile> outputList = new ArrayList<>();
        WildlifeToken.WildlifeTokenType requiredWildlifeTokenType = inputTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType();

        ArrayList<Tile> adjacentTileListWithSameWildlifeTokenList = playerBoard.getAdjacentNonEmptyTileList(inputTile)
                .stream().filter(adjacentTile -> adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == requiredWildlifeTokenType)
                .collect(Collectors.toCollection(ArrayList::new));

        outputList.add(inputTile);
        outputList.addAll(adjacentTileListWithSameWildlifeTokenList);

        for (Tile adjacentTile : adjacentTileListWithSameWildlifeTokenList) {
            traverseTileGroup(playerBoard, outputList, adjacentTile.getHexCoordinate());
        }

        return outputList;
    }

    public static void traverseTileGroupNDistance_V1(PlayerBoard playerBoard, ArrayList<Tile> listOfTilesInGroup, HexCoordinate hexCoordinate, int distanceLeftToTravel) {

        System.out.println(listOfTilesInGroup);
        if (listOfTilesInGroup.size() == 3) {
            System.out.println("Found three");
        }

        Tile inputTile = playerBoard.getPlayerBoardAs2dArray().get(hexCoordinate.getX()).get(hexCoordinate.getY());
        WildlifeToken.WildlifeTokenType requiredWildlifeTokenType = inputTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType();

        System.out.printf("Looking at %s\n", inputTile);



        ArrayList<Tile> adjacentTileListWithSameWildlifeTokenList = playerBoard.getAdjacentNonEmptyTileList(inputTile)
                .stream().filter(adjacentTile -> adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == requiredWildlifeTokenType)
                .collect(Collectors.toCollection(ArrayList::new));

        // Make list of new unrecorded adjacent tiles with same animal token
        ArrayList<Tile> newAdjacentTileList = new ArrayList<>();
        for (Tile adjacentTile : adjacentTileListWithSameWildlifeTokenList) {
//            System.out.printf("\tLooking at %s\n", adjacentTile);
            boolean tileNotInTileGroup = !listOfTilesInGroup.contains(adjacentTile);
            if (tileNotInTileGroup) {
                newAdjacentTileList.add(adjacentTile);
            }
        }



        // Base Case: If there are no more new unrecorded adjacent tiles
        if (newAdjacentTileList.size() == 0) return;
        if (listOfTilesInGroup.size() >= 3 && distanceLeftToTravel < 0) {
            return;
        }

        listOfTilesInGroup.addAll(newAdjacentTileList);

        for (Tile adjacentTile : newAdjacentTileList) {
            traverseTileGroupNDistance_V1(playerBoard, listOfTilesInGroup, adjacentTile.getHexCoordinate(), distanceLeftToTravel-1);
        }
    }




    public static ArrayList<Tile> getTileGroupOfSizeNFromTile_V1(PlayerBoard playerBoard, HexCoordinate hexCoordinate, int distanceToTravel) {
        Tile inputTile = playerBoard.getPlayerBoardAs2dArray().get(hexCoordinate.getX()).get(hexCoordinate.getY());
        ArrayList<Tile> outputList = new ArrayList<>();
        WildlifeToken.WildlifeTokenType requiredWildlifeTokenType = inputTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType();

        ArrayList<Tile> adjacentTileListWithSameWildlifeTokenList = playerBoard.getAdjacentNonEmptyTileList(inputTile)
                .stream().filter(adjacentTile -> adjacentTile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == requiredWildlifeTokenType)
                .collect(Collectors.toCollection(ArrayList::new));

        outputList.add(inputTile);
        outputList.addAll(adjacentTileListWithSameWildlifeTokenList);

        for (Tile adjacentTile: adjacentTileListWithSameWildlifeTokenList) {
            System.out.println("started traversal");
            traverseTileGroupNDistance_V1(playerBoard, outputList, adjacentTile.getHexCoordinate(), distanceToTravel);
        }

        return outputList;
    }

    public static boolean areGroupsEqual(ArrayList<Tile> group1, ArrayList<Tile> group2) {
        return group1.containsAll(group2) && group2.containsAll(group1);
    }

    public static int scoreBearScoringCardB(PlayerBoard playerBoard) {
        int numberOfTriples = 0;

        ArrayList<Tile> tilesRemovedFromScoring = new ArrayList<>();


        for (ArrayList<Tile> row: playerBoard.getPlayerBoardAs2dArray()) {
            for (Tile tile : row) {
                boolean tileHasBearToken = tile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.BEAR;
                if (tileHasBearToken) {

                    // Add local tile group to records if not already added
                    ArrayList<Tile> localTileGroup = getTileGroupFromTile(playerBoard, tile.getHexCoordinate());

                    // Ignore cases where there is not enough in the group for a triple
                    if (localTileGroup.size() < 3) {
                        continue;
                    }

                    // List containing three-group collection of bears
                    ArrayList<Tile> localTileGroupTriple = new ArrayList<>(localTileGroup.subList(0, 3));

                    // List without the aforementioned three-group collection of bears
                    ArrayList<Tile> localTileGroupWithoutTriple = new ArrayList<>();
                    try {
                        localTileGroupWithoutTriple.addAll(localTileGroup.subList(4, localTileGroup.size()));
                    } catch (IllegalArgumentException ex) {
                        // Nothing, just ignore if it doesn't work
                    }

                    // List of the triple and the adjacent tiles (we remove any tiles not adjacent to the triple).
                    ArrayList<Tile> localTileGroupWithAdjacentTiles = new ArrayList<>(localTileGroupTriple);
                    for (Tile localTile: localTileGroupWithoutTriple) {
                        for (Tile localTripleTile: localTileGroupTriple) {
                            if (localTile.isAdjacentToTile(localTripleTile)) {
                                localTileGroupWithAdjacentTiles.add(localTile);
                            }
                        }
                    }

//                    tilesRemovedFromScoring.addAll(localTileGroupWithAdjacentTiles);


//                    System.out.printf("Looking at tile group: %s\n", localTileGroup);
//                    System.out.printf("Tiles removed from scoring: %s\n\n", tilesRemovedFromScoring);


                    boolean isLocalTileGroupRecorded = false;
                    for (Tile tileInGroup: localTileGroupWithAdjacentTiles) {
                        boolean tileNotYetRecorded = !tilesRemovedFromScoring.contains(tileInGroup);
                        if (tileNotYetRecorded) {
                            tilesRemovedFromScoring.add(tileInGroup);
                        } else {
                            isLocalTileGroupRecorded = true;
                        }
                    }
//
                    if (!isLocalTileGroupRecorded) {
//                        System.out.println("INCREMENENTED SCORE");
                        numberOfTriples++;
                    }

                }
            }
        }

        int localScore = numberOfTriples * 10;
        return localScore;
    }

    public static int scoreBearScoringCardB_V1(PlayerBoard playerBoard) {
        int numberOfTriples = 0;

        ArrayList<ArrayList<Tile>> tileGroupList = new ArrayList<>();
//        ArrayList<Tile> tilesRemovedFromScoring = new ArrayList<>();


        for (ArrayList<Tile> row: playerBoard.getPlayerBoardAs2dArray()) {
            for (Tile tile : row) {
                boolean tileHasBearToken = tile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.BEAR;
                if (tileHasBearToken) {

                    // Add local tile group to records if not already added
                    ArrayList<Tile> localTileGroup = getTileGroupOfSizeNFromTile_V1(playerBoard, tile.getHexCoordinate(), 4);
                    boolean isLocalTileGroupUnrecorded = true;
                    for (ArrayList<Tile> tileGroup: tileGroupList) {
                        if (areGroupsEqual(tileGroup, localTileGroup)) {
                            isLocalTileGroupUnrecorded = false;
                            break;
                        }
                    }

                    if (isLocalTileGroupUnrecorded) {
                        if (localTileGroup.size() >= 3) {
                            numberOfTriples++;
                        }
                        tileGroupList.add(localTileGroup);
                    }

                }
            }
        }

        int localScore = numberOfTriples * 10;
        return localScore;
    }

    public static int scoreBearScoringCardC(PlayerBoard playerBoard) {
        HashMap<Integer, Integer> groupSizeFrequency = new HashMap<>();
        groupSizeFrequency.put(3, 0);
        groupSizeFrequency.put(2, 0);
        groupSizeFrequency.put(1, 0);

        ArrayList<Tile> excludedTilesFromScoring = new ArrayList<>();

        // Score singe group of size 3
        for (ArrayList<Tile> row : playerBoard.getPlayerBoardAs2dArray()) {
            boolean foundTriple = false;

            for (Tile tile : row) {
                boolean tileHasBearToken = tile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.BEAR;
                if (tileHasBearToken) {

                    ArrayList<Tile> tileGroupList = getTileGroupFromTile(playerBoard, tile.getHexCoordinate());

                    // Remove excluded tiles from scoring from the group list.
                    tileGroupList = tileGroupList.stream()
                            .filter(groupTile -> !excludedTilesFromScoring.contains(groupTile))
                            .collect(Collectors.toCollection(ArrayList::new));


                    // Ensure the group size is exactly 3
                    if (tileGroupList.size() != 3) {
                        continue;
                    }

                    groupSizeFrequency.put(3, groupSizeFrequency.get(3)+1);
                    foundTriple = true;

                    ArrayList<Tile> tripleGroupList = new ArrayList<>(tileGroupList.subList(0, 3));
                    excludedTilesFromScoring.addAll(tripleGroupList);

                    // Remove the triple from the group list
                    tileGroupList.remove(0);
                    tileGroupList.remove(0);
                    tileGroupList.remove(0);

                    // Identify and place adjacent tiles to the triple into the excluded from scoring list
                    for (Tile groupTile: tileGroupList) {
                        for (Tile tripleTile: tripleGroupList) {
                            boolean isTileAdjacentToTriple = groupTile.isAdjacentToTile(tripleTile);
                            if (isTileAdjacentToTriple) {
                                excludedTilesFromScoring.add(groupTile);
                            }
                        }
                    }

                    break;
                }
            }

            if (foundTriple) break;
        }

        // Score a single group of size 2
        for (ArrayList<Tile> row : playerBoard.getPlayerBoardAs2dArray()) {
            boolean foundDouble = false;

            for (Tile tile : row) {
                boolean tileHasBearToken = tile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.BEAR;
                if (tileHasBearToken) {
                    ArrayList<Tile> tileGroupList = getTileGroupFromTile(playerBoard, tile.getHexCoordinate());

                    // Remove excluded tiles from scoring from the group list.
                    tileGroupList = tileGroupList.stream()
                            .filter(groupTile -> !excludedTilesFromScoring.contains(groupTile))
                            .collect(Collectors.toCollection(ArrayList::new));


                    // Ensure the group size is exactly 2
                    if (tileGroupList.size() != 2) {
                        continue;
                    }

                    groupSizeFrequency.put(2, groupSizeFrequency.get(2)+1);
                    foundDouble = true;

                    ArrayList<Tile> tripleGroupList = new ArrayList<>(tileGroupList.subList(0, 2));
                    excludedTilesFromScoring.addAll(tripleGroupList);

                    // Remove the double from the group list
                    tileGroupList.remove(0);
                    tileGroupList.remove(0);

                    // Identify and place adjacent tiles to the triple into the excluded from scoring list
                    for (Tile groupTile: tileGroupList) {
                        for (Tile tripleTile: tripleGroupList) {
                            boolean isTileAdjacentToTriple = groupTile.isAdjacentToTile(tripleTile);
                            if (isTileAdjacentToTriple) {
                                excludedTilesFromScoring.add(groupTile);
                            }
                        }
                    }

                    break;

                }
            }
            if (foundDouble) break;
        }

        // Score a single group of size 1
        for (ArrayList<Tile> row : playerBoard.getPlayerBoardAs2dArray()) {
            boolean foundSingle = false;

            for (Tile tile : row) {
                boolean tileHasBearToken = tile.getHabitatTile().getWildlifeToken().getWildlifeTokenType() == WildlifeToken.WildlifeTokenType.BEAR;
                if (tileHasBearToken) {

                    ArrayList<Tile> tileGroupList = getTileGroupFromTile(playerBoard, tile.getHexCoordinate());
                    // Remove excluded tiles from scoring from the group list.
                    tileGroupList = tileGroupList.stream()
                            .filter(groupTile -> !excludedTilesFromScoring.contains(groupTile))
                            .collect(Collectors.toCollection(ArrayList::new));

                    // Ensure the group size is exactly 1
                    if (tileGroupList.size() != 1) {
                        continue;
                    }

                    groupSizeFrequency.put(1, groupSizeFrequency.get(1)+1);
                    foundSingle = true;

                    ArrayList<Tile> tripleGroupList = new ArrayList<>(tileGroupList.subList(0, 1));
                    excludedTilesFromScoring.addAll(tripleGroupList);

                    // Remove the single from the group list
                    tileGroupList.remove(0);

                    // Identify and place adjacent tiles to the triple into the excluded from scoring list
                    for (Tile groupTile: tileGroupList) {
                        for (Tile tripleTile: tripleGroupList) {
                            boolean isTileAdjacentToTriple = groupTile.isAdjacentToTile(tripleTile);
                            if (isTileAdjacentToTriple) {
                                excludedTilesFromScoring.add(groupTile);
                            }
                        }
                    }

                    break;
                }
            }
            if (foundSingle) break;
        }

        int localScore = 0;

        if (groupSizeFrequency.get(3) > 0) {
            localScore += 8;
        }
        if (groupSizeFrequency.get(2) > 0) {
            localScore += 5;
        }
        if (groupSizeFrequency.get(1) > 0) {
            localScore += 2;
        }

        if (groupSizeFrequency.get(3) > 0 && groupSizeFrequency.get(2) > 0 && groupSizeFrequency.get(1) > 0) {
            localScore += 3;
        }



//        System.out.printf(groupSizeFrequency.toString());

        return localScore;
    }







}



