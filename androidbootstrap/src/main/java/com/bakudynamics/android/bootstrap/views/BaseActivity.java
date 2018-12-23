package com.bakudynamics.android.bootstrap.views;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bakudynamics.android.bootstrap.App;
import com.bakudynamics.android.bootstrap.views.binders.ActivityViewBinder;


public class BaseActivity extends AppCompatActivity implements ActivityViewBinder, Extractor {


    private boolean finishOnHomeClick = false;

    public App getApp() {
        return (App) getApplication();
    }

    public String TAG = getClass().getCanonicalName();


    public int getColour(@ColorRes int resid) {
        return ContextCompat.getColor(this, resid);
    }

    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void showHomeButton() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void showHomeButton(boolean finishOnHomeClick) {
        this.finishOnHomeClick = finishOnHomeClick;
        showHomeButton();
    }

    protected void removeToolbarText() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    protected void setToolbarText(String text) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(text);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home && finishOnHomeClick)
            finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Activity getContext() {
        return this;
    }
}
