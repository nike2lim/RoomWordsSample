package co.kr.shlim.roomwordssample

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class WordRepository(application : Application) {
    val mWordDao : WordDao?
    val mAllWords : LiveData<List<Word>>?

    init {
        val db = WordRoomDatabase.DatabaseProvider.getDataBase(application)
        mWordDao = db?.wordDao()
        mAllWords = mWordDao?.getAllWords()
    }

    fun getAllWords() : LiveData<List<Word>>? {
        return mAllWords
    }

    fun insert(word : Word) {
        insertAsyncTask(mWordDao!!).execute(word)
    }

    class insertAsyncTask(val dao : WordDao) : AsyncTask<Word, Unit, Unit>() {

        override fun doInBackground(vararg p0: Word) {
            dao.insert(p0[0])
        }
    }
}