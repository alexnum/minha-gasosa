package com.minhagasosa.bd;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.minhagasosa.bd.Modelo;
import com.minhagasosa.bd.Carro;
import com.minhagasosa.bd.Rota;
import com.minhagasosa.bd.Abastecimento;

import com.minhagasosa.bd.ModeloDao;
import com.minhagasosa.bd.CarroDao;
import com.minhagasosa.bd.RotaDao;
import com.minhagasosa.bd.AbastecimentoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig modeloDaoConfig;
    private final DaoConfig carroDaoConfig;
    private final DaoConfig rotaDaoConfig;
    private final DaoConfig abastecimentoDaoConfig;

    private final ModeloDao modeloDao;
    private final CarroDao carroDao;
    private final RotaDao rotaDao;
    private final AbastecimentoDao abastecimentoDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        modeloDaoConfig = daoConfigMap.get(ModeloDao.class).clone();
        modeloDaoConfig.initIdentityScope(type);

        carroDaoConfig = daoConfigMap.get(CarroDao.class).clone();
        carroDaoConfig.initIdentityScope(type);

        rotaDaoConfig = daoConfigMap.get(RotaDao.class).clone();
        rotaDaoConfig.initIdentityScope(type);

        abastecimentoDaoConfig = daoConfigMap.get(AbastecimentoDao.class).clone();
        abastecimentoDaoConfig.initIdentityScope(type);

        modeloDao = new ModeloDao(modeloDaoConfig, this);
        carroDao = new CarroDao(carroDaoConfig, this);
        rotaDao = new RotaDao(rotaDaoConfig, this);
        abastecimentoDao = new AbastecimentoDao(abastecimentoDaoConfig, this);

        registerDao(Modelo.class, modeloDao);
        registerDao(Carro.class, carroDao);
        registerDao(Rota.class, rotaDao);
        registerDao(Abastecimento.class, abastecimentoDao);
    }
    
    public void clear() {
        modeloDaoConfig.getIdentityScope().clear();
        carroDaoConfig.getIdentityScope().clear();
        rotaDaoConfig.getIdentityScope().clear();
        abastecimentoDaoConfig.getIdentityScope().clear();
    }

    public ModeloDao getModeloDao() {
        return modeloDao;
    }

    public CarroDao getCarroDao() {
        return carroDao;
    }

    public RotaDao getRotaDao() {
        return rotaDao;
    }

    public AbastecimentoDao getAbastecimentoDao() {
        return abastecimentoDao;
    }

}