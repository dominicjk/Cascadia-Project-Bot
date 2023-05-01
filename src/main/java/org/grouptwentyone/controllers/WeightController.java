package org.grouptwentyone.controllers;

public class WeightController {

    public static class WeightConstants {
        // If placing a bear token will not result in a new pair, we subtract this value from the weight of the number
        // of pairs it would have created had it resulted in a new pair.
        public static double nonPairBearPlacementReduction = 0.75;
        public static double ruinsBearPairWeight = -2.0;
        public static double allBearsInPairsReduction = 0.5;

        // Multipliers for each animal weight table
        public static double elkMultiplier = 1;
        public static double bearMultiplier = 1.8;
        public static double hawkMultiplier = 1;
        public static double salmonMultiplier = 0.3;
        public static double foxMultiplier = 1.5;

    }

}
