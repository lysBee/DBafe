package com.yspjt.dbafe.Service;

import com.yspjt.dbafe.Model.SigModel;

public interface SiginUpService {

    boolean login(SigModel sigmodal);

    int callUserCode(SigModel sigmodal);

    boolean checkDuplicateId(SigModel sigModel);

    boolean joinUp(SigModel sigModel);

}
