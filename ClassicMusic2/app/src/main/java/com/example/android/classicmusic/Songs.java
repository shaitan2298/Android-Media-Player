package com.example.android.classicmusic;

import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ankita on 11/7/2017.
 */

public class Songs {
    final String MEDIA_PATH = new String("/sdcard/");
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    public Songs() {

    }
    public ArrayList<HashMap<String, String>> getPlayList() {
        File home = new File(MEDIA_PATH);
        try {
            if (home.listFiles(new FileExtensionFilter()).length > 0) {
                for (File file : home.listFiles(new FileExtensionFilter())) {
                    HashMap<String, String> song = new HashMap<String, String>();
                    song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
                    song.put("songPath", file.getPath());
                    songsList.add(song);
                }
            }
        } catch (Exception ex) {
            Log.e("MyException", ex.toString());
        }
        return songsList;
    }
    class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {

            return (name.endsWith(".mp3") || name.endsWith(".MP3"));

        }
    }
}



