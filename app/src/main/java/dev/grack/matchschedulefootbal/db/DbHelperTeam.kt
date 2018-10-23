package dev.grack.matchschedulefootbal.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DbHelperTeam(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteDetailTeam.db", null, 1) {
    companion object {
        private var instance: DbHelperTeam? = null

        @Synchronized
        fun getInstance(ctx: Context): DbHelperTeam {
            if (instance == null) {
                instance = DbHelperTeam(ctx.applicationContext)
            }

            return instance as DbHelperTeam
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.ID_TEAM to TEXT,
                FavoriteTeam.STR_TEAM to TEXT,
                FavoriteTeam.STR_TEAM_BADGE to TEXT,
                FavoriteTeam.STR_FORMED_YEAR to TEXT,
                FavoriteTeam.STR_STADIUM to TEXT,
                FavoriteTeam.STR_DESCRIPTION_EN to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropIndex(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}

val Context.databaseTeam: DbHelperTeam
    get() = DbHelperTeam.getInstance(applicationContext)