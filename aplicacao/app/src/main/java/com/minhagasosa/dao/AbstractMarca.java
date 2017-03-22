package com.minhagasosa.dao;

import io.requery.*;

@Entity
abstract class AbstractMarca {

    @Key @Generated
    Long id;
    String nome;
}
