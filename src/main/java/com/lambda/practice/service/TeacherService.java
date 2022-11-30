package com.lambda.practice.service;

import com.lambda.practice.domain.Teacher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    public String world(){
        //exception을 발생 시킴(개발자가)
        Optional<Teacher> teacherOptional = Optional.empty();
        Teacher teacher = teacherOptional.orElseThrow(() -> new RuntimeException("해당 teacher가 없습니다."));
        //위에서 처리한 exception은 콘솔에만 찍히고 사용자는 모름.
        // 유저에게 exception이 왜 발생했는지 알려주고 싶다. -> @RestControllerAdvice를 사용해서 에러를 처리한다.

        return "World";
    }
}
