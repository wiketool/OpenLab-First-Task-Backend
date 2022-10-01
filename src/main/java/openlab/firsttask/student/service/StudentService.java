package openlab.firsttask.student.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import openlab.firsttask.student.dto.StudentDTO;
import openlab.firsttask.student.entity.StudentDO;
import openlab.firsttask.student.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    public Long selectOrInsertStudent(StudentDTO studentDTO) {
        LambdaQueryWrapper<StudentDO> isExistQuery = new QueryWrapper<StudentDO>().lambda();
        isExistQuery.eq(StudentDO::getStudentId, studentDTO.getStudentId());
        StudentDO studentDO = studentMapper.selectOne(isExistQuery);
        if (studentDO == null) {
            studentDO = StudentDO.builder()
                                 .studentId(studentDTO.getStudentId())
                                 .name(studentDTO.getName()).build();
            studentMapper.insert(studentDO);
        }
        Long id = studentDO.getId();
        return id;
    }

    public Optional<Long> selectStudentPKId(Long studentId) {
        LambdaQueryWrapper<StudentDO> query = new QueryWrapper<StudentDO>().lambda();
        query.eq(StudentDO::getStudentId, studentId);
        StudentDO studentDO = studentMapper.selectOne(query);
        if (studentDO == null) {
            return Optional.empty();
        }
        return Optional.of(studentDO.getId());
    }


}
