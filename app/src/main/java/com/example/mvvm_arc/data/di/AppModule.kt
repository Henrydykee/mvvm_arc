package com.example.mvvm_arc.data.di

import android.content.Context
import androidx.room.Room
import com.example.mvvm_arc.data.database.NoteDatabase
import com.example.mvvm_arc.data.repository.NoteRepositoryImpl
import com.example.mvvm_arc.domain.repositiory.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,  // Fixed: Added .java for class reference
            NoteDatabase.name          // Fixed: Use actual database name string
        ).build()

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository =
        NoteRepositoryImpl(dao = db.dao)
}