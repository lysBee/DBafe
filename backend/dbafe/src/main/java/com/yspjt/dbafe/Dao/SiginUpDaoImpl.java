package com.yspjt.dbafe.Dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.yspjt.dbafe.Model.SigModel;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SiginUpDaoImpl implements SiginUpDao {
    private final SqlSession session;

    @Override
    public SigModel login(SigModel sigModel) {
        try {
            // dbfLogin 쿼리를 사용하여 로그인을 시도합니다.
            SigModel result = session.selectOne("dbfLogin", sigModel);

            if (result != null) {
                // 로그인 성공 시, 결과를 반환합니다.
                return result;
            } else {
                // 로그인 실패 시, null을 반환하거나 예외를 던질 수 있습니다.
                // 여기서는 null을 반환하도록 예시로 작성합니다.
                return null;
            }
        } catch (Exception e) {
            // 예외가 발생할 경우, 적절히 처리하여 로그에 오류 메시지를 남깁니다.
            System.out.println("로그인 중 오류 발생: " + e.getMessage());
            // 예외가 발생했을 때도 null을 반환하거나 예외를 다시 던질 수 있습니다.
            return null;
        }
    }

    @Override
    public int callUserCode(SigModel sigModel) {
        int callUserCode = 0;
        try {
            callUserCode = session.selectOne("callUserCode", sigModel);
        } catch (Exception e) {
            System.out.println("콜스테이트 다오임플 오류 " + e);
        }
        return callUserCode;
    }

    @Override
    public int checkDuplicateId(SigModel sigModel) {
        int checkDuplicateId = 0;
        try {
            checkDuplicateId = session.selectOne("checkDuplicateId", sigModel);

        } catch (Exception e) {
            System.out.println("중복확인임플에서 오류 발생: " + e.getMessage());

        }

        return checkDuplicateId;
    }

    @Override
    public boolean joinUp1(SigModel sigModel) {
        boolean joinUp = false;
        try {
            int joinUpCheck = session.insert("joinUp1", sigModel);
            joinUp = joinUpCheck == 1;
        } catch (Exception e) {
            System.err.println("회원가입 다오 임플 joinUp1 에러: " + e);
        }
        return joinUp;
    }

    @Override
    public boolean joinUp2(SigModel sigModel) {
        boolean joinUp = false;
        try {
            int joinUpCheck = session.insert("joinUp2", sigModel);
            joinUp = joinUpCheck == 1;
        } catch (Exception e) {
            System.err.println("회원가입 다오 임플 joinUp2 에러: " + e);
        }
        return joinUp;
    }

}
