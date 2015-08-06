package in.novopay.darpan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.novopay.khushpreetsingh.mymusic.R;

import in.novopay.darpan.Models.Collection1;
import in.novopay.darpan.Models.MusicApiResponse;
import in.novopay.darpan.network.MusicAPI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import hugo.weaving.DebugLog;
import in.novopay.darpan.provider.MusicDatabase;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by  on 8/4/15.
 */
public class FirstFragment extends Fragment {

    private ListView listView;
    private List<Music> musicList = new ArrayList<>();
    private MusicAdapter musicAdapter;
    private MusicApiResponse musicResponse;
    //MusicCursor musicCursorAdaptor;
    //MusicDatabase musicDbHelper;
    //SQLiteDatabase musicDb;


    @Nullable
    @Override
    @DebugLog
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_first_listview);

        /*musicDbHelper=new MusicDatabase(getActivity());

        musicDb=musicDbHelper.getReadableDatabase();
        Cursor cursor=musicDb.query(MusicDatabase.Tables.MUSIC, null, null, null, null, null, null);
        musicCursorAdaptor=new MusicCursor(getActivity(),cursor);
        listView.setAdapter(musicCursorAdaptor);*/


        // Uncomment for API Stuff
        MusicAPI.getApi().getMusicList(new Callback<MusicApiResponse>() {

            @Override
            @DebugLog
            public void success(MusicApiResponse musicApiResponse, Response response) {

                musicAdapter = new MusicAdapter(getActivity(), musicApiResponse.getResults().getCollection1());
                listView.setAdapter(musicAdapter);
                musicResponse = musicApiResponse;

            }

            @Override
            @DebugLog
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "some error"+ error.getResponse().getReason(),Toast.LENGTH_SHORT).show();
            }
        });

       /*MusicSQLiteOpenHelper musicSQLiteOpenHelper = new MusicSQLiteOpenHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = musicSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(MusicSQLiteOpenHelper.Tables.MUSIC,null,null,null,null,null,null);
        System.out.println(cursor.getCount());*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MusicServices.class);
                intent.putExtra(MusicServices.KEY_METHOD, MusicServices.METHOD_PLAY);
                getActivity().startService(intent);
                Intent intentActivity = new Intent(getActivity(), MusicActivity.class);
                intentActivity.putExtra("MUSICLIST",musicResponse);
                intentActivity.putExtra("POS", position);
                getActivity().startActivity(intentActivity);

            }
        });
        return view;
    }

    @Override
    @DebugLog
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    @DebugLog
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
