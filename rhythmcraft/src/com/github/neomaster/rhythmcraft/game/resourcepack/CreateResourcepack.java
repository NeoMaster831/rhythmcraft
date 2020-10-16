package com.github.neomaster.rhythmcraft.game.resourcepack;

import com.github.neomaster.rhythmcraft.utils.ChatUtils;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;

public final class CreateResourcepack {

    public static final void createResoucepack(Player p, ArrayList<File> Songs) {

        String path = "plugins/RhythmCraft/rc_pack";
        File packDirectory = new File(path);

        if (packDirectory.exists()) {
            p.sendMessage(ChatUtils.chat("이미 파일이 있습니다. 예전의 것이라면 삭제하고 다시 해주세요.", "#FF0000"));
            return;
        }

        packDirectory.mkdir();

        // ------------- aaa_____________- ---

        File mcmeta = new File(path + "/pack.mcmeta");
        File assets = new File(path + "/assets");
        File minecraft = new File(path + "/assets/minecraft");
        File sounds = new File(path + "/assets/minecraft/sounds");
        File soundsJson = new File(path + "/assets/minecraft/sounds.json");
        File customMusics = new File(path + "/assets/minecraft/sounds/custommusics");

        assets.mkdirs();
        minecraft.mkdirs();
        sounds.mkdirs();
        customMusics.mkdirs();
        BufferedWriter mcmetaBw;
        BufferedWriter customMusicsBw;
        ArrayList<File> dummyFiles = new ArrayList<File>();

        try { // 노래 파일 복사

            int fileDummyIndex = 0;
            while (fileDummyIndex < Songs.size()) {

                String fileName = (Songs.get(fileDummyIndex).getName().toLowerCase()).replace(' ', '_');

                File dummyFile = new File(path + "/assets/minecraft/sounds/custommusics/" + fileName);
                dummyFiles.add(dummyFile);
                fileDummyIndex++;
            }

            for (int i = 0; i < dummyFiles.size(); i++) {
                FileInputStream fis = new FileInputStream(Songs.get(i));
                FileOutputStream fos = new FileOutputStream(dummyFiles.get(i));

                int Byte = 0;
                while ((Byte = fis.read()) != -1) {
                    fos.write(Byte);
                }

                fos.flush();
                fis.close(); fos.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try { // pack.mcmeta 작성

            mcmetaBw = new BufferedWriter(new FileWriter(mcmeta));
            String mcmetaValue = "{\r\n" +
                    "   \"pack\": {\r\n" +
                    "      \"pack_format\": 5,\r\n" +
                    "      \"description\": \"Desc\"\r\n" +
                    "   }\r\n" +
                    "}";

            mcmetaBw.write(mcmetaValue);
            mcmetaBw.flush();
            mcmetaBw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try { // sounds.json 작성

            customMusicsBw = new BufferedWriter(new FileWriter(soundsJson));
            ArrayList<String> songNames = new ArrayList<String>();
            ArrayList<String> jsonFormat = new ArrayList<String>();

            int n = 0;
            while (n < Songs.size()) { // songName init
                String[] splitedFileName = ((Songs.get(n).getName().toLowerCase()).replace(' ', '_')).split(".ogg");
                String fileName = splitedFileName[0];
                songNames.add(fileName);
                n++;
            }

            int a = 0;
            while (a < Songs.size()) { // cmValue 작성

                if (a == Songs.size() - 1) {
                    String formats = "\t\"custommusic." + songNames.get(a) + "\":\r\n" +
                            "\t{\r\n" +
                            "\t\t\"sounds\":\r\n" +
                            "\t\t[\r\n" +
                            "\t\t\t\"custommusics/" + songNames.get(a) + "\"\r\n" +
                            "\t\t]\r\n" +
                            "\t}\r\n";
                    jsonFormat.add(formats); a++;
                    continue;
                }

                String formats = "\t\"custommusic." + songNames.get(a) + "\":\r\n" +
                        "\t{\r\n" +
                        "\t\t\"sounds\":\r\n" +
                        "\t\t[\r\n" +
                        "\t\t\t\"custommusics/" + songNames.get(a) + "\"\r\n" +
                        "\t\t]\r\n" +
                        "\t},\r\n";
                jsonFormat.add(formats); a++;
            }

            String cmValue = "{\r\n" + String.join("", jsonFormat) + "}";
            customMusicsBw.write(cmValue);

            customMusicsBw.flush();
            customMusicsBw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        p.sendMessage(ChatUtils.chat("리소스팩을 생성 완료하였습니다. [plugins/RhythmCraft]", "#00FF00"));
    }

}
