package ro.mygallery.mygallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageHelper {
    //this method helps decrease image size for better performance
    private static int calculate_int_sample_size(BitmapFactory.Options options,int req_width, int req_height)
    {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int in_smaple_size = 1;
        if(height > req_height || width >req_width)
        {
            final int half_height = height / 2;
            final int half_width = width / 2;
            while( (half_height / in_smaple_size) >= req_height || (half_width / in_smaple_size) >= req_width )
            {
                in_smaple_size *= 2;
            }
        }

        return in_smaple_size;
    }

    public static Bitmap decode_sample_bitmap_from_path(String path_name, int req_width, int req_height)
    {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path_name, options);

        options.inSampleSize = calculate_int_sample_size(options, req_width, req_height);

        options.inJustDecodeBounds = false;
        return  BitmapFactory.decodeFile(path_name,options);
    }
}
