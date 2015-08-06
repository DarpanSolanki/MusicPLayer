package in.novopay.darpan.network;

import in.novopay.darpan.Models.MusicApiResponse;

import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by khushpreetsingh on 8/6/15.
 */
public class MusicAPI {
    private static final String URL = "https://www.kimonolabs.com/api";
    private static MusicInterface musicInterface = null;
    public static MusicInterface getApi() {
        if(musicInterface == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(URL)
                    .build();
            musicInterface = restAdapter.create(MusicInterface.class);
        }
        return musicInterface;
    }



    public interface MusicInterface{
        @GET("/4fa3g2ku?apikey=K1ZY5s3LrzsuCwWwsHt4LrEuQEjYobqi")
        void getMusicList(retrofit.Callback<MusicApiResponse> musicApiResponse);

    }
}
