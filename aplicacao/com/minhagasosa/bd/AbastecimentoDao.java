package com.minhagasosa.bd;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.minhagasosa.bd.Abastecimento;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table ABASTECIMENTO.
*/
public class AbastecimentoDao extends AbstractDao<Abastecimento, Long> {

    public static final String TABLENAME = "ABASTECIMENTO";

    /**
     * Properties of entity Abastecimento.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property DataAbastecimento = new Property(1, java.util.Date.class, "dataAbastecimento", false, "DATA_ABASTECIMENTO");
        public final static Property PrecoTotal = new Property(2, Float.class, "precoTotal", false, "PRECO_TOTAL");
        public final static Property PrecoCombustivel = new Property(3, Float.class, "precoCombustivel", false, "PRECO_COMBUSTIVEL");
        public final static Property Litros = new Property(4, Float.class, "litros", false, "LITROS");
        public final static Property Odometro = new Property(5, Float.class, "odometro", false, "ODOMETRO");
        public final static Property TanqueCheio = new Property(6, Boolean.class, "tanqueCheio", false, "TANQUE_CHEIO");
    };


    public AbastecimentoDao(DaoConfig config) {
        super(config);
    }
    
    public AbastecimentoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'ABASTECIMENTO' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'DATA_ABASTECIMENTO' INTEGER," + // 1: dataAbastecimento
                "'PRECO_TOTAL' REAL," + // 2: precoTotal
                "'PRECO_COMBUSTIVEL' REAL," + // 3: precoCombustivel
                "'LITROS' REAL," + // 4: litros
                "'ODOMETRO' REAL," + // 5: odometro
                "'TANQUE_CHEIO' INTEGER);"); // 6: tanqueCheio
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'ABASTECIMENTO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Abastecimento entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        java.util.Date dataAbastecimento = entity.getDataAbastecimento();
        if (dataAbastecimento != null) {
            stmt.bindLong(2, dataAbastecimento.getTime());
        }
 
        Float precoTotal = entity.getPrecoTotal();
        if (precoTotal != null) {
            stmt.bindDouble(3, precoTotal);
        }
 
        Float precoCombustivel = entity.getPrecoCombustivel();
        if (precoCombustivel != null) {
            stmt.bindDouble(4, precoCombustivel);
        }
 
        Float litros = entity.getLitros();
        if (litros != null) {
            stmt.bindDouble(5, litros);
        }
 
        Float odometro = entity.getOdometro();
        if (odometro != null) {
            stmt.bindDouble(6, odometro);
        }
 
        Boolean tanqueCheio = entity.getTanqueCheio();
        if (tanqueCheio != null) {
            stmt.bindLong(7, tanqueCheio ? 1l: 0l);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Abastecimento readEntity(Cursor cursor, int offset) {
        Abastecimento entity = new Abastecimento( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)), // dataAbastecimento
            cursor.isNull(offset + 2) ? null : cursor.getFloat(offset + 2), // precoTotal
            cursor.isNull(offset + 3) ? null : cursor.getFloat(offset + 3), // precoCombustivel
            cursor.isNull(offset + 4) ? null : cursor.getFloat(offset + 4), // litros
            cursor.isNull(offset + 5) ? null : cursor.getFloat(offset + 5), // odometro
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0 // tanqueCheio
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Abastecimento entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDataAbastecimento(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
        entity.setPrecoTotal(cursor.isNull(offset + 2) ? null : cursor.getFloat(offset + 2));
        entity.setPrecoCombustivel(cursor.isNull(offset + 3) ? null : cursor.getFloat(offset + 3));
        entity.setLitros(cursor.isNull(offset + 4) ? null : cursor.getFloat(offset + 4));
        entity.setOdometro(cursor.isNull(offset + 5) ? null : cursor.getFloat(offset + 5));
        entity.setTanqueCheio(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Abastecimento entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Abastecimento entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
