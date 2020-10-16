package com.github.neomaster.rhythmcraft.game.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class GameLocations {

    public Location player1_1key; public Location DJLocation;
    public Location player1_2key; public Location middle;
    public Location player1_3key; public Location player1;
    public Location player1_4key; public Location player2;
    public Location player1_5key; public Location player3;
    public Location player1_6key; public Location player4;
    public Location player1_7key; public Player DJ;
    public Location player1_8key;
    public Location player2_1key;
    public Location player2_2key;
    public Location player2_3key;
    public Location player2_4key;
    public Location player2_5key;
    public Location player2_6key;
    public Location player2_7key;
    public Location player2_8key;
    public Location player3_1key;
    public Location player3_2key;
    public Location player3_3key;
    public Location player3_4key;
    public Location player3_5key;
    public Location player3_6key;
    public Location player3_7key;
    public Location player3_8key;
    public Location player4_1key;
    public Location player4_2key;
    public Location player4_3key;
    public Location player4_4key;
    public Location player4_5key;
    public Location player4_6key;
    public Location player4_7key;
    public Location player4_8key;
    public Location player1_1keyPanjung;
    public Location player1_2keyPanjung;
    public Location player1_3keyPanjung;
    public Location player1_4keyPanjung;
    public Location player1_5keyPanjung;
    public Location player1_6keyPanjung;
    public Location player1_7keyPanjung;
    public Location player1_8keyPanjung;
    public Location player2_1keyPanjung;
    public Location player2_2keyPanjung;
    public Location player2_3keyPanjung;
    public Location player2_4keyPanjung;
    public Location player2_5keyPanjung;
    public Location player2_6keyPanjung;
    public Location player2_7keyPanjung;
    public Location player2_8keyPanjung;
    public Location player3_1keyPanjung;
    public Location player3_2keyPanjung;
    public Location player3_3keyPanjung;
    public Location player3_4keyPanjung;
    public Location player3_5keyPanjung;
    public Location player3_6keyPanjung;
    public Location player3_7keyPanjung;
    public Location player3_8keyPanjung;
    public Location player4_1keyPanjung;
    public Location player4_2keyPanjung;
    public Location player4_3keyPanjung;
    public Location player4_4keyPanjung;
    public Location player4_5keyPanjung;
    public Location player4_6keyPanjung;
    public Location player4_7keyPanjung;
    public Location player4_8keyPanjung;

    public static void intializeGameLocations(World world, GameLocations Locations, Player dj) {

        Locations.middle = new Location(world, 0.0, 100.0, 0.0); // just dummy data
        Locations.DJLocation = new Location(world, 16, 110, 16);
        Locations.player3 = new Location(world, 21, 105, 0);
        Locations.player4 = new Location(world, 0, 105, 21);
        Locations.player1 = new Location(world, -21, 105, 0);
        Locations.player2 = new Location(world, 0, 105, -21);
        Locations.DJ = dj;

        Locations.player1_1key = new Location(world, -6, 101, -4);
        Locations.player1_2key = new Location(world, -6, 101, -3);
        Locations.player1_3key = new Location(world, -6, 101, -2);
        Locations.player1_4key = new Location(world, -6, 101, -1);
        Locations.player1_5key = new Location(world, -6, 101, 1);
        Locations.player1_6key = new Location(world, -6, 101, 2);
        Locations.player1_7key = new Location(world, -6, 101, 3);
        Locations.player1_8key = new Location(world, -6, 101, 4);

        Locations.player1_1keyPanjung = new Location(world, -19, 101, -4);
        Locations.player1_2keyPanjung = new Location(world, -19, 101, -3);
        Locations.player1_3keyPanjung = new Location(world, -19, 101, -2);
        Locations.player1_4keyPanjung = new Location(world, -19, 101, -1);
        Locations.player1_5keyPanjung = new Location(world, -19, 101, 1);
        Locations.player1_6keyPanjung = new Location(world, -19, 101, 2);
        Locations.player1_7keyPanjung = new Location(world, -19, 101, 3);
        Locations.player1_8keyPanjung = new Location(world, -19, 101, 4);

        Locations.player2_1key = new Location(world, 4, 101, -6);
        Locations.player2_2key = new Location(world, 3, 101, -6);
        Locations.player2_3key = new Location(world, 2, 101, -6);
        Locations.player2_4key = new Location(world, 1, 101, -6);
        Locations.player2_5key = new Location(world, -1, 101, -6);
        Locations.player2_6key = new Location(world, -2, 101, -6);
        Locations.player2_7key = new Location(world, -3, 101, -6);
        Locations.player2_8key = new Location(world, -4, 101, -6);

        Locations.player2_1keyPanjung = new Location(world, 4, 101, -19);
        Locations.player2_2keyPanjung = new Location(world, 3, 101, -19);
        Locations.player2_3keyPanjung = new Location(world, 2, 101, -19);
        Locations.player2_4keyPanjung = new Location(world, 1, 101, -19);
        Locations.player2_5keyPanjung = new Location(world, -1, 101, -19);
        Locations.player2_6keyPanjung = new Location(world, -2, 101, -19);
        Locations.player2_7keyPanjung = new Location(world, -3, 101, -19);
        Locations.player2_8keyPanjung = new Location(world, -4, 101, -19);

        Locations.player3_1key = new Location(world, 6, 101, 4);
        Locations.player3_2key = new Location(world, 6, 101, 3);
        Locations.player3_3key = new Location(world, 6, 101, 2);
        Locations.player3_4key = new Location(world, 6, 101, 1);
        Locations.player3_5key = new Location(world, 6, 101, -1);
        Locations.player3_6key = new Location(world, 6, 101, -2);
        Locations.player3_7key = new Location(world, 6, 101, -3);
        Locations.player3_8key = new Location(world, 6, 101, -4);

        Locations.player3_1keyPanjung = new Location(world, 19, 101, 4);
        Locations.player3_2keyPanjung = new Location(world, 19, 101, 3);
        Locations.player3_3keyPanjung = new Location(world, 19, 101, 2);
        Locations.player3_4keyPanjung = new Location(world, 19, 101, 1);
        Locations.player3_5keyPanjung = new Location(world, 19, 101, -1);
        Locations.player3_6keyPanjung = new Location(world, 19, 101, -2);
        Locations.player3_7keyPanjung = new Location(world, 19, 101, -3);
        Locations.player3_8keyPanjung = new Location(world, 19, 101, -4);

        Locations.player4_1key = new Location(world, -4, 101, 6);
        Locations.player4_2key = new Location(world, -3, 101, 6);
        Locations.player4_3key = new Location(world, -2, 101, 6);
        Locations.player4_4key = new Location(world, -1, 101, 6);
        Locations.player4_5key = new Location(world, 1, 101, 6);
        Locations.player4_6key = new Location(world, 2, 101, 6);
        Locations.player4_7key = new Location(world, 3, 101, 6);
        Locations.player4_8key = new Location(world, 4, 101, 6);

        Locations.player4_1keyPanjung = new Location(world, -4, 101, 19);
        Locations.player4_2keyPanjung = new Location(world, -3, 101, 19);
        Locations.player4_3keyPanjung = new Location(world, -2, 101, 19);
        Locations.player4_4keyPanjung = new Location(world, -1, 101, 19);
        Locations.player4_5keyPanjung = new Location(world, 1, 101, 19);
        Locations.player4_6keyPanjung = new Location(world, 2, 101, 19);
        Locations.player4_7keyPanjung = new Location(world, 3, 101, 19);
        Locations.player4_8keyPanjung = new Location(world, 4, 101, 19);

    }
}
