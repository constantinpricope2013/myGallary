package ro.mygallery.mygallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{

    private Context context;
    public Integer[] images = {
            R.drawable.image1,R.drawable.image2,
            R.drawable.image3,R.drawable.image4,
            R.drawable.image5,R.drawable.image6,
            R.drawable.image7,R.drawable.image8,
            R.drawable.image9
    };
    public ImageAdapter(Context c){
        context = c;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(240,240));
        return imageView;
    }
}
