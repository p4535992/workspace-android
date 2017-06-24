package com.github.p4535992.testdbflowbug;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.AndroidDatabase;

import java.util.ArrayList;

/**
 * ContentProvider implementation for CompanyModel application
 */
public class CompanyModelContentProvider extends ContentProvider {

  /**
   * The UriMatcher for the Uri recognition
   */
  private final static UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
  private final static int STOP_DIR_INDICATOR = 1;
  private final static int STOP_ITEM_INDICATOR = 2;
  private final static int STOP_SEARCH = 3;

  static {
    URI_MATCHER.addURI(AppDatabase.AUTHORITY, AppDatabase.CompanyModelsTable.PATH, STOP_DIR_INDICATOR);
    URI_MATCHER.addURI(AppDatabase.AUTHORITY, AppDatabase.CompanyModelsTable.PATH + "/#", STOP_ITEM_INDICATOR);
    URI_MATCHER.addURI(AppDatabase.AUTHORITY, AppDatabase.CompanyModelsTable.PATH + "/"
            + SearchManager.SUGGEST_URI_PATH_QUERY, STOP_SEARCH);
  }

  /**
   * The SQLiteOpenHelper implementation
   */
  //private AppDatabase mDb;
  private SQLiteDatabase mDb;

  @Override
  public boolean onCreate() {
    //mDb = new AppDatabase(getContext());
    mDb = ((AndroidDatabase) FlowManager.getDatabase(AppDatabase.DB_NAME).getWritableDatabase()).getDatabase();
    return true;
  }

  @Nullable
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    // We get the code from the URI_MATCHER
    final int uriMatcherCode = URI_MATCHER.match(uri);
    Cursor cursor = null;
    String itemId = null;
    StringBuilder whereClause = null;
    //SQLiteDatabase db = mDb.getReadableDatabase();
    SQLiteDatabase db = mDb;
    switch (uriMatcherCode) {
      case STOP_ITEM_INDICATOR:
        itemId = uri.getPathSegments().get(1);
        whereClause = new StringBuilder(AppDatabase.CompanyModelsTable.ID).append(" = ").append(itemId);
        if (selection != null) {
          whereClause.append(" AND (").append(selection).append(" ) ");
        }
        cursor = db.query(AppDatabase.CompanyModelsTable.TABLE_NAME, null, whereClause.toString(), selectionArgs,
                null, null, null);
        break;
      case STOP_DIR_INDICATOR:
        cursor = db.query(AppDatabase.CompanyModelsTable.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        break;
      case STOP_SEARCH:
        selectionArgs[0] = "%" + selectionArgs[0] + "%";
        final String[] searchProjection = new String[]{AppDatabase.CompanyModelsTable._ID, AppDatabase.CompanyModelsTable.NAME + " as " + SearchManager.SUGGEST_COLUMN_TEXT_1};
        cursor = db.query(AppDatabase.CompanyModelsTable.TABLE_NAME, searchProjection, selection, selectionArgs, null, null, null);
        break;
    }
    // In this case the notify the cursor listener of the reading. It's useful to notify the Adapter
    // that can update their data
    if (cursor != null) {
      cursor.setNotificationUri(getContext().getContentResolver(), AppDatabase.CompanyModelsTable.CONTENT_URI);
    }
    return cursor;
  }

  @Nullable
  @Override
  public String getType(Uri uri) {
    switch (URI_MATCHER.match(uri)) {
      case STOP_DIR_INDICATOR:
        return AppDatabase.CompanyModelsTable.MIME_TYPE_DIR;
      case STOP_ITEM_INDICATOR:
        return AppDatabase.CompanyModelsTable.MIME_TYPE_ITEM;
      default:
        throw new IllegalArgumentException("The Uri " + uri + " is unknown for this ContentProvider");
    }
  }

  @Nullable
  @Override
  public Uri insert(Uri uri, ContentValues values) {
    if (URI_MATCHER.match(uri) == STOP_DIR_INDICATOR) {
      //SQLiteDatabase db = mDb.getWritableDatabase();
        SQLiteDatabase db = mDb;
      //long newTeamId = db.insert(ApoBusDB.BusStop.TABLE_NAME, ApoBusDB.BusStop.INDICATOR, values);
      long newTeamId = db.insertWithOnConflict(AppDatabase.CompanyModelsTable.TABLE_NAME, AppDatabase.CompanyModelsTable.ADDRESS,
              values, SQLiteDatabase.CONFLICT_REPLACE);
      if (newTeamId > 0) {
        Uri newTeamUri = ContentUris.withAppendedId(AppDatabase.CompanyModelsTable.CONTENT_URI, newTeamId);
        getContext().getContentResolver().notifyChange(newTeamUri, null);
        return newTeamUri;
      }
    } else {
      throw new IllegalArgumentException("The Uri " + uri + " is unknown for this ContentProvider");
    }
    return null;
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    String itemId = null;
    StringBuilder whereClause = null;
    //SQLiteDatabase db = mDb.getWritableDatabase();
    SQLiteDatabase db = mDb;
    int deleteNumber = 0;
    switch (URI_MATCHER.match(uri)) {
      case STOP_ITEM_INDICATOR:
        itemId = uri.getPathSegments().get(1);
        whereClause = new StringBuilder(AppDatabase.CompanyModelsTable.ID).append(" = ").append(itemId);
        if (selection != null) {
          whereClause.append(" AND (").append(selection).append(" ) ");
        }
        deleteNumber = db.delete(AppDatabase.CompanyModelsTable.TABLE_NAME, whereClause.toString(), selectionArgs);
        break;
      case STOP_DIR_INDICATOR:
        deleteNumber = db.delete(AppDatabase.CompanyModelsTable.TABLE_NAME, selection, selectionArgs);
        break;
    }
    // In this case the notify the cursor listener of the reading. It's useful to notify the Adapter
    // that can update their data
    if (deleteNumber > 0) {
      getContext().getContentResolver().notifyChange(uri, null);
    }
    return deleteNumber;
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
    String itemId = null;
    StringBuilder whereClause = null;
    //SQLiteDatabase db = mDb.getWritableDatabase();
    SQLiteDatabase db = mDb;
    int updateNumber = 0;
    switch (URI_MATCHER.match(uri)) {
      case STOP_ITEM_INDICATOR:
        itemId = uri.getPathSegments().get(1);
        whereClause = new StringBuilder(AppDatabase.CompanyModelsTable.ID).append(" = ").append(itemId);
        if (selection != null) {
          whereClause.append(" AND (").append(selection).append(" ) ");
        }
        updateNumber = db.update(AppDatabase.CompanyModelsTable.TABLE_NAME, values, whereClause.toString(), selectionArgs);
        break;
      case STOP_DIR_INDICATOR:
        updateNumber = db.update(AppDatabase.CompanyModelsTable.TABLE_NAME, values, selection, selectionArgs);
        break;
    }
    // In this case the notify the cursor listener of the reading. It's useful to notify the Adapter
    // that can update their data
    if (updateNumber > 0) {
      getContext().getContentResolver().notifyChange(uri, null);
    }
    return updateNumber;
  }

  @Override
  public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations)
          throws OperationApplicationException {
    // We have overridden the applyBatch to improve performances. In this was we can
    // execute them into a transaction
    //final SQLiteDatabase db = mDb.getWritableDatabase();
    SQLiteDatabase db = mDb;
    db.beginTransaction();
    try {
      final int numOperations = operations.size();
      final ContentProviderResult[] results = new ContentProviderResult[numOperations];
      for (int i = 0; i < numOperations; i++) {
        results[i] = operations.get(i).apply(this, results, i);
      }
      db.setTransactionSuccessful();
      return results;
    } finally {
      db.endTransaction();
    }
  }
}
