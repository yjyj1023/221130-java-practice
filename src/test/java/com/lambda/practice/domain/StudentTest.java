package com.lambda.practice.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    public void dummy() {
        List<Teacher> teachers = new ArrayList<>();
        Teacher kyeongrok = new Teacher("김경록", true, true);
        Teacher kyeonghwan = new Teacher("고경환", true, false);
        Teacher sujin = new Teacher("김수진", false, true);
        Teacher sohyun = new Teacher("강소현", true, true);

        teachers.add(kyeongrok);
        teachers.add(kyeonghwan);
        teachers.add(sujin);
        teachers.add(sohyun);

        //stream 사용 전
        List<Teacher> likeAlgorithmTeacher = new ArrayList<>();
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).isLikeAlgorithm()){
                likeAlgorithmTeacher.add(teachers.get(i));
            }
        }

        //stream().filter() 사용
        System.out.println("---알고리즘 true 이신 분들---");
        List<Teacher> likeAlgorithmTeachers = teachers.stream()
                .filter(teacher -> teacher.isLikeAlgorithm() == true)
                .collect(Collectors.toList());

        for(Teacher teacher: likeAlgorithmTeachers){
            System.out.println(teacher.getName());
        }

        //stream().filter().map() 사용
        System.out.println("---SpringBoot 가 true 이신 분들---");
        List<String> likeSpringBootTeachers = teachers.stream()
                .filter(teacher -> teacher.isLikeSpringBoot() == true)
                .map(teacher -> teacher.getName())
                .collect(Collectors.toList());

        for(String teacherName: likeSpringBootTeachers){
            System.out.println(teacherName);
        }
    }
}