package com.yspjt.dbafe.Dao;

import com.yspjt.dbafe.Model.SigModel;

public interface SiginUpDao {

    SigModel login(SigModel sigmodal);

    int callUserCode(SigModel sigmodal);

    int checkDuplicateId(SigModel sigModel);

    boolean joinUp1(SigModel sigModel);

    boolean joinUp2(SigModel sigModel);

}
