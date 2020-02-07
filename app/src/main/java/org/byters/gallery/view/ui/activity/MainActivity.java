package org.byters.gallery.view.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import org.byters.api.view.INavigator;
import org.byters.gallery.BuildConfig;
import org.byters.gallery.GalleryApplication;
import org.byters.gallery.R;

public class MainActivity extends Activity {

    private static final int REQUEST_CODE_READ_STORAGE = 23;
    public INavigator navigator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GalleryApplication.getInjector().inject(this);
        setContentView(R.layout.activity_main);

        navigator.set(this, getFragmentManager(), R.id.flContent);

        checkNavigate();
    }

    private void checkNavigate() {
        requestPermissions();
    }

    private void requestPermissions() {
        if (getPackageManager().checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, BuildConfig.APPLICATION_ID)
                != PackageManager.PERMISSION_GRANTED
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_READ_STORAGE);

        } else openContent();
    }

    private void openContent() {
        if (getIntent() != null && getIntent().getData() != null)
            navigator.navigateImage(getIntent().getData(), false);
        else
            navigator.navigateFolderList();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_READ_STORAGE
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            openContent();
        else navigator.navigateError();
    }
}
