package inc.slartibartfast.codechallange_telstra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import inc.slartibartfast.codechallange_telstra.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private val recyclerAdapter = TileAdapter()
    private lateinit var viewMode: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initSwipeContainer()
        initRecyclerView()
    }

    private fun initViewModel() {
        viewMode = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewMode.liveCountryData.observe(this) {
            title = it.title
            recyclerAdapter.setTileItems(it.rows)
            recyclerAdapter.notifyDataSetChanged()
        }
    }

    private fun initSwipeContainer() {
        findViewById<SwipeRefreshLayout>(R.id.swipeContainer).apply {

            val failTest = true
            viewMode.makeApiCall(this, failTest)

            setOnRefreshListener {
                viewMode.makeApiCall(this, !failTest)
            }
        }
    }

    private fun initRecyclerView() {
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recyclerAdapter
        }
    }
}