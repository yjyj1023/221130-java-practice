package com.lambda.practice.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void name() {
        List<Teacher> teachers = new ArrayList<>();
        Teacher kyeongrok = new Teacher("김경록", true, true);
        Teacher kyeonghwan = new Teacher("고경환", true, false);
        Teacher sujin = new Teacher("김수진", false, true);
        Teacher sohyun = new Teacher("강소현", true, true);

        teachers.add(kyeongrok);
        teachers.add(kyeonghwan);
        teachers.add(sujin);
        teachers.add(sohyun);
        teachers.add(new Teacher("김경", false, true));

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

        //optional 사용
        Optional<Teacher> optionalTeacher = Optional.of(kyeongrok);
        optionalTeacher.orElseThrow(() -> new RuntimeException());
    }

    @Test
    void predicateTest() {
        // 숫자 num을 넣으면 10보다 큰지 true,false로 리턴 해주는 내장 인터페이스
        Predicate<Integer> predicate = num -> num>10;
        System.out.println(predicate.test(10));
    }

    @Test
    void map() {
        // 문자열 list -> Integer list
        String[] list = {"1", "2", "3"};
        List<Integer> nums = Arrays.stream(list)
                .map(strNum -> Integer.parseInt(strNum))
                .collect(Collectors.toList());

        List<Teacher> teachers = new ArrayList<>();
        Teacher kyeongrok = new Teacher("김경록", true, true);
        Teacher kyeonghwan = new Teacher("고경환", true, false);
        Teacher sujin = new Teacher("김수진", false, true);
        Teacher sohyun = new Teacher("강소현", true, true);

        teachers.add(kyeongrok);
        teachers.add(kyeonghwan);
        teachers.add(sujin);
        teachers.add(sohyun);
        teachers.add(new Teacher("김경", false, true));

        //isLikeSpringBoot == true인 Teacher의 이름의 자릿수 구하기
        List<Integer> digitsOfNames = teachers.stream()
                .filter(Teacher::isLikeSpringBoot)
                .map(Teacher::cntNameDigit)
                .collect(Collectors.toList());
    }
}