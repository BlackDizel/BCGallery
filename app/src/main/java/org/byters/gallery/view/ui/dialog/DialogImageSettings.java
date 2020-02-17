package org.byters.gallery.view.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import org.byters.api.view.ui.dialog.listener.IDialogImageSettingsListener;
import org.byters.gallery.R;

import java.lang.ref.WeakReference;

public class DialogImageSettings implements View.OnClickListener {

    private final WeakReference<IDialogImageSettingsListener> refListener;
    private Dialog dialog;

    public DialogImageSettings(Context context, IDialogImageSettingsListener listenerDialogSettings) {

        this.refListener = new WeakReference<>(listenerDialogSettings);

        dialog = new Dialog(context, R.style.dialog_transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = LayoutInflater.from(context).inflate(R.layout.view_image_settings, null);
        dialog.setContentView(view);

        dialog.findViewById(R.id.tvCrop).setOnClickListener(this);
        dialog.findViewById(R.id.tvRotate).setOnClickListener(this);
        dialog.findViewById(R.id.tvDelete).setOnClickListener(this);
        dialog.findViewById(R.id.tvShare).setOnClickListener(this);
        dialog.findViewById(R.id.tvEdit).setOnClickListener(this);

        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void cancel() {
        if (dialog == null) return;
        dialog.cancel();
    }

    public void show() {
        if (dialog == null) return;
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (refListener == null || refListener.get() == null) return;

        if (v.getId() == R.id.tvCrop)
            refListener.get().onClickCrop();
        if (v.getId() == R.id.tvDelete)
            refListener.get().onClickDelete();
        if (v.getId() == R.id.tvShare)
            refListener.get().onClickShare();
        if (v.getId() == R.id.tvEdit)
            refListener.get().onClickEdit();
        if (v.getId() == R.id.tvRotate)
            refListener.get().onClickRotate();
    }
}
