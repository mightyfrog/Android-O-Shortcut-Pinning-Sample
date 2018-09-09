package org.mightyfrog.android.shortcutpinningsample

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

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

        textView.text = intent?.getStringExtra("text")
    }

    private fun pinShortcut() {
        getSystemService(ShortcutManager::class.java)?.also { manager ->
            if (manager.isRequestPinShortcutSupported) {
                val intent = Intent(this, MainActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    putExtra("text", "Hello Shortcut!")
                }
                val info = ShortcutInfo.Builder(this, createId())
                        .setIcon(Icon.createWithResource(this, R.drawable.ic_beach_access))
                        .setShortLabel(getString(R.string.label))
                        .setIntent(intent)
                        .build()
                manager.requestPinShortcut(info, null)
            }
        }
    }

    private fun createId() = System.currentTimeMillis().toString()
}
