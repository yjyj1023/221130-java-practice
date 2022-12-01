package com.lambda.practice.service;

import com.lambda.practice.domain.Teacher;
import com.lambda.practice.exception.ErrorCode;
import com.lambda.practice.exception.HospitalException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    public String world(String userName){
        //exception을 발생 시킴(개발자가)
        Optional<Teacher> teacherOptional = Optional.empty();

        //HospitalException을 Throw할 때 HospitalException에 선언한 ErrorCode와 message를 전달한다.
        Teacher teacher = teacherOptional.orElseThrow(() ->
                new HospitalException(ErrorCode.USERNAME_NOT_FOUND, "DB에 "+userName+"으로 검색 했을 때 빈값이 아닙니다."));
        //위에서 처리한 exception은 콘솔에만 찍히고 사용자는 모름.
        // 유저에게 exception이 왜 발생했는지 알려주고 싶다. -> @RestControllerAdvice를 사용해서 에러를 처리한다.

        return "World";
    }
}
