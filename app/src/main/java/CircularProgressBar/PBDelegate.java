package CircularProgressBar;

/**
 * Created by admin on 12/12/2015.
 */

import android.graphics.Canvas;
import android.graphics.Paint;

interface PBDelegate {
    void draw(Canvas canvas, Paint paint);

    void start();

    void stop();

    void progressiveStop(CircularProgressDrawable.OnEndListener listener);
}
