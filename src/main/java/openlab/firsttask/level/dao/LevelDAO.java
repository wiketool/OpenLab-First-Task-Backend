package openlab.firsttask.level.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import openlab.firsttask.level.Entity.LevelDO;
import openlab.firsttask.level.mapper.LevelMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LevelDAO extends ServiceImpl<LevelMapper, LevelDO> {

}
