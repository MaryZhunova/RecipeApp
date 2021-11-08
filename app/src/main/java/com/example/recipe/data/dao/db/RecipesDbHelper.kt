package com.example.recipe.data.dao.db

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.recipe.data.dao.RecipesDbContract
import javax.inject.Inject
import javax.inject.Named

class RecipesDbHelper @Inject constructor(@Named("context") context: Context) :
    SQLiteOpenHelper(context, RecipesDbContract.DB_FILE_NAME, null, RecipesDbContract.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_RECIPES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE_RECIPES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        private const val CREATE_TABLE_RECIPES =
            "CREATE TABLE IF NOT EXISTS " + RecipesDbContract.RecipesEntry.TABLE_NAME_RECIPES +
                    "(" +
                    BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RecipesDbContract.RecipesEntry.URI + " TEXT NOT NULL UNIQUE," +
                    RecipesDbContract.RecipesEntry.LABEL + " TEXT," +
                    RecipesDbContract.RecipesEntry.IMAGE + " TEXT," +
                    RecipesDbContract.RecipesEntry.SOURCE + " TEXT," +
                    RecipesDbContract.RecipesEntry.URL + " TEXT" +
//                    RecipesDbContract.RecipesEntry.INGREDIENT_LINES + " TEXT," +
//                    RecipesDbContract.RecipesEntry.DIET_LABELS + " TEXT," +
//                    RecipesDbContract.RecipesEntry.HEALTH_LABELS + " TEXT," +
//                    RecipesDbContract.RecipesEntry.CUISINE_TYPE + " TEXT," +
//                    RecipesDbContract.RecipesEntry.MEAL_TYPE + " TEXT," +
                    /*RecipesDbContract.RecipesEntry.DISH_TYPE  + " TEXT" + */")"

//        private const val CREATE_TABLE_INGREDIENT_LINES =
//            "CREATE TABLE IF NOT EXISTS " + RecipesDbContract.IngredientLinesEntry.TABLE_NAME_INGREDIENT_LINES +
//                    "(" +
//                    BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    RecipesDbContract.IngredientLinesEntry.INGREDIENT + " TEXT," +
//                    " FOREIGN KEY ("+RecipesDbContract.IngredientLinesEntry.INGREDIENT_LINES+") REFERENCES "+RecipesDbContract.RecipesEntry.TABLE_NAME_RECIPES+"("+RecipesDbContract.RecipesEntry.INGREDIENT_LINES+"))"

        private const val DROP_TABLE_RECIPES =
            "DROP TABLE IF EXISTS " + RecipesDbContract.RecipesEntry.TABLE_NAME_RECIPES
    }
}

//private val INIT_TABLE_BOOKS = "INSERT INTO " + BooksEntry.TABLE_NAME.toString() +
//        "(" + BooksEntry.ISBN.toString() + ", " + BooksEntry.TITLE.toString() + ", " + BooksEntry.AUTHOR.toString() + ", " + BooksEntry.SHELF_NUMBER.toString() + ") " +
//        "VALUES " +
//        "(\"978-1260440225\", \"Java: A Beginner's Guide\", \"Herbert Schildt\", 1), " +
//        "(\"978-0134685991\", \"Effective Java\", \"Joshua Bloch\", 1), " +
//        "(\"978-0132350884\", \"Clean Code\", \"Robert C. Martin\", 2), " +
//        "(\"978-1590596739\", \"The Definitive Guide to SQLite\", \"Mike Owens\", 3), " +
//        "(\"978-0321349606\", \"Java Concurrency in Practice\", \"Brian Goetz\", 1)"