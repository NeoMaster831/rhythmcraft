package com.github.neomaster.rhythmcraft.game.repeat;

import com.github.neomaster.rhythmcraft.game.utils.GameFunc;
import com.github.neomaster.rhythmcraft.game.utils.Variables;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GameRepeat {

    public static void tpToLocation(Player p, Location loc, float Pitch, float Yaw) { // Yaw = y Pitch = x

        new BukkitRunnable() {
            @Override
            public void run() {
                if (Variables.isEnded.get(p) == true) {

                    this.cancel();
                }

                loc.setPitch(Pitch); loc.setYaw(Yaw);
                p.teleport(loc);
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("rhythmcraft"),1L, 1L);
    }

    public static void slotHotbarSet(Player p) {

        new BukkitRunnable() {
            @Override
            public void run() {

                if (Variables.isEnded.get(p) == true) {
                    p.getInventory().clear();
                    this.cancel();
                }

                Variables.Scores returnScore = null;

                HashMap<Player, Variables.Scores> dHash = new HashMap<>();
                for (int i = 0; i < Variables.q.size(); i++) { // HashMap 탐색
                    for (Player pe : Variables.q.get(i).keySet()) { //
                        if (pe.equals(p)) {
                            returnScore = Variables.q.get(i).get(pe);
                            Variables.q.get(i).remove(pe);
                            break;
                        }
                    }
                }

                if (p.getInventory().getHeldItemSlot() != 4) {

                    ArmorStand tArmorStand = null;
                    List<Entity> a = p.getWorld().getEntities();
                    for (Entity e : a) {
                        if (e.getCustomName() == Variables.SUMMONTOKEN) {
                            tArmorStand = (ArmorStand)e;
                            break;
                        }
                    }
                    if (tArmorStand == null) {
                        returnScore.MISS++; // 만약 키가 없을 떄
                        dHash.put(p, returnScore);
                        GameFunc.putToQ(dHash, p);
                    }

                    Location tLoc = tArmorStand.getLocation();
                    double tX = tLoc.getX();
                    double tZ = tLoc.getZ();
                    double tY = 101;

                    if (tZ > 17.5 && tZ < 20.5) { // BAD / p1
                        if (tZ > 18.15 && tZ < 19.85) { // GOOD
                            if (tZ > 18.6 && tZ < 19.4) {
                                if (tZ > 18.8 && tZ < 19.2) {
                                    returnScore.PERFECT++;
                                    dHash.put(p, returnScore);
                                    GameFunc.putToQ(dHash, p);

                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("PERFECT!", ChatColor.YELLOW));
                                }
                                else {
                                    returnScore.GREAT++;
                                    dHash.put(p, returnScore);
                                    GameFunc.putToQ(dHash, p);
                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("GREAT!", ChatColor.GREEN));
                                }
                            }
                            else {
                                returnScore.GOOD++;
                                dHash.put(p, returnScore);
                                GameFunc.putToQ(dHash, p);
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("GOOD", ChatColor.DARK_GREEN));
                            }
                        }
                        else {
                            returnScore.BAD++;
                            dHash.put(p, returnScore);
                            GameFunc.putToQ(dHash, p);
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("BAD", ChatColor.GRAY));
                        }
                    }
                    else {
                        returnScore.MISS++;
                        dHash.put(p, returnScore);
                        GameFunc.putToQ(dHash, p);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("MISS", ChatColor.DARK_GRAY));
                    }
                }

                Inventory playerInv = p.getInventory();
                playerInv.setItem(0, new ItemStack(Material.RED_CONCRETE, 1));
                playerInv.setItem(1, new ItemStack(Material.ORANGE_CONCRETE, 1));
                playerInv.setItem(2, new ItemStack(Material.YELLOW_CONCRETE, 1));
                playerInv.setItem(3, new ItemStack(Material.LIME_CONCRETE, 1));
                playerInv.setItem(4, new ItemStack(Material.WHITE_CONCRETE, 1));
                playerInv.setItem(5, new ItemStack(Material.LIGHT_BLUE_CONCRETE, 1));
                playerInv.setItem(6, new ItemStack(Material.BLUE_CONCRETE, 1));
                playerInv.setItem(7, new ItemStack(Material.MAGENTA_CONCRETE, 1));
                playerInv.setItem(8, new ItemStack(Material.LIGHT_GRAY_CONCRETE, 1));

                p.getInventory().setHeldItemSlot(4);
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("rhythmcraft"), 1L, 1L);
    }

    public static void slotHotbatSetDJ(Player dj) {

        new BukkitRunnable() {
            @Override
            public void run() {

                if (dj.getInventory().getHeldItemSlot() == 0) {
                    ArmorStandDirector.SlotType slotType = ArmorStandDirector.SlotType.RED;
                    ArmorStandDirector.DirectionType direc1 = ArmorStandDirector.DirectionType.PLAYER1; ArmorStandDirector.DirectionType direc2 = ArmorStandDirector.DirectionType.PLAYER2;ArmorStandDirector.DirectionType direc3 = ArmorStandDirector.DirectionType.PLAYER3; ArmorStandDirector.DirectionType direc4 = ArmorStandDirector.DirectionType.PLAYER4;
                    UUID d1 = ArmorStandDirector.Summon(dj, slotType, direc1); UUID d2 = ArmorStandDirector.Summon(dj, slotType, direc2); UUID d3 = ArmorStandDirector.Summon(dj, slotType, direc3); UUID d4 = ArmorStandDirector.Summon(dj, slotType, direc4);
                    ArmorStandDirector.TP(dj, d1); ArmorStandDirector.TP(dj, d2); ArmorStandDirector.TP(dj, d3);ArmorStandDirector.TP(dj, d4);
                }

                if (dj.getInventory().getHeldItemSlot() == 1) {
                    ArmorStandDirector.SlotType slotType = ArmorStandDirector.SlotType.ORANGE;
                    ArmorStandDirector.DirectionType direc1 = ArmorStandDirector.DirectionType.PLAYER1; ArmorStandDirector.DirectionType direc2 = ArmorStandDirector.DirectionType.PLAYER2;ArmorStandDirector.DirectionType direc3 = ArmorStandDirector.DirectionType.PLAYER3; ArmorStandDirector.DirectionType direc4 = ArmorStandDirector.DirectionType.PLAYER4;
                    UUID d1 = ArmorStandDirector.Summon(dj, slotType, direc1); UUID d2 = ArmorStandDirector.Summon(dj, slotType, direc2); UUID d3 = ArmorStandDirector.Summon(dj, slotType, direc3); UUID d4 = ArmorStandDirector.Summon(dj, slotType, direc4);
                    ArmorStandDirector.TP(dj, d1); ArmorStandDirector.TP(dj, d2); ArmorStandDirector.TP(dj, d3);ArmorStandDirector.TP(dj, d4);
                }

                if (dj.getInventory().getHeldItemSlot() == 2) {
                    ArmorStandDirector.SlotType slotType = ArmorStandDirector.SlotType.YELLOW;
                    ArmorStandDirector.DirectionType direc1 = ArmorStandDirector.DirectionType.PLAYER1; ArmorStandDirector.DirectionType direc2 = ArmorStandDirector.DirectionType.PLAYER2;ArmorStandDirector.DirectionType direc3 = ArmorStandDirector.DirectionType.PLAYER3; ArmorStandDirector.DirectionType direc4 = ArmorStandDirector.DirectionType.PLAYER4;
                    UUID d1 = ArmorStandDirector.Summon(dj, slotType, direc1); UUID d2 = ArmorStandDirector.Summon(dj, slotType, direc2); UUID d3 = ArmorStandDirector.Summon(dj, slotType, direc3); UUID d4 = ArmorStandDirector.Summon(dj, slotType, direc4);
                    ArmorStandDirector.TP(dj, d1); ArmorStandDirector.TP(dj, d2); ArmorStandDirector.TP(dj, d3);ArmorStandDirector.TP(dj, d4);
                }

                if (dj.getInventory().getHeldItemSlot() == 3) {
                    ArmorStandDirector.SlotType slotType = ArmorStandDirector.SlotType.LIME;
                    ArmorStandDirector.DirectionType direc1 = ArmorStandDirector.DirectionType.PLAYER1; ArmorStandDirector.DirectionType direc2 = ArmorStandDirector.DirectionType.PLAYER2;ArmorStandDirector.DirectionType direc3 = ArmorStandDirector.DirectionType.PLAYER3; ArmorStandDirector.DirectionType direc4 = ArmorStandDirector.DirectionType.PLAYER4;
                    UUID d1 = ArmorStandDirector.Summon(dj, slotType, direc1); UUID d2 = ArmorStandDirector.Summon(dj, slotType, direc2); UUID d3 = ArmorStandDirector.Summon(dj, slotType, direc3); UUID d4 = ArmorStandDirector.Summon(dj, slotType, direc4);
                    ArmorStandDirector.TP(dj, d1); ArmorStandDirector.TP(dj, d2); ArmorStandDirector.TP(dj, d3);ArmorStandDirector.TP(dj, d4);
                }

                if (dj.getInventory().getHeldItemSlot() == 5) {
                    ArmorStandDirector.SlotType slotType = ArmorStandDirector.SlotType.AQUA;
                    ArmorStandDirector.DirectionType direc1 = ArmorStandDirector.DirectionType.PLAYER1; ArmorStandDirector.DirectionType direc2 = ArmorStandDirector.DirectionType.PLAYER2;ArmorStandDirector.DirectionType direc3 = ArmorStandDirector.DirectionType.PLAYER3; ArmorStandDirector.DirectionType direc4 = ArmorStandDirector.DirectionType.PLAYER4;
                    UUID d1 = ArmorStandDirector.Summon(dj, slotType, direc1); UUID d2 = ArmorStandDirector.Summon(dj, slotType, direc2); UUID d3 = ArmorStandDirector.Summon(dj, slotType, direc3); UUID d4 = ArmorStandDirector.Summon(dj, slotType, direc4);
                    ArmorStandDirector.TP(dj, d1); ArmorStandDirector.TP(dj, d2); ArmorStandDirector.TP(dj, d3);ArmorStandDirector.TP(dj, d4);
                }

                if (dj.getInventory().getHeldItemSlot() == 6) {
                    ArmorStandDirector.SlotType slotType = ArmorStandDirector.SlotType.BLUE;
                    ArmorStandDirector.DirectionType direc1 = ArmorStandDirector.DirectionType.PLAYER1; ArmorStandDirector.DirectionType direc2 = ArmorStandDirector.DirectionType.PLAYER2;ArmorStandDirector.DirectionType direc3 = ArmorStandDirector.DirectionType.PLAYER3; ArmorStandDirector.DirectionType direc4 = ArmorStandDirector.DirectionType.PLAYER4;
                    UUID d1 = ArmorStandDirector.Summon(dj, slotType, direc1); UUID d2 = ArmorStandDirector.Summon(dj, slotType, direc2); UUID d3 = ArmorStandDirector.Summon(dj, slotType, direc3); UUID d4 = ArmorStandDirector.Summon(dj, slotType, direc4);
                    ArmorStandDirector.TP(dj, d1); ArmorStandDirector.TP(dj, d2); ArmorStandDirector.TP(dj, d3);ArmorStandDirector.TP(dj, d4);
                }

                if (dj.getInventory().getHeldItemSlot() == 7) {
                    ArmorStandDirector.SlotType slotType = ArmorStandDirector.SlotType.PINK;
                    ArmorStandDirector.DirectionType direc1 = ArmorStandDirector.DirectionType.PLAYER1; ArmorStandDirector.DirectionType direc2 = ArmorStandDirector.DirectionType.PLAYER2;ArmorStandDirector.DirectionType direc3 = ArmorStandDirector.DirectionType.PLAYER3; ArmorStandDirector.DirectionType direc4 = ArmorStandDirector.DirectionType.PLAYER4;
                    UUID d1 = ArmorStandDirector.Summon(dj, slotType, direc1); UUID d2 = ArmorStandDirector.Summon(dj, slotType, direc2); UUID d3 = ArmorStandDirector.Summon(dj, slotType, direc3); UUID d4 = ArmorStandDirector.Summon(dj, slotType, direc4);
                    ArmorStandDirector.TP(dj, d1); ArmorStandDirector.TP(dj, d2); ArmorStandDirector.TP(dj, d3);ArmorStandDirector.TP(dj, d4);
                }

                if (dj.getInventory().getHeldItemSlot() == 8) {
                    ArmorStandDirector.SlotType slotType = ArmorStandDirector.SlotType.GRAY;
                    ArmorStandDirector.DirectionType direc1 = ArmorStandDirector.DirectionType.PLAYER1; ArmorStandDirector.DirectionType direc2 = ArmorStandDirector.DirectionType.PLAYER2;ArmorStandDirector.DirectionType direc3 = ArmorStandDirector.DirectionType.PLAYER3; ArmorStandDirector.DirectionType direc4 = ArmorStandDirector.DirectionType.PLAYER4;
                    UUID d1 = ArmorStandDirector.Summon(dj, slotType, direc1); UUID d2 = ArmorStandDirector.Summon(dj, slotType, direc2); UUID d3 = ArmorStandDirector.Summon(dj, slotType, direc3); UUID d4 = ArmorStandDirector.Summon(dj, slotType, direc4);
                    ArmorStandDirector.TP(dj, d1); ArmorStandDirector.TP(dj, d2); ArmorStandDirector.TP(dj, d3);ArmorStandDirector.TP(dj, d4);
                }

                Inventory playerInv = dj.getInventory();
                playerInv.setItem(0, new ItemStack(Material.RED_CONCRETE, 1));
                playerInv.setItem(1, new ItemStack(Material.ORANGE_CONCRETE, 1));
                playerInv.setItem(2, new ItemStack(Material.YELLOW_CONCRETE, 1));
                playerInv.setItem(3, new ItemStack(Material.LIME_CONCRETE, 1));
                playerInv.setItem(4, new ItemStack(Material.WHITE_CONCRETE, 1));
                playerInv.setItem(5, new ItemStack(Material.LIGHT_BLUE_CONCRETE, 1));
                playerInv.setItem(6, new ItemStack(Material.BLUE_CONCRETE, 1));
                playerInv.setItem(7, new ItemStack(Material.MAGENTA_CONCRETE, 1));
                playerInv.setItem(8, new ItemStack(Material.LIGHT_GRAY_CONCRETE, 1));

                dj.getInventory().setHeldItemSlot(4);
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("rhythmcraft"), 1L, 1L);

    }
}
