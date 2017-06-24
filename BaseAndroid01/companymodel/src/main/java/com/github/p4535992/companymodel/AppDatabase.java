package com.github.p4535992.companymodel;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Implement data of database + SQLiteOpenHelper implementation
 */
@Database(name = "AppDatabase", version = 1, insertConflict = ConflictAction.IGNORE, updateConflict= ConflictAction.REPLACE,consistencyCheckEnabled = true)
public class AppDatabase {

    /**
     * The Tag for the Log
     */
    private static final String TAG = AppDatabase.class.getSimpleName();


    public static final String NAME = "AppDatabase"; // we will add the .db extension

    public static final int VERSION = 1;

    /**
     * The name of the DB
     */
    public static final String DB_NAME = NAME;

    /**
     * The current version of the DB
     */
    public static final int DB_VERSION = 1;

    /**
     * The Authority for the ContentProvider, in most cases is the directory package of the current gradle module
     */
    public static final String AUTHORITY = "com.github.p4535992.companymodel";

    /**
     * The Uri for this resources
     */
    public static final Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY + "/" );

    /**
     * Utility class for manage the table CompanyModels with the objects CompanyModels
     */
    public interface CompanyModelsTable extends BaseColumns {

        /**
         * The Path for this kind of resource
         */
        String PATH = "company_model";

        /**
         * The Uri for this kind of resource
         */
        Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY + "/" + PATH);

        /**
         * The mime type for the dir
         */
        String MIME_TYPE_DIR = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd." + PATH;

        /**
         * The mime type for the single item
         */
        String MIME_TYPE_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd." + PATH;

        /**
         * The name of the table
         */
        String TABLE_NAME = "company_models";
        /**
         * The names of the columns
         */
        String ID = "id";
        String NAME = "name";
        String ADDRESS = "address";
        String LOCATIONID = "locationId";
    }

    /*
    public static class DbHelper extends FlowSQLiteOpenHelper{

        public DbHelper(DatabaseDefinition databaseDefinition, DatabaseHelperListener listener) {
            super(databaseDefinition, listener);
        }

        //[OPTIONAL] if for some reason need to use the class SQLiteOpenHelper

        private final Context mContext;
        public DbHelper(Context context) {
            super(context, DB_NAME, new CompanyModelCursorFactory(), DB_VERSION);
            mContext = context;
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.beginTransaction();
                final String createSQL = "<SQL TO CREATE TABLE>";
                db.execSQL(createSQL);
                db.setTransactionSuccessful();
            } catch (Exception ioe) {
                Log.e(TAG, "Error reading create SQL", ioe);
            } finally {
                db.endTransaction();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.beginTransaction();
                final String dropSQL = "<SQL DROP TABLE>";
                db.execSQL(dropSQL);
                db.setTransactionSuccessful();
            } catch (Exception ioe) {
                Log.e(TAG, "Error reading create SQL", ioe);
            } finally {
                db.endTransaction();
            }
            onCreate(db);
        }

    }
    */


}
