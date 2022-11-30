package com.lambda.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Teacher {
    private String name;
    private boolean isLikeAlgorithm;
    private boolean isLikeSpringBoot;

    public int cntNameDigit(){
        return this.name.length();
    }

    public int getCnt(){
        return 1;
    }
}
