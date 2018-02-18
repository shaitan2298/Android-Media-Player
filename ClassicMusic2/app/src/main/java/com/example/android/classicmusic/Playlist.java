package com.example.android.classicmusic;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ankita on 11/8/2017.
 */

public class Playlist extends AppCompatActivity {
    private static final int My_Permission_Request = 1;
    ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter adapter;
    MediaPlayer mp;
    ActionBar ab;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);
        try {
            if (ContextCompat.checkSelfPermission(Playlist.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(Playlist.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(Playlist.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, My_Permission_Request);
                } else {
                    ActivityCompat.requestPermissions(Playlist.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, My_Permission_Request);
                }
            } else {
                doStuff();

            }
        } catch (Exception ex) {
            Log.e("MyException", ex.toString());
        }
        registerForContextMenu(listView);
        ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.show();
    }

    private void doStuff() {
        try {
            listView = (ListView) findViewById(R.id.listView);
            arrayList = new ArrayList<String>();
            getMusic();
            adapter = new ArrayAdapter(Playlist.this, android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(adapter);
            listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                }


                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        int songIndex = i;
                        Intent in = new Intent(Playlist.this, MainActivity.class);
                        in.putExtra("songIndex", songIndex);
                        setResult(100, in);
                        startActivity(in);
                        mp.start();
                    } catch (Exception ex) {
                        Log.e("MyException",ex.toString());
                    }
                }
            });
        } catch (Exception ex) {
            Log.e("MyException", ex.toString());
        }

    }

    public void getMusic() {
        try {
            ContentResolver contentResolver = getContentResolver();
            Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            Cursor cursor = contentResolver.query(musicUri, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int songTitle = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                int songArtist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

                do {
                    String currentTitle = cursor.getString(songTitle);
                    String currentArtist = cursor.getString(songArtist);
                    arrayList.add(currentTitle + "\n" + currentArtist);

                } while (cursor.moveToNext());

            }
        } catch (Exception ex) {
            Log.e("MyException", ex.toString());
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case My_Permission_Request: {
                try {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if (ContextCompat.checkSelfPermission(Playlist.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                            doStuff();
                        } else {
                            Toast.makeText(this, "No permission granted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        return;
                    }
                } catch (Exception ex) {
                    Log.e("MyException", ex.toString());
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ab.show();
        int id=item.getItemId();
         if(id==R.id.profile) {
                Intent i1=new Intent(Playlist.this,Profile.class);
                startActivity(i1);
         }
        if(id==R.id.settings) {

            Intent i2 = new Intent(Playlist.this, Settings.class);
            startActivity(i2);
        }



        return super.onOptionsItemSelected(item);
    }


}
