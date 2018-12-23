package com.bakudynamics.android.bootstrap.factories;

import android.os.Bundle;
import com.bakudynamics.android.bootstrap.views.BaseFragment;

public class FragmentFactory extends Factory {

    protected static final String ARG_LAYOUT_RES_ID = "layoutResId";
    protected static final String ARG_TYPE = "type";

    public static <T extends BaseFragment> T newInstanceOf(Class<T> fragmentClass, int resId) {
        T t;
        try {
            t = fragmentClass.newInstance();
            Bundle args = new Bundle();
            args.putInt(ARG_LAYOUT_RES_ID, resId);
            t.setArguments(args);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) new BaseFragment().withView(resId);
    }

}
