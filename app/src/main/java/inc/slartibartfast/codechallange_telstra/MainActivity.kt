package inc.slartibartfast.codechallange_telstra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import inc.slartibartfast.codechallange_telstra.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewMode: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewMode = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewMode.makeApiCall()

        viewMode.liveCountryData.observe(this) { countryData ->
            val titles = StringBuilder()

            title = countryData.title
            countryData.rows.forEach {
                titles.appendLine(it.title)
            }

            val textView = findViewById<TextView>(R.id.the_text_view)
            textView.text = titles
        }
    }
}