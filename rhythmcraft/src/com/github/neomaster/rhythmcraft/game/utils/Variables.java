package com.github.neomaster.rhythmcraft.game.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Variables {

    public static HashMap<Player, GameLocations> a = new HashMap<Player, GameLocations>();
    public static ArrayList<GameLocations> l = new ArrayList<>();

    // ====================================================================

    public static HashMap<Player, ArrayList<Player>> listOfPlayingGame = new HashMap<Player, ArrayList<Player>>(); // DJ, DJ 포함 플레이어
    public static ArrayList<HashMap<Player, Scores>> q = new ArrayList<HashMap<Player, Scores>>();

    public class Scores {

        public int MISS;
        public int BAD;
        public int GOOD;
        public int GREAT;
        public int PERFECT;
    }

    public static String SUMMONTOKEN = "token=summoned";
    public static HashMap<Player, Boolean> isEnded = new HashMap<>();
}
