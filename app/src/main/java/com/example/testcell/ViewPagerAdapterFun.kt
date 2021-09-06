package layout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testcell.R


class ViewPagerAdapterFun(private var Pilihan1: List<Int>, private var Pilihan2: List<Int>) : RecyclerView.Adapter<ViewPagerAdapterFun.pager2ViewHolder>() {

    inner class pager2ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val pil1 : ImageView = itemView.findViewById(R.id.pil1)
        val pil2 : ImageView = itemView.findViewById(R.id.pil2)



        init {
            pil1.setOnClickListener {
                val position = adapterPosition
                Toast.makeText(itemView.context, "${position+1} pertama", Toast.LENGTH_SHORT).show()
            }
            pil2.setOnClickListener {
                val position = adapterPosition
                Toast.makeText(itemView.context, "${position+1} kedua", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapterFun.pager2ViewHolder {
        return pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itempage_pilihan, parent, false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapterFun.pager2ViewHolder, position: Int) {
        holder.pil1.setImageResource(Pilihan1[position])
        holder.pil2.setImageResource(Pilihan2[position])
    }

    override fun getItemCount(): Int {
        return Pilihan1.size
    }
}