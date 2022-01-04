package com.java.demonotes.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.java.demonotes.framework.db.DatabaseService
import com.java.demonotes.framework.db.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(
        @ApplicationContext context: Context):DatabaseService {

        return Room.databaseBuilder(context,DatabaseService::class.java,DatabaseService.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesNoteDao(databaseService: DatabaseService):NoteDao{
        return databaseService.noteDao()
    }

}