package com.minhagasosa.dao;

import io.requery.*;

@Entity
abstract class AbstractCarro {

    @Key @Generated
    Long id;
    Marca marca;
    String nome;
}
