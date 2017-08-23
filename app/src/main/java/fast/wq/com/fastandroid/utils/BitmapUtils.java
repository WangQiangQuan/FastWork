package fast.wq.com.fastandroid.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 *
 */

public class BitmapUtils {
    public static Bitmap decodeSampleBitmapFromPath(String path,int reqWidth,int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

//        BitmapFactory.decodeResource(res,resId,options);
        BitmapFactory.decodeFile(path,options);
        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);

        options.inJustDecodeBounds = false;
        return    BitmapFactory.decodeFile(path,options);
    }
    public static Bitmap decodeSampleBitmapFromResource(Resources res,int resId,int reqWidth,int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(res,resId,options);

        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);

        options.inJustDecodeBounds = false;
        return    BitmapFactory.decodeResource(res,resId,options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        int inSampleSize = 1;
        final int width = options.outWidth;
        final int height = options.outHeight;

        if (width > reqWidth || height >reqHeight){
            final int halfwidth = width / 2;
            final int halfheight = height /2;
            while ((halfwidth/inSampleSize) >= reqWidth && (halfheight/inSampleSize)>= reqHeight){
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
