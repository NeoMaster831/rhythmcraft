package com.github.neomaster.rhythmcraft.game.repeat;

import com.github.neomaster.rhythmcraft.game.utils.Variables;
import com.github.neomaster.rhythmcraft.main.GamePlayVariables;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class ArmorStandDirector {

    public static void TP(Player hoster, UUID uuid) {

        new BukkitRunnable() {

            @Override
            public void run() {

                ArmorStand tArmorStand = (ArmorStand)Bukkit.getEntity(uuid);
                Location loc = tArmorStand.getLocation();
                double x = loc.getX();
                double dummyY = loc.getY();
                double z = loc.getZ();

                if (x <= -5) { // PLAYER1, x를 -
                    double newX = x - GamePlayVariables.PlaySpeed.get(hoster);
                    Location newLoc = new Location(tArmorStand.getWorld(), newX, dummyY, z);
                    tArmorStand.teleport(newLoc);
                }
                if (z <= -5) { // PLAYER2, z를 -
                    double newZ = z - GamePlayVariables.PlaySpeed.get(hoster);
                    Location newLoc = new Location(tArmorStand.getWorld(), x, dummyY, newZ);
                    tArmorStand.teleport(newLoc);
                }
                if (x >= 5) {// PLAYER3,
                    double newX = x + GamePlayVariables.PlaySpeed.get(hoster);
                    Location newLoc = new Location(tArmorStand.getWorld(), newX, dummyY, z);
                    tArmorStand.teleport(newLoc);
                }
                if (z >= 5) {// PLAYER4라고
                    double newZ = z + GamePlayVariables.PlaySpeed.get(hoster);
                    Location newLoc = new Location(tArmorStand.getWorld(), x, dummyY, newZ);
                    tArmorStand.teleport(newLoc);
                }

                // 주의주의주의주의주의주의주의주의주의주의주의주의주의주의주의주의주의주의주의주의주의주의 여기가 아머스탠드 없애는곳

                if (x < -21 || x > 21 || z < -21 || z > 21) {
                    tArmorStand.remove();
                }

            }

        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("rhythmcraft"), 1L, 1L);

    }

    public enum SlotType {
        RED,
        ORANGE,
        YELLOW,
        LIME,
        AQUA,
        BLUE,
        PINK,
        GRAY
    }

    public enum DirectionType {
        PLAYER1,
        PLAYER2,
        PLAYER3,
        PLAYER4
    }

    @SuppressWarnings("deprecated")
    public static UUID Summon(Player hoster, SlotType slotType, DirectionType directionType) {

        World SummoningWorld = GamePlayVariables.PlayWorld.get(hoster);
        int x = 0;
        int z = 0;
        ItemStack key;

        if (directionType == DirectionType.PLAYER1) {
            x = -6;
            if (slotType == SlotType.RED) z = -4;
            else if (slotType == SlotType.ORANGE) z = -3;
            else if (slotType == SlotType.YELLOW) z = -2;
            else if (slotType == SlotType.LIME) z = -1;
            else if (slotType == SlotType.AQUA) z = 1;
            else if (slotType == SlotType.BLUE) z = 2;
            else if (slotType == SlotType.PINK) z = 3;
            else if (slotType == SlotType.GRAY) z = 4;
        }
        else if (directionType == DirectionType.PLAYER2) {
            z = -6;
            if (slotType == SlotType.RED) x = 4;
            else if (slotType == SlotType.ORANGE) x = 3;
            else if (slotType == SlotType.YELLOW) x = 2;
            else if (slotType == SlotType.LIME) x = 1;
            else if (slotType == SlotType.AQUA) x = -1;
            else if (slotType == SlotType.BLUE) x = -2;
            else if (slotType == SlotType.PINK) x = -3;
            else if (slotType == SlotType.GRAY) x = -4;
        }
        else if (directionType == DirectionType.PLAYER3) {
            x = 6;
            if (slotType == SlotType.RED) z = 4;
            else if (slotType == SlotType.ORANGE) z = 3;
            else if (slotType == SlotType.YELLOW) z = 2;
            else if (slotType == SlotType.LIME) z = 1;
            else if (slotType == SlotType.AQUA) z = -1;
            else if (slotType == SlotType.BLUE) z = -2;
            else if (slotType == SlotType.PINK) z = -3;
            else if (slotType == SlotType.GRAY) z = -4;

        }
        else if (directionType == DirectionType.PLAYER4) {
            z = 6;
            if (slotType == SlotType.RED) x = -4;
            else if (slotType == SlotType.ORANGE) x = -3;
            else if (slotType == SlotType.YELLOW) x = -2;
            else if (slotType == SlotType.LIME) x = -1;
            else if (slotType == SlotType.AQUA) x = 1;
            else if (slotType == SlotType.BLUE) x = 2;
            else if (slotType == SlotType.PINK) x = 3;
            else if (slotType == SlotType.GRAY) x = 4;
        }

        if (slotType == SlotType.RED) key = new ItemStack(Material.RED_CONCRETE, 1);
        else if (slotType == SlotType.ORANGE) key = new ItemStack(Material.ORANGE_CONCRETE, 1);
        else if (slotType == SlotType.YELLOW) key = new ItemStack(Material.YELLOW_CONCRETE, 1);
        else if (slotType == SlotType.LIME) key = new ItemStack(Material.LIME_CONCRETE, 1);
        else if (slotType == SlotType.AQUA) key = new ItemStack(Material.LIGHT_BLUE_CONCRETE, 1);
        else if (slotType == SlotType.BLUE) key = new ItemStack(Material.BLUE_CONCRETE, 1);
        else if (slotType == SlotType.PINK) key = new ItemStack(Material.MAGENTA_CONCRETE, 1);
        else if (slotType == SlotType.GRAY) key = new ItemStack(Material.LIGHT_GRAY_CONCRETE, 1);
        else key = new ItemStack(Material.WHITE_CONCRETE, 1);

        Location summonLoc = new Location(SummoningWorld, x, 101, z);
        ArmorStand lArmorStand = summonLoc.getWorld().spawn(summonLoc, ArmorStand.class);
        lArmorStand.setVisible(false);
        lArmorStand.setMarker(true);
        lArmorStand.setSmall(true);
        lArmorStand.setCustomName(Variables.SUMMONTOKEN);
        lArmorStand.setHelmet(key);

        return lArmorStand.getUniqueId();
    }
}
