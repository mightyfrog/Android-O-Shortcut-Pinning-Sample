package org.mightyfrog.android.shortcutpinningsample

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * https://developer.android.com/reference/android/content/pm/ShortcutManager.html
 *
 * @author Shigehiro Soejima
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            pinShortcut()
        }
    }

    private fun pinShortcut() {
        val manager = getSystemService(ShortcutManager::class.java)
        if (manager != null && manager.isRequestPinShortcutSupported) {
            val intent = Intent(Intent.ACTION_VIEW) // action required
            val info = ShortcutInfo.Builder(this, createId())
                    .setIcon(Icon.createWithResource(this, R.drawable.ic_beach_access))
                    .setShortLabel(getString(R.string.label))
                    .setIntent(intent)
                    .build()
            manager.requestPinShortcut(info, PendingIntent.getActivity(this, 1, Intent(this, MainActivity::class.java), PendingIntent.FLAG_ONE_SHOT).intentSender)
        }
    }

    private fun createId(): String {
        return "" + System.currentTimeMillis()
    }
}
