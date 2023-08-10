package com.sera.snsdemo.domain.post.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DailyPostCountRequest {
    Long memberId;
    LocalDate firstDate;
    LocalDate lastDate;
}
