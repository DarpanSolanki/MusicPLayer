package in.novopay.darpan.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import hugo.weaving.DebugLog;

/**
 * Created by darpansolanki on 8/6/15.
 */
public class MusicDatabase extends SQLiteOpenHelper {
    public static int VERSION=1;
    public static String DATABASE_NAME="musicdb";
    public MusicDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public interface Tables{
        String MUSIC="music";

    }

    public interface TableMusic{
        String MUSIC_NAME="music_name";
        String MUSIC_AUTHOR="music_author";
        String MUSIC_IMAGE_URL="image_url";
    }

    final String CREATE_TABLE_MUSIC="create table "+Tables.MUSIC+"("
            + BaseColumns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +TableMusic.MUSIC_NAME+" TEXT NOT NULL,"
            +TableMusic.MUSIC_AUTHOR+" TEXT NOT NULL,"
            +TableMusic.MUSIC_IMAGE_URL+" TEXT NOT NULL);";

    @DebugLog
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_MUSIC);
        ContentValues contentValues=new ContentValues();
        contentValues.put(TableMusic.MUSIC_NAME, "Darpan music");
        contentValues.put(TableMusic.MUSIC_AUTHOR,"Darpan is author");
        contentValues.put(TableMusic.MUSIC_IMAGE_URL,"https://www.apple.com/autopush/us/itunes/charts/songs/images/2015/7/2181930b-360e-16c4-9e5c-f3c2362900f8UMG_cvrart_00602547444516_01_RGB72_1500x1500_15UMGIM34395.jpg");
        db.insert(Tables.MUSIC,null, contentValues);
        db.insert(Tables.MUSIC,null,contentValues);
        db.insert(Tables.MUSIC,null,contentValues);
        db.insert(Tables.MUSIC,null,contentValues);
        db.insert(Tables.MUSIC,null,contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}