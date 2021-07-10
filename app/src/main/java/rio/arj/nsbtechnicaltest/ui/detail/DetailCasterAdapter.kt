package rio.arj.nsbtechnicaltest.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rio.arj.nsbtechnicaltest.data.repository.popular.model.Credit
import rio.arj.nsbtechnicaltest.data.repository.popular.model.CreditResponse
import rio.arj.nsbtechnicaltest.databinding.ItemCastBinding
import rio.arj.nsbtechnicaltest.helper.loadImage

class DetailCasterAdapter(
  private val casterList: CreditResponse
) : RecyclerView.Adapter<DetailCasterAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val viewDataBinding = ItemCastBinding.inflate(inflater, parent, false)
    return ViewHolder(viewDataBinding)
  }

  override fun getItemCount(): Int {
    return casterList.cast.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(casterList.cast[position])
  }

  class ViewHolder(private val binding: ItemCastBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Credit) {
      binding.imageProfile.loadImage(model.profilePath)
      binding.textCasterName.text = model.name
    }
  }

}