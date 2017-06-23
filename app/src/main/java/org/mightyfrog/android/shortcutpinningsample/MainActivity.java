package org.mightyfrog.android.shortcutpinningsample;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * https://developer.android.com/reference/android/content/pm/ShortcutManager.html
 *
 * @author Shigehiro Soejima
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinShortcut();
            }
        });
    }

    @TargetApi(26)
    private void pinShortcut() {
        ShortcutManager manager = getSystemService(ShortcutManager.class);
        if (manager.isRequestPinShortcutSupported()) {
            Intent intent = new Intent(Intent.ACTION_VIEW); // action required
            ShortcutInfo info = new ShortcutInfo.Builder(this, createId())
                    .setIcon(Icon.createWithResource(this, R.drawable.ic_beach_access))
                    .setShortLabel(getString(R.string.label))
                    .setIntent(intent)
                    .build();

            manager.requestPinShortcut(info, PendingIntent.getActivity(this, 1, new Intent(this, MainActivity.class), PendingIntent.FLAG_ONE_SHOT).getIntentSender());
        }
    }

    private String createId() {
        return "" + System.currentTimeMillis();
    }
}
