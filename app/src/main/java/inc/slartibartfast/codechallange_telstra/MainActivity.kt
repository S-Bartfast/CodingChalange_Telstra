package inc.slartibartfast.codechallange_telstra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import inc.slartibartfast.codechallange_telstra.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private val recyclerAdapter = TileAdapter()
    private lateinit var viewMode: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recyclerAdapter
        }
    }

    private fun initViewModel() {
        viewMode = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewMode.liveCountryData.observe(this) {
            title = it.title
            recyclerAdapter.tileList = it.rows
            recyclerAdapter.notifyDataSetChanged()
        }

        viewMode.makeApiCall()
    }
}