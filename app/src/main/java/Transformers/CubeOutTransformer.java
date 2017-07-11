package Transformers;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Sahil Jain on 09-07-2017.
 */

public class CubeOutTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {

        page.setPivotX(position < 0f ? page.getWidth() : 0f);
        page.setPivotY(page.getHeight() * 0.5f);
        page.setRotationY(90f * position);

    }

}//CubeOutTransformer Class
