package org.grouptwentyone.models;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerManager {

    private ArrayList<Player> playerList;
    private int playerIndexCycle = 0;



    public PlayerManager(ArrayList<Player> inputPlayerList) {
        setPlayerList(inputPlayerList);
    }


    public Player getFirstPlayer() {
        return this.playerList.get(0);
    }

    public Player cycleToNextPlayer() {

        // Makes index cycle instead of going out of bounds
        if (playerIndexCycle == playerList.size()-1) {
            playerIndexCycle = 0;
        } else {
            playerIndexCycle++;
        }


        return playerList.get(playerIndexCycle);
    }

    public void shufflePlayerList() {
        Collections.shuffle(this.playerList);
    }


    public ArrayList<Player> getPlayerList() {
        return this.playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public void tallyUpAllScores() {
        for (Player player: getPlayerList()) {
            player.getPlayerBoardObject().getScore();
        }
    }

}
