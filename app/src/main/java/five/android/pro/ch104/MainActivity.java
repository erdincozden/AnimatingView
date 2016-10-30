package five.android.pro.ch104;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import android.view.Menu;
import android.view.MenuItem;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private boolean mIsHeads;
    private AnimatorSet mFlipper;
    private Bitmap mHeadSImage, mTailsImage;
    private ImageView mFlipImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHeadSImage = BitmapFactory.decodeResource(getResources(), R.drawable.heads);
        mTailsImage = BitmapFactory.decodeResource(getResources(), R.drawable.tails);

        mFlipImage=(ImageView)findViewById(R.id.flip_image);
        mFlipImage.setImageBitmap(mHeadSImage);
        mIsHeads=true;

        mFlipper = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip);
        mFlipper.setTarget(mFlipImage);

        ObjectAnimator flipAnimator=(ObjectAnimator)mFlipper.getChildAnimations().get(0);


        flipAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if(valueAnimator.getAnimatedFraction()>=0.25f&&mIsHeads){
                    mFlipImage.setImageBitmap(mTailsImage);
                    mIsHeads=false;
                }
                if (valueAnimator.getAnimatedFraction()>=0.75f&&mIsHeads){
                    mFlipImage.setImageBitmap(mHeadSImage);
                    mIsHeads=true;
                }
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            mFlipper.start();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}






