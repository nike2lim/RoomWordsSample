package co.kr.shlim.roomwordssample

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao() : WordDao

    object DatabaseProvider {
        val DB_NAME = "word_database"
        private var INSTANCE : WordRoomDatabase? = null

        fun getDataBase(context : Context) : WordRoomDatabase? {
            if(null == INSTANCE) {
                INSTANCE  = Room.databaseBuilder(context.applicationContext , WordRoomDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabase)
                    .build()
            }
            return INSTANCE
        }

        val sRoomDatabase : RoomDatabase.Callback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE?.wordDao()).execute()
            }
        }

        class PopulateDbAsync(val mDao : WordDao?) : AsyncTask<Unit, Unit, Unit>() {

            override fun doInBackground(vararg p0: Unit?) {
                mDao?.deleteAll()
                var word : Word = Word("Hello")
                mDao?.insert(word)
                word  = Word("World")
                mDao?.insert(word)
            }
        }
    }
}