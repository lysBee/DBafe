package com.yspjt.dbafe.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yspjt.dbafe.Dao.SiginUpDao;
import com.yspjt.dbafe.Model.SigModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SiginUpServiceImpl implements SiginUpService {
    private final SiginUpDao siginUpDao;

    @Override
    public boolean login(SigModel sigModel) {
        String encryptedPassword = PasswordEncryptor.passEncoding(sigModel.getUser_pass());
        sigModel.setUser_pass(encryptedPassword);
        SigModel login = siginUpDao.login(sigModel);
        if (login != null) {
            return true;

        } else {
            return false;
        }
    }

    @Override
    public int callUserCode(SigModel sigModel) {
        int callUserCode = 0;
        try {
            callUserCode = siginUpDao.callUserCode(sigModel);
        } catch (Exception e) {
            System.out.println("콜스테이트 서비스 임플 오류" + e);
        }

        return callUserCode;
    }

    @Override
    public boolean checkDuplicateId(SigModel sigModel) {
        boolean checkDuplicateId = false;
        try {
            int idCheck = siginUpDao.checkDuplicateId(sigModel);
            if (idCheck == 0) {
                checkDuplicateId = true;
            } else {
                checkDuplicateId = false;
            }
        } catch (Exception e) {
            System.out.println("checkDuplicateId 서비스 임플 오류" + e);
        }

        return checkDuplicateId;
    }

    @Override
    @Transactional
    public boolean joinUp(SigModel sigModel) {
        boolean joinUp = false;
        try {
            String encryptedPassword = PasswordEncryptor.passEncoding(sigModel.getUser_pass());
            sigModel.setUser_pass(encryptedPassword);

            // BMI 계산 로직 추가
            double heightInMeters = sigModel.getUser_height() / 100.0;
            double bmi = sigModel.getUser_weight() / (heightInMeters * heightInMeters);

            // BMI를 소수점 두 자리까지 반올림하여 계산
            BigDecimal bmiBigDecimal = new BigDecimal(bmi);
            bmiBigDecimal = bmiBigDecimal.setScale(2, RoundingMode.HALF_UP);

            // Convert BigDecimal to double
            double bmiDouble = bmiBigDecimal.doubleValue();

            // Set BMI in the model
            sigModel.setUser_bmi(bmiDouble);

            boolean result1 = siginUpDao.joinUp1(sigModel);
            boolean result2 = siginUpDao.joinUp2(sigModel);
            joinUp = result1 && result2;
        } catch (Exception e) {
            System.out.println("회원 가입 서비스 임플" + e);
        }
        return joinUp;
    }

}
