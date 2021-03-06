package com.minhagasosa.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import com.minhagasosa.dao.Carro;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * DAO for table "CARRO".
 */
public class CarroDao extends AbstractDao<Carro, Long> {

    public static final String TABLENAME = "CARRO";
    private final int THREE = 3;
    private final int FOUR = 4;
    private final int FIVE = 5;
    private final int SIX = 6;
    private final int SEVEN = 7;
    private final int EIGHT = 8;
    private final int NINE = 9;
    private final int TEN = 10;
    private DaoSession daoSession;

    public CarroDao(DaoConfig config) {
        super(config);
    }

    public CarroDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CARRO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"MARCA\" TEXT," + // 1: marca
                "\"ANO\" TEXT," + // 2: ano
                "\"CONSUMO_URBANO_GASOLINA\" REAL," + // THREE: consumoUrbanoGasolina
                "\"CONSUMO_RODOVIARIO_GASOLINA\" REAL," + // FOUR: consumoRodoviarioGasolina
                "\"CONSUMO_URBANO_ALCOOL\" REAL," + // FIVE: consumoUrbanoAlcool
                "\"CONSUMO_RODOVIARIO_ALCOOL\" REAL," + // SIX: consumoRodoviarioAlcool
                "\"IS_FLEX\" INTEGER," + // SEVEN: isFlex
                "\"VERSION\" TEXT," + // EIGHT: version
                "\"MODELO_ID\" INTEGER);"); // NINE: modeloId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CARRO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    public void bindValues(SQLiteStatement stmt, Carro entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String marca = entity.getMarca();
        if (marca != null) {
            stmt.bindString(2, marca);
        }

        String ano = entity.getAno();
        if (ano != null) {
            stmt.bindString(THREE, ano);
        }

        Float consumoUrbanoGasolina = entity.getConsumoUrbanoGasolina();
        if (consumoUrbanoGasolina != null) {
            stmt.bindDouble(FOUR, consumoUrbanoGasolina);
        }

        Float consumoRodoviarioGasolina = entity.getConsumoRodoviarioGasolina();
        if (consumoRodoviarioGasolina != null) {
            stmt.bindDouble(FIVE, consumoRodoviarioGasolina);
        }

        Float consumoUrbanoAlcool = entity.getConsumoUrbanoAlcool();
        if (consumoUrbanoAlcool != null) {
            stmt.bindDouble(SIX, consumoUrbanoAlcool);
        }

        Float consumoRodoviarioAlcool = entity.getConsumoRodoviarioAlcool();
        if (consumoRodoviarioAlcool != null) {
            stmt.bindDouble(SEVEN, consumoRodoviarioAlcool);
        }

        Boolean isFlex = entity.getIsFlex();
        if (isFlex != null) {
            stmt.bindLong(EIGHT, isFlex ? 1L: 0L);
        }

        String version = entity.getVersion();
        if (version != null) {
            stmt.bindString(NINE, version);
        }

        Long modeloId = entity.getModeloId();
        if (modeloId != null) {
            stmt.bindLong(TEN, modeloId);
        }
    }

    @Override
    public void attachEntity(Carro entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /** @inheritdoc */
    @Override
    public Carro readEntity(Cursor cursor, int offset) {
        Carro entity = new Carro( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // marca
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // ano
                cursor.isNull(offset + THREE) ? null : cursor.getFloat(offset + THREE), // consumoUrbanoGasolina
                cursor.isNull(offset + FOUR) ? null : cursor.getFloat(offset + FOUR), // consumoRodoviarioGasolina
                cursor.isNull(offset + FIVE) ? null : cursor.getFloat(offset + FIVE), // consumoUrbanoAlcool
                cursor.isNull(offset + SIX) ? null : cursor.getFloat(offset + SIX), // consumoRodoviarioAlcool
                cursor.isNull(offset + SEVEN) ? null : cursor.getShort(offset + SEVEN) != 0, // isFlex
                cursor.isNull(offset + EIGHT) ? null : cursor.getString(offset + EIGHT), // version
                cursor.isNull(offset + NINE) ? null : cursor.getLong(offset + NINE) // modeloId
        );
        return entity;
    }

    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Carro entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMarca(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAno(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setConsumoUrbanoGasolina(cursor.isNull(offset + THREE) ? null : cursor.getFloat(offset + THREE));
        entity.setConsumoRodoviarioGasolina(cursor.isNull(offset + FOUR) ? null : cursor.getFloat(offset + FOUR));
        entity.setConsumoUrbanoAlcool(cursor.isNull(offset + FIVE) ? null : cursor.getFloat(offset + FIVE));
        entity.setConsumoRodoviarioAlcool(cursor.isNull(offset + SIX) ? null : cursor.getFloat(offset + SIX));
        entity.setIsFlex(cursor.isNull(offset + SEVEN) ? null : cursor.getShort(offset + SEVEN) != 0);
        entity.setVersion(cursor.isNull(offset + EIGHT) ? null : cursor.getString(offset + EIGHT));
        entity.setModeloId(cursor.isNull(offset + NINE) ? null : cursor.getLong(offset + NINE));
    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Carro entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(Carro entity) {
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

    private String selectDeep;

    String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getModeloDao().getAllColumns());
            builder.append(" FROM CARRO T");
            builder.append(" LEFT JOIN MODELO T0 ON T.\"MODELO_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }

    protected Carro loadCurrentDeep(Cursor cursor, boolean lock) {
        Carro entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Modelo modelo = loadCurrentOther(daoSession.getModeloDao(), cursor, offset);
        entity.setModelo(modelo);

        return entity;
    }

    Carro loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();

        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);

        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }

    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    List<Carro> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Carro> list = new ArrayList<Carro>(count);

        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }

    List<Carro> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }


    /** A raw-style query where you can pass any WHERE clause and arguments. */
    List<Carro> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }

    /**
     * Properties of entity Carro.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "id", true, "_id");
        public final static Property MARCA = new Property(1, String.class, "marca", false, "MARCA");
        public final static Property ANO = new Property(2, String.class, "ano", false, "ANO");
        public final static Property CONSUMO_URBANO_GASOLINA = new Property(3, Float.class, "consumoUrbanoGasolina", false, "CONSUMO_URBANO_GASOLINA");
        public final static Property CONSUMO_RODOVIARIO_GASOLINA = new Property(4, Float.class, "consumoRodoviarioGasolina", false, "CONSUMO_RODOVIARIO_GASOLINA");
        public final static Property CONSUMO_URBANO_ALCOOL = new Property(5, Float.class, "consumoUrbanoAlcool", false, "CONSUMO_URBANO_ALCOOL");
        public final static Property CONSUMO_RODOVIARIO_ALCOOL = new Property(6, Float.class, "consumoRodoviarioAlcool", false, "CONSUMO_RODOVIARIO_ALCOOL");
        public final static Property IS_FLEX = new Property(7, Boolean.class, "isFlex", false, "IS_FLEX");
        public final static Property VERSION = new Property(8, String.class, "version", false, "VERSION");
        public final static Property MODELO_ID = new Property(9, Long.class, "modeloId", false, "MODELO_ID");
    };

}
