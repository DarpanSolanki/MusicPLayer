package in.novopay.darpan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import in.novopay.darpan.Models.Collection1;

import com.novopay.khushpreetsingh.mymusic.R;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by khushpreetsingh on 8/4/15.
 */
public class MusicAdapter extends BaseAdapter {
    WeakReference<Context> contextWeakReference;
    List<Collection1> musicList;
    public MusicAdapter(Context context, List<Collection1> musicList) {
        this.contextWeakReference = new WeakReference<Context>(context);
        this.musicList = musicList;

    }

    @Override
    public int getCount() {
        return musicList.size();
    }

    @Override
    public Collection1 getItem(int position) {
        return musicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView albumView;
        TextView artistView;
        TextView songView;
        ImageView imageView;

    }
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if(convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(contextWeakReference.get());
            view = layoutInflater.inflate(R.layout.item_music, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.albumView = (TextView) view.findViewById(R.id.item_music_album);
            viewHolder.artistView = (TextView) view.findViewById(R.id.item_music_artist);
            viewHolder.songView = (TextView) view.findViewById(R.id.item_music_song);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.item_music_image);

            view.setTag(viewHolder);
        }

    if(viewHolder == null){
        viewHolder = (ViewHolder) view.getTag();
    }

        Collection1 music = getItem(position);

        viewHolder.albumView.setText(music.getSongName().getText());
        viewHolder.artistView.setText(music.getArtistName().getText());
        viewHolder.songView.setText(music.getSongName().getText());
        Picasso.with(contextWeakReference.get()).load(music.getImageUrl().getSrc()).error(R.drawable.thumb).into(viewHolder.imageView);



        return view;
    }
}
