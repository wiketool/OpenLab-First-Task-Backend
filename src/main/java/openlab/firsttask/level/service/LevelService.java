package openlab.firsttask.level.service;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import openlab.firsttask.common.enums.ApiExceptionEnum;
import openlab.firsttask.common.utils.AssertUtils;
import openlab.firsttask.level.Entity.LevelDO;
import openlab.firsttask.level.dao.LevelDAO;
import openlab.firsttask.level.dto.response.LevelPasswordResponseDTO;
import openlab.firsttask.level.dto.response.PassLevelResponseDTO;
import openlab.firsttask.level.dto.resquest.PassLevelRequestDTO;
import openlab.firsttask.level.dto.resquest.VerifyLevelRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.MessageFormat;

@Service
public class LevelService {
    @Autowired
    LevelDAO levelDAO;

    public PassLevelResponseDTO selectOrInsertPassLevelInfo(PassLevelRequestDTO passLevelRequestDTO,
                                                            Long studentPKId) {
        LambdaQueryChainWrapper<LevelDO> isExistQuery = levelDAO.lambdaQuery();
        isExistQuery.eq(LevelDO::getLevelId, passLevelRequestDTO.getLevelId());
        isExistQuery.eq(LevelDO::getStudentPKId, studentPKId);
        LevelDO levelDO = isExistQuery.one();
        if (levelDO == null) {
            String flag = DigestUtils.md5DigestAsHex(
                    MessageFormat.format("Level{0}{1}", passLevelRequestDTO.getLevelId(), studentPKId)
                                 .getBytes());
            levelDO = LevelDO.builder().flag(flag)
                             .levelId(passLevelRequestDTO.getLevelId())
                             .passed(1).score(10)
                             .studentPKId(studentPKId)
                             .build();
            boolean isSave = levelDAO.save(levelDO);
            AssertUtils.isTrue(isSave, ApiExceptionEnum.DATABASE_ERROR);
        }
        PassLevelResponseDTO responseDTO = PassLevelResponseDTO.builder()
                                                               .state(levelDO.getPassed())
                                                               .flag(levelDO.getFlag())
                                                               .build();
        return responseDTO;
    }

    /**
     * 返回1为过关。<br>
     * 返回0为没有过关，可能原因有：数据库没有记录、flag错误
     */
    public Integer verifyFlagLevel(VerifyLevelRequestDTO requestDTO, Long studentPKId) {
        LambdaQueryChainWrapper<LevelDO> query = levelDAO.lambdaQuery();
        query.eq(LevelDO::getStudentPKId, studentPKId);
        query.eq(LevelDO::getLevelId, requestDTO.getLevelId());
        LevelDO levelDO = query.one();
        if (levelDO == null || (levelDO.getFlag().equals(requestDTO.getFlag())) == false) {
            return 0;
        }
        return 1;
    }

    public Integer queryIsPassedLevel(Integer levelId, Long studentPKId) {
        LambdaQueryChainWrapper<LevelDO> query = levelDAO.lambdaQuery();
        query.eq(LevelDO::getStudentPKId, studentPKId);
        query.eq(LevelDO::getLevelId, levelId);
        if (query.count() < 1) {
            return 0;
        }
        return 1;
    }

    public LevelPasswordResponseDTO verifyLevelPassword(String password, Long studentPKId) {
        String source = MessageFormat.format("FHDJKFDHH{0}", studentPKId);
        String correctPassword = DigestUtils.md5DigestAsHex(source.getBytes()).substring(0, 6);
        LevelPasswordResponseDTO responseDTO = LevelPasswordResponseDTO.builder()
                                                                       .passed(1)
                                                                       .build();
        if (correctPassword.equals(password) == false) {
            responseDTO.setPassed(0);
            responseDTO.setPassword(correctPassword);
        }
        return responseDTO;
    }

}
