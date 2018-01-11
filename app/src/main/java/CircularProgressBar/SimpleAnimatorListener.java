package CircularProgressBar;

/**
 * Created by admin on 03/02/2016.
 */

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.CallSuper;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
abstract class SimpleAnimatorListener implements Animator.AnimatorListener{
    private boolean mStarted = false;
    private boolean mCancelled = false;

    @Override
    @CallSuper
    public void onAnimationStart(Animator animation) {
        mCancelled = false;
        mStarted = true;
    }

    @Override
    public final void onAnimationEnd(Animator animation) {
        onPreAnimationEnd(animation);
        mStarted = false;
    }

    protected void onPreAnimationEnd(Animator animation) {
    }

    @Override
    @CallSuper
    public void onAnimationCancel(Animator animation) {
        mCancelled = true;
    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    public boolean isStartedAndNotCancelled() {
        return mStarted && !mCancelled;
    }
}

