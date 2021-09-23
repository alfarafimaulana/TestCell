package layout

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.testcell.R


class ViewPagerAdapterIklanVoc(private var images: List<Int>) : RecyclerView.Adapter<ViewPagerAdapterIklanVoc.pager2ViewHolder>() {

    inner class pager2ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val itemImage : ImageView = itemView.findViewById(R.id.gambarFun)



        init {
            itemImage.setOnClickListener {
                val gmmIntentUri = Uri.parse("geo:0,0?q=Burger")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                itemView.context.startActivity(mapIntent)

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapterIklanVoc.pager2ViewHolder {
        return pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itempage_iklan, parent, false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapterIklanVoc.pager2ViewHolder, position: Int) {

        holder.itemImage.setImageResource(images[position])

    }

    override fun getItemCount(): Int {
        return images.size
    }
}