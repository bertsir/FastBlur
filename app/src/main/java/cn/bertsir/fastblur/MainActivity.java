package cn.bertsir.fastblur;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import cn.bertsir.blurlibrary.ImageBlur;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";


    private ImageView iv;
    private SeekBar sb,sb1;
    private float scale = 0.1f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        sb = (SeekBar) findViewById(R.id.sb);
        sb1 = (SeekBar) findViewById(R.id.sb1);
        Bitmap bitmap = ImageBlur.doBlurJniBitMap(BitmapFactory.decodeResource(getResources(), R.drawable.haimian),
                1, true);
        iv.setImageBitmap(bitmap);


        //改变模糊度
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e(TAG, "onProgressChanged: 模糊前"+System.currentTimeMillis() );
                Bitmap bitmap = ImageBlur.doBlurJniBitMapFast(BitmapFactory.decodeResource(getResources(), R
                        .drawable.haimian),progress, scale, true);
                Log.e(TAG, "onProgressChanged: 模糊后"+System.currentTimeMillis() );
                iv.setImageBitmap(bitmap);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        //改变bitmap宽高
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float v = Float.valueOf(String.valueOf(progress)) / 10;
                if(v == 0.0f){
                    scale = 0.1f;
                }else{
                    scale = v;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


    }

}
