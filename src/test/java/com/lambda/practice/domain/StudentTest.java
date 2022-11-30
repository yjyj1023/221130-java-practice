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

        System.out.println(digitsOfNames);
    }

    @Test
    void reduce() {

        String[] list = {"1", "2", "3"};
        List<Integer> nums = Arrays.stream(list)
                .map(strNum -> Integer.parseInt(strNum))
                .collect(Collectors.toList());

        //그냥 list 합 구하기
        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
        }
        System.out.println(sum);

        //맵리듀스 사용해서 list 합 구하기
        int reduceSum = Arrays.stream(list)
                .map(strNum -> Integer.parseInt(strNum))
                .reduce(0, (a, b) -> a+b); // 재귀와 비슷하다.
        System.out.println(reduceSum);


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

        //맵리듀스 사용해서 SpringBoot을 좋아하시는 선생님들의 수 구하기
        int reduceIsLikeSpringBootTeachers = teachers.stream()
                .filter(Teacher::isLikeSpringBoot)
                .map(teacher -> teacher.getCnt())
                .reduce(0, (a, b) -> a+b); // 재귀와 비슷하다.
        System.out.println(reduceIsLikeSpringBootTeachers);

    }

    @Test
    void optionalTest() {
        //optional 사용
        //DB에서 select되어 값이 있는 상태
        Optional<Teacher> optionalTeacher = Optional.of(new Teacher("김경록", true, true));
        Optional<Teacher> emptyTeacher = Optional.empty();

        //리스트의 경우
        List<Teacher> teachers = new ArrayList<>();
        teachers.size(); // -> 값이 없으면 0으로 출력, optional은 null

        //값이 있는 경우
        Teacher teacher = optionalTeacher.get();
        System.out.println(teacher);

        //값이 없는 경우, No value present 에러가 남.
        //emptyTeacher.get();

        // 에러가 나기때문에 에러를 던져줌
        //Teacher teacher2 =emptyTeacher.orElseThrow(RuntimeException::new);
        //Teacher teacher3 =emptyTeacher.orElseThrow(() -> new RuntimeException());

        // 값이 있는 경우 무언가 처리 하고 싶을 때
        // 존재하는 경우 RuntimeException 에러가 나면서 이름이 출력됨.
        //optionalTeacher.ifPresent(sth -> {
        //    throw new RuntimeException(sth.getName());
        //});

        // .ofNullable, orElse() 사용하기
        Optional<Teacher> optionalTeacher2 = Optional.of(new Teacher(null, true, true));
        String name = Optional.ofNullable(optionalTeacher2.get().getName()).orElse("이름이 없습니다.");
        System.out.println(name);

    }
}