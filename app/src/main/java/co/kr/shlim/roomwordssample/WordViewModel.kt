package co.kr.shlim.roomwordssample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class WordViewModel(application: Application) : AndroidViewModel(application) {


    val mRepository : WordRepository
    val mAllWords : LiveData<List<Word>>?

    init {
        mRepository = WordRepository(getApplication())
        mAllWords = mRepository?.getAllWords()
    }

    fun getAllWords() : LiveData<List<Word>> {
        return mAllWords!!
    }

    fun insert(word : Word?) {
        word?.let {
            mRepository.insert(it)
        }
    }
}