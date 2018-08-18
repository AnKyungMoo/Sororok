package com.nexters.sororok.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.nexters.sororok.R;

//import de.hdodenhof.circleimageview.CircleImageView;

public class AvatarImageBehavior extends CoordinatorLayout.Behavior<ImageView> {

    private final Context mContext;

    private int mStartChildXPosition;
    private int mEndChildXPosition;

    private int mStartDependencyYPosition;
    private int mEndDependencyYPosition;

    private int mStartChildHeight;
    private int mEndChildHeight;

    private float mStartToolBarPosition;

    public AvatarImageBehavior(Context context, AttributeSet attrs) {
        this.mContext = context;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {

        initProperties(child, dependency);

        final int maxScrollDistance = (int) (mStartToolBarPosition - getStatusBarHeight());
        float expandedPercentageFactor = dependency.getY() / maxScrollDistance;

        float distanceYToSubtract = ((mStartDependencyYPosition - mEndDependencyYPosition)
                * (1f - expandedPercentageFactor)) + (child.getHeight() / 2);

        float distanceXToSubtract = ((mStartChildXPosition - mEndChildXPosition)
                * (1f - expandedPercentageFactor)) + (child.getWidth() / 2);

        float heightToSubtract = ((mStartChildHeight - mEndChildHeight) * (1f - expandedPercentageFactor));

        child.setY(mStartDependencyYPosition - distanceYToSubtract);
        child.setX(mStartChildXPosition - distanceXToSubtract);

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();

        lp.width = (int) (mStartChildHeight - heightToSubtract);
        lp.height = (int) (mStartChildHeight - heightToSubtract);

        child.setLayoutParams(lp);

        return true;
    }

    private void initProperties(ImageView child, View dependency) {

        if (mStartChildHeight == 0)
            mStartChildHeight = child.getHeight();

        if (mEndChildHeight == 0)
            mEndChildHeight = mContext.getResources().getDimensionPixelOffset(R.dimen.spacing_small);

        if (mStartChildXPosition == 0)
            mStartChildXPosition = (int) (child.getX() + (child.getWidth() / 2));

        if (mEndChildXPosition == 0)
            mEndChildXPosition = mContext.getResources().getDimensionPixelOffset(R.dimen.abc_action_bar_content_inset_material) + (mEndChildHeight / 2);

        if (mStartDependencyYPosition == 0)
            mStartDependencyYPosition = (int) (dependency.getY());

        if (mEndDependencyYPosition == 0)
            mEndDependencyYPosition = (dependency.getHeight() / 2);

        if (mStartToolBarPosition == 0)
            mStartToolBarPosition = dependency.getY() + (dependency.getHeight() / 2);
    }

    public int getStatusBarHeight() {

        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}

