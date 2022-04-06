package inc.slartibartfast.codechallange_telstra

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import inc.slartibartfast.codechallange_telstra.databinding.TileBinding

class TileAdapter : RecyclerView.Adapter<TileAdapter.TileViewHolder>() {
    var tileList: List<TileData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileViewHolder {
        val binding = TileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        holder.bind(tileList?.get(position))
    }

    override fun getItemCount() = tileList?.size ?: 0

    class TileViewHolder(private val binding: TileBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TileData?) {
            binding.model = data

            val imageView = binding.image

            Glide
                .with(binding.root.context)
                .load(data?.imageHref?.replace("http://", "https://"))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView)

            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context, "${data?.title} clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}