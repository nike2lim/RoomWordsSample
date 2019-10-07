package co.kr.shlim.roomwordssample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class WordListAdapter(val context : Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder> ()  {


    var mWords : List<Word>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    fun setWords(words : List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if(null != mWords) {
            return mWords!!.size
        }else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val currentWord = mWords?.get(position)

        currentWord?.word?.let {
            holder.wordItemView.setText(it)
        }?:let {
            holder.wordItemView.setText("No Word")
        }
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView = itemView.textView
    }

}