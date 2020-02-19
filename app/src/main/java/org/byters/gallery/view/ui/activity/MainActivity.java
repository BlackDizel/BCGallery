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

    private static final int REQUEST_CODE_STORAGE = 23;
    private static final CharSequence TYPE_IMAGE = "image";
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
        if (!allPermissionsGranted() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_STORAGE);

        } else openContent();
    }

    private boolean allPermissionsGranted() {
        return getPackageManager().checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, BuildConfig.APPLICATION_ID)
                == PackageManager.PERMISSION_GRANTED
                && getPackageManager().checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, BuildConfig.APPLICATION_ID)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void openContent() {
        if (getIntent() != null && getIntent().getData() != null) {
            if (getIntent().getType() != null
                    && getIntent().getType().contains(TYPE_IMAGE))
                navigator.navigateImage(getIntent().getData(), false);
        } else
            navigator.navigateFolderList();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_STORAGE
                && grantResults.length == 2
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            openContent();
        else navigator.navigateError();
    }
}
