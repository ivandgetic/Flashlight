package ivandgetic.Flashlight;

import android.app.Activity;
import android.app.ActionBar;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.hardware.Camera;
import android.os.PowerManager;
import android.view.WindowManager;
import android.widget.SeekBar;

public class MainActivity extends Activity {
    private boolean isopent = false;
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        SeekBar seekbar = (SeekBar) findViewById(R.id.seekbar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 0) {
                    setScreenBrightness(MainActivity.this, progress);
                }
            }
        });
    }

    public void kaiguan(View view) {
        if (!isopent) {
            camera = Camera.open();
            Camera.Parameters params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isopent = true;
        } else {
            camera.stopPreview();
            camera.release();
            isopent = false;
        }
    }

    public void setScreenBrightness(Activity activity, int value) {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.screenBrightness = value * (1.0f / 100.0f);
        activity.getWindow().setAttributes(params);
    }

}
