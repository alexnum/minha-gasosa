package com.minhagasosa.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.minhagasosa.dao.Modelo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MODELO".
*/
public class ModeloDao extends AbstractDao<Modelo, Long> {

    public static final String TABLENAME = "MODELO";



    public ModeloDao(DaoConfig config) {
        super(config);
    }
    
    public ModeloDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MODELO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"MODELO\" TEXT);"); // 1: MODELO
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MODELO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected final void bindValues(SQLiteStatement stmt, Modelo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String MODELO = entity.getMODELO();
        if (MODELO != null) {
            stmt.bindString(2, MODELO);
        }
    }

    /** @inheritdoc */
    @Override
    public final Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public final Modelo readEntity(Cursor cursor, int offset) {
        Modelo entity = new Modelo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // MODELO
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public final void readEntity(Cursor cursor, Modelo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMODELO(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected final Long updateKeyAfterInsert(Modelo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public final Long getKey(Modelo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected final boolean isEntityUpdateable() {
        return true;
    }


    /**
     * Properties of entity Modelo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "id", true, "_id");
        public final static Property MODELO = new Property(1, String.class, "MODELO", false, "MODELO");
    }
    
}
