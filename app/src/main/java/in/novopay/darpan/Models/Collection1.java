
package in.novopay.darpan.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Collection1 implements Serializable{

    @Expose
    private ImageUrl ImageUrl;
    @Expose
    private ArtistName artistName;
    @Expose
    private SongName songName;
    @Expose
    private Integer index;
    @Expose
    private String url;

    /**
     * 
     * @return
     *     The ImageUrl
     */
    public ImageUrl getImageUrl() {
        return ImageUrl;
    }

    /**
     * 
     * @param ImageUrl
     *     The ImageUrl
     */
    public void setImageUrl(ImageUrl ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    /**
     * 
     * @return
     *     The artistName
     */
    public ArtistName getArtistName() {
        return artistName;
    }

    /**
     * 
     * @param artistName
     *     The artistName
     */
    public void setArtistName(ArtistName artistName) {
        this.artistName = artistName;
    }

    /**
     * 
     * @return
     *     The songName
     */
    public SongName getSongName() {
        return songName;
    }

    /**
     * 
     * @param songName
     *     The songName
     */
    public void setSongName(SongName songName) {
        this.songName = songName;
    }

    /**
     * 
     * @return
     *     The index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * 
     * @param index
     *     The index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
