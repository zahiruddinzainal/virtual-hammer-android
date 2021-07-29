package zvhir.dev.virtualhammer;


import android.app.Activity;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;
import com.squareup.seismic.ShakeDetector;




public class MainActivity extends Activity implements ShakeDetector.Listener {

    MediaPlayer player;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);

    }

    @Override public void hearShake() {
        play();
    }


    private void play() {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.hammer);
        }
        player.start();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

}
