package cn.bertsir.blurlibrary;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by QIUJUER on 2014/4/19.
 */
public class ImageBlur {
    static {
        System.loadLibrary("ImageBlur");
    }


    public static native void blurIntArray(int[] pImg, int w, int h, int r);
    public static native void blurBitMap(Bitmap bitmap, int r);


    /**
     * 压缩Bitmap的宽高，可以提高模糊效率
     * @param bm
     * @return
     */
    public static Bitmap zoomImg(Bitmap bm,float f){

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = f;
        float scaleHeight = f;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }


    /**
     * 快速模糊bitmap
     * @param sentBitmap Bitmap
     * @param radius  模糊度
     * @param scale 质量（其实就是Bitmap的宽高压缩比，比例越小速度越快，内存占用越低）取值0.1f-1.0f
     * @param canReuseInBitmap 是否复用一般true就行
     * @return
     */
    public static Bitmap doBlurJniBitMapFast(Bitmap sentBitmap, int radius,float scale, boolean canReuseInBitmap) {
        Bitmap bitmap = doBlurJniBitMap(zoomImg(sentBitmap, scale), radius, canReuseInBitmap);
        return (bitmap);
    }

    public static Bitmap doBlurJniBitMap(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {
        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }

        if (radius < 1) {
            return (null);
        }

        blurBitMap(bitmap, radius);
        return (bitmap);
    }



}
