package butterknife.internal;

import android.os.Handler;
import android.view.View;

/**
 * A {@linkplain View.OnClickListener click listener} that debounces multiple clicks posted in the
 * same frame. A click on one button disables all buttons for that frame.
 */
public abstract class DebouncingOnTapListener implements View.OnClickListener {

    private int taps = 0;
    private Handler handler = new Handler();

    @Override
    public final void onClick(View v) {
        taps += 1;
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                taps = 0;
            }
        }, 1000);
        doTap(v, taps);
    }

    public abstract void doTap(View v, int taps);
}
