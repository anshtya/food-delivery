package com.anshtya.fooddelivery.di

import android.content.Context
import androidx.room.Room
import com.anshtya.fooddelivery.data.local.CartDao
import com.anshtya.fooddelivery.data.local.CartDatabase
import com.anshtya.fooddelivery.data.local.CartDatabase.Companion.MIGRATION_1_2
import com.anshtya.fooddelivery.data.repository.CartRepository
import com.anshtya.fooddelivery.data.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCartDatabase(@ApplicationContext app: Context): CartDatabase {
        return Room
            .databaseBuilder(app, CartDatabase::class.java, "cart.db")
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Singleton
    @Provides
    fun provideCartDao(db: CartDatabase): CartDao {
        return db.cartDao()
    }

    @Singleton
    @Provides
    fun provideFoodRepository(): FoodRepository {
        return FoodRepository()
    }

    @Singleton
    @Provides
    fun provideCartRepository(cartDao: CartDao): CartRepository {
        return CartRepository(cartDao)
    }
}