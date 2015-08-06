package in.novopay.darpan;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by khushpreetsingh on 8/4/15.
 */
public class Music implements Serializable{
    private String songName;
    private String albumName;
    private String singerName;
    private String imageUrl;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Music(String albumName, String songName, String singerName, String imageUrl) {
        this.albumName = albumName;
        this.songName = songName;
        this.singerName = singerName;
        this.imageUrl = imageUrl;

    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

}
