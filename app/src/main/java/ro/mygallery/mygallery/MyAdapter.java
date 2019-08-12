package ro.mygallery.mygallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    //this is for the list of the images (RecycleView)
    private ArrayList<Cell> gallary_list;
    private Context context;

    public MyAdapter (Context context, ArrayList<Cell> gallary_list)
    {
        this.gallary_list = gallary_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_cell, viewGroup, false);
        return new MyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.image_view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        set_image_from_path(gallary_list.get(i).getPath(), viewHolder.image_view);
        viewHolder.image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //what happens when you click on image
                Toast.makeText(context, "" + gallary_list.get(i).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return gallary_list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image_view;

        public ViewHolder(View view)
        {
            super(view);
            image_view = (ImageView) view.findViewById(R.id.img);
        }

    }
    private void set_image_from_path (String path, ImageView image)
    {
        File imgFile = new File(path);
        if(imgFile.exists())
        {
            Bitmap myBitmap = ImageHelper.decode_sample_bitmap_from_path(imgFile.getAbsolutePath(), 200,200);
            image.setImageBitmap(myBitmap);
        }
    }
}
