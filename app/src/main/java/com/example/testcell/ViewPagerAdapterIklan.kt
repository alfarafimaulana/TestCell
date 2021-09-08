package layout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testcell.R


class ViewPagerAdapterIklan( private var images: List<Int>) : RecyclerView.Adapter<ViewPagerAdapterIklan.pager2ViewHolder>() {

    inner class pager2ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val itemImage : ImageView = itemView.findViewById(R.id.gambarFun)



        init {
            itemImage.setOnClickListener {
                val position = adapterPosition
                Toast.makeText(itemView.context, "${position+1}", Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapterIklan.pager2ViewHolder {
        return pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itempage_iklan, parent, false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapterIklan.pager2ViewHolder, position: Int) {

        holder.itemImage.setImageResource(images[position])

    }

    override fun getItemCount(): Int {
        return images.size
    }
}