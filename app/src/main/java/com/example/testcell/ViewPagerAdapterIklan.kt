package layout

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.testcell.R
import com.example.testcell.TestAct


class ViewPagerAdapterIklan( private var images: List<Int>) : RecyclerView.Adapter<ViewPagerAdapterIklan.pager2ViewHolder>() {

    inner class pager2ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val itemImage : ImageView = itemView.findViewById(R.id.gambarFun)



        init {
            itemImage.setOnClickListener {
                val position = adapterPosition
                if(position == 0){
                    val openURL = Intent(android.content.Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://www.inews.id/techno/telco/cara-mengaktifkan-paket-belajar-telkomsel")
                    itemView.context.startActivity(openURL)
                }
                if(position == 1){
                    val openURL = Intent(android.content.Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://www.kompas.com/tren/read/2021/09/25/103000465/telkom-siap-ganti-rugi-akibat-gangguan-internet-indihome")
                    itemView.context.startActivity(openURL)
                }


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