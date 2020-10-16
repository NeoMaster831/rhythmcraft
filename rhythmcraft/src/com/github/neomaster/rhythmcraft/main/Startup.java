package com.github.neomaster.rhythmcraft.main;

import com.github.neomaster.rhythmcraft.command.RhythmCraft;
import com.github.neomaster.rhythmcraft.listener.*;
import com.github.neomaster.rhythmcraft.ui.GameStartUI;
import com.github.neomaster.rhythmcraft.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class Startup extends JavaPlugin {

    public void onEnable() {

        // --------------- INIT FILES ----------------- (plugin storage files)

        if (!(new File("plugins/RhythmCraft/").exists())) {
            Bukkit.getConsoleSender().sendMessage(ChatUtils.chat("No Rhythmcraft files found! Making...", "#FF0000"));
            initFiles();
            Bukkit.getConsoleSender().sendMessage(ChatUtils.chat("Made.", "#00FF00"));
        }

        Bukkit.getConsoleSender().sendMessage(ChatUtils.chat("Welcome to Rhythmcraft!", "#9571FF"));

        List<String> songsList = getSongsList();
        Bukkit.getConsoleSender().sendMessage(ChatUtils.chat("Songs(" + songsList.size() + "):", "#B7FFDA"));
        for (int i = 0; i < songsList.size(); i++) {
            Bukkit.getConsoleSender().sendMessage(ChatUtils.chat("    - " + songsList.get(i), "#B7FFDA"));
        }

        // -------------------------- REGISTER EVENTS -------------------------------

        try {
            putSongLength();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new RhythmCraft(this);
        new RcTabComplete(this);
        new GamePlayVarInit(this);
        new GameStartClicked(this);
        new SetPlaySpeedListener(this);
        new SetSongUI(this);
        new SetWorldUI(this);
        GameStartUI.intialize();
        com.github.neomaster.rhythmcraft.ui.SetSongUI.intialize();
        com.github.neomaster.rhythmcraft.ui.SetWorldUI.intialize();
    }

    public final void initFiles() {

        File songFile = new File("plugins/RhythmCraft/songs/");
        File esperFile = new File("plugins/RhythmCraft/espers/");

        songFile.mkdirs(); esperFile.mkdirs();

    }

    public final List<String> getSongsList() {

        File songFile = new File("plugins/RhythmCraft/songs/");
        String[] songLists = songFile.list();
        List<String> toReturn = new ArrayList<String>();

        for (String s : songLists) {
            toReturn.add(s);
        }

        return toReturn;
    }

    public void putSongLength() throws IOException {

        for (File f : new File("plugins/RhythmCraft/songs/").listFiles()) {

            String a = f.getName().split(".ogg")[0].toLowerCase().replace(' ', '_');
            GamePlayVariables.SongLength.put(a, calculateDuration(f));

        }
    }

    public double calculateDuration(final File oggFile) throws IOException {
        int rate = -1;
        int length = -1;

        int size = (int) oggFile.length();
        byte[] t = new byte[size];

        FileInputStream stream = new FileInputStream(oggFile);
        stream.read(t);

        for (int i = size-1-8-2-4; i>=0 && length<0; i--) { //4 bytes for "OggS", 2 unused bytes, 8 bytes for length
            // Looking for length (value after last "OggS")
            if (
                    t[i]==(byte)'O'
                            && t[i+1]==(byte)'g'
                            && t[i+2]==(byte)'g'
                            && t[i+3]==(byte)'S'
            ) {
                byte[] byteArray = new byte[]{t[i+6],t[i+7],t[i+8],t[i+9],t[i+10],t[i+11],t[i+12],t[i+13]};
                ByteBuffer bb = ByteBuffer.wrap(byteArray);
                bb.order(ByteOrder.LITTLE_ENDIAN);
                length = bb.getInt(0);
            }
        }
        for (int i = 0; i<size-8-2-4 && rate<0; i++) {
            // Looking for rate (first value after "vorbis")
            if (
                    t[i]==(byte)'v'
                            && t[i+1]==(byte)'o'
                            && t[i+2]==(byte)'r'
                            && t[i+3]==(byte)'b'
                            && t[i+4]==(byte)'i'
                            && t[i+5]==(byte)'s'
            ) {
                byte[] byteArray = new byte[]{t[i+11],t[i+12],t[i+13],t[i+14]};
                ByteBuffer bb = ByteBuffer.wrap(byteArray);
                bb.order(ByteOrder.LITTLE_ENDIAN);
                rate = bb.getInt(0);
            }

        }
        stream.close();

        double duration = (double) (length*1000) / (double) rate;
        return duration;
    }
}
