package com.netease.glsurfaceView.utils;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BitmapConform {
  public static Bitmap toConformBitmap(Bitmap background) {
    if (background == null) {
      return null;
    }

    try {
      int bgWidth = background.getWidth();
      int bgHeight = background.getHeight();

      if (NumberUtils.isPowerOfTwo(bgWidth)
          && NumberUtils.isPowerOfTwo(bgHeight)) {
        return background;
      }

      int targetW = NumberUtils.nextPowerOfTwo(bgWidth);
      int targetH = NumberUtils.nextPowerOfTwo(bgHeight);

      Bitmap newbmp = Bitmap.createBitmap(targetW, targetH, Config.ARGB_8888);
      Canvas cv = new Canvas(newbmp);

      Rect src = new Rect(0, 0, bgWidth, bgHeight);
      Rect dst = new Rect(0, 0, targetW, targetH);
      cv.drawBitmap(background, src, dst, null);

      cv.save(Canvas.ALL_SAVE_FLAG);
      cv.restore();
      background.recycle();
      return newbmp;
    } catch (OutOfMemoryError e) {
      return null;
    } catch (Exception e) {
      return null;
    }
  }

  public static Bitmap readBitmap(Context context, String path) {
    Bitmap bitmap = null;
    bitmap = revitionImageSize(context, path);
    // try {
    // AssetManager assetManager = context.getAssets();
    // InputStream is = assetManager.open(path);
    // bitmap = BitmapFactory.decodeStream(is);
    //
    // } catch (OutOfMemoryError e) {
    // return null;
    // } catch (Exception e) {
    // return null;
    // }
    return bitmap;
  }

  public static Bitmap revitionImageSize(Context context, String path) {
    if (path == null) {
      return null;
    }

    // DisplayMetrics outMetrics = new DisplayMetrics();
    // getWindowManager().getDefaultDisplay().getMetrics(outMetrics);

    Bitmap bitmap = null;
    int maxSize = 0;
    // float xdpi = context.getResources().getDisplayMetrics().xdpi;
    // float ydpi = context.getResources().getDisplayMetrics().xdpi;
    // float minDpi = xdpi > ydpi ? ydpi : xdpi;
    // Log.d("zyyin", "xdpi:" + xdpi + " ydpi:" + ydpi + " minDpi:" + minDpi);
    int minDpi = context.getResources().getDisplayMetrics().densityDpi;
    if (minDpi < 240) {
      maxSize = 512;
    } else {
      maxSize = 1000;
    }
    try {
      // BufferedInputStream in;
      // in = new BufferedInputStream(new FileInputStream(new File(path)));
      // AssetManager assetManager = context.getAssets();
      InputStream in = context.getAssets().open(path);
      BitmapFactory.Options options = new BitmapFactory.Options();
      options.inJustDecodeBounds = true;

      BitmapFactory.decodeStream(in, null, options);
      in.close();
      int i = 0;
      while (true) {
        if ((options.outWidth >> i <= maxSize)
            && (options.outHeight >> i <= maxSize)) {
          in = context.getAssets().open(path);
          options.inSampleSize = (int) Math.pow(2.0D, i);
          options.inJustDecodeBounds = false;
          bitmap = BitmapFactory.decodeStream(in, null, options);
          break;
        }
        i += 1;
      }
      in.close();
    } catch (OutOfMemoryError e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bitmap;
  }

}
