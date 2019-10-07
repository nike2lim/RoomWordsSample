package co.kr.shlim.roomwordssample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    lateinit var mAdapter : WordListAdapter
    lateinit var mWordViewModel : WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.apply {
            mAdapter = WordListAdapter(context)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }

        mWordViewModel = ViewModelProviders.of(this)[WordViewModel::class.java]

        mWordViewModel.getAllWords().observe(this, object : Observer<List<Word>> {
            override fun onChanged(words : List<Word>) {
                mAdapter.setWords(words)
            }
        })

        fab.setOnClickListener({
            Intent(this@MainActivity, NewWordActivity::class.java).let {
                startActivityForResult(it, NEW_WORD_ACTIVITY_REQUEST_CODE)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val word = data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { Word(it) }
            mWordViewModel.insert(word)
        }else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }

}
