package fast.wq.com.fastandroid.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

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
            //不满足条件 让采样率翻2倍
            while ((halfwidth/inSampleSize) >= reqWidth && (halfheight/inSampleSize)>= reqHeight){
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /**
     *  根据图片的宽高，选择不同的scletype
     * @param srcWidth 下载的宽高
     * @param srcHeight
     * @param reqWidth 75 需要的宽高
     * @param reqHeight 98
     */
    public static void calScleType(ImageView mImageView,int srcWidth, int srcHeight, int reqWidth, int reqHeight){

        //宽高都小于 imageview
        if (srcWidth <= reqWidth && srcHeight <= reqHeight){
            mImageView.setScaleType(ImageView.ScaleType.CENTER);
        }
        //宽高都大于 imageview
        if (srcWidth > reqWidth && srcHeight >reqHeight){
//            mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }

        //宽 小于 imageview 高大
        if (srcWidth < reqWidth && srcHeight >reqHeight){
            mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }

        //宽 大于 imageview 高小
        if (srcWidth < reqWidth && srcHeight >reqHeight){
            mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }

    }
}
