package layout

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testcell.MainActivity
import com.example.testcell.R
import com.example.testcell.profileUser
import com.example.testcell.voiceOfCustomer


class ViewPagerAdapter(private var title : List<String>,private var images : List<Int>) : RecyclerView.Adapter<ViewPagerAdapter.pager2ViewHolder>() {

    inner class pager2ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val itemTitle : TextView = itemView.findViewById(R.id.namaFungsiDiViewpager)
        val itemImage : ImageView = itemView.findViewById(R.id.gambarFungsiDiViewpager)

        init {
            itemImage.setOnClickListener {
                val position = adapterPosition
                Toast.makeText(itemView.context, "${position+1}", Toast.LENGTH_SHORT).show()
                if (position == 0){
                    val dashboardIntent = Intent(itemView.context, MainActivity::class.java)
                    itemView.context.startActivity(dashboardIntent)
                }
                if (position == 1){
                    val dashboardIntent = Intent(itemView.context, voiceOfCustomer::class.java)
                    itemView.context.startActivity(dashboardIntent)
                }
                if (position == 2){
                    val dashboardIntent = Intent(itemView.context, profileUser::class.java)
                    itemView.context.startActivity(dashboardIntent)
                }
            }

        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.pager2ViewHolder {
        return pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itempage_fun_select, parent, false))


    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.pager2ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemImage.setImageResource(images[position])

    }

    override fun getItemCount(): Int {
        return title.size
    }
}