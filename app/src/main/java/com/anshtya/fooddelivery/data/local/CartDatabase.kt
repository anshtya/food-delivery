package com.anshtya.fooddelivery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [CartEntity::class],
    version = 2,
    exportSchema = true
)
abstract class CartDatabase: RoomDatabase() {
    abstract fun cartDao(): CartDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE food_cart ADD COLUMN foodId INTEGER NOT NULL")
            }
        }
    }
}