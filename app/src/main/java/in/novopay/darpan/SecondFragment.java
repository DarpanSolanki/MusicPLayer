package in.novopay.darpan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.novopay.khushpreetsingh.mymusic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khushpreetsingh on 8/4/15.
 */
public class SecondFragment extends Fragment {

    private GridView gridView;
    private List<Music> musicList = new ArrayList<>();
    private MusicAdapter musicAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicList.add(new Music("0SongName", "0Album", "0Artist","http://loremflickr.com/320/240"));
        musicList.add(new Music("1SongName", "1Album", "1Artist","http://loremflickr.com/320/240"));
        musicList.add(new Music("2SongName", "2Album", "1Artist","http://loremflickr.com/320/240"));
        musicList.add(new Music("3SongName", "3Album", "3Artist","http://loremflickr.com/320/240"));
        musicList.add(new Music("4SongName", "4Album", "4Artist", "http://loremflickr.com/320/240"));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        gridView = (GridView)  view.findViewById(R.id.gridview);
        /*musicAdapter = new MusicAdapter(getActivity(), musicList);
        gridView.setAdapter(musicAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),MusicActivity.class);
                intent.putExtra("music",(Serializable)musicList);
                startActivity(intent);
            }
        });*/
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
