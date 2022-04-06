package inc.slartibartfast.codechallange_telstra

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TileAdapter : RecyclerView.Adapter<TileAdapter.TileViewHolder>() {
    var tileList: List<TileData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TileViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tile, parent, false))

    override fun onBindViewHolder(holder: TileAdapter.TileViewHolder, position: Int) {
        holder.bind(tileList?.get(position))
    }

    override fun getItemCount() = tileList?.size ?: 0

    class TileViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)

        fun bind(data: TileData?) {
            title.text = data?.title
            description.text = data?.description
        }
    }
}