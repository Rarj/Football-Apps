package dev.grack.matchschedulefootbal.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: DbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DbHelper {
            if (instance == null) {
                instance = DbHelper(ctx.applicationContext)
            }

            return instance as DbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.ID_EVENT to TEXT,
                Favorite.TEAM_ID_HOME to TEXT,
                Favorite.TEAM_ID_AWAY to TEXT,
                Favorite.TEAM_NAME_HOME to TEXT,
                Favorite.TEAM_NAME_AWAY to TEXT,
                Favorite.TIME_MATCH to TEXT,
                Favorite.TEAM_SCORE_HOME to TEXT,
                Favorite.TEAM_SCORE_AWAY to TEXT,
                Favorite.TEAM_DATE_EVENT to TEXT)

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
        db.dropIndex(Favorite.TABLE_FAVORITE, true)
        db.dropIndex(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}

val Context.database: DbHelper
    get() = DbHelper.getInstance(applicationContext)