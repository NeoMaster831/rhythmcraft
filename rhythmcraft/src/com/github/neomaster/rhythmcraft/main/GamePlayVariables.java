package com.github.neomaster.rhythmcraft.main;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public final class GamePlayVariables {
    /*
    GameStartOption에 있는 SetWorld 옵션의 주요 변수입니다. 기본값은 플레이어가 들어올때부터 world로 설정되어 있습니다.
     */
    public static HashMap<Player, World> PlayWorld = new HashMap<Player, World>();

    /*
    GameStartOption에 있는 PlaySpeed 옵션의 주요 변수입니다. 기본값은 플레이어가 들어올떄부터 0.33으로 되어있습니다.
    *********** %value% block per tick **************************
     */
    public static HashMap<Player, Double> PlaySpeed = new HashMap<Player, Double>();
    public static ArrayList<Player> isSettingSpeed = new ArrayList<Player>();

    /*
    GameStartOption에 있는 SelectSong 옵션의 변수입니다. 기본값은 무작위입니다.
     */
    public static HashMap<Player, String> PlaySong = new HashMap<Player, String>();

    /*
    노래들의 길이입니다. 서버를 시작/리붓할 때마다 처음부터 정해져 있습니다.
     */
    public static HashMap<String, Double> SongLength = new HashMap<>();
    // ====================== Public var

}
