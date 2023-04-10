package com.finalproject.recruit.service;

import com.finalproject.recruit.dto.applymanage.ApplyResponseDTO;
import com.finalproject.recruit.parameter.ApplyProcedure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
class ApplyManageServiceTest {

    @InjectMocks
    private ApplyManageService applyManageService;


    @Test
    public void calcScore(){
        ApplyResponseDTO dto = new ApplyResponseDTO(
                1L,
                "지원자1",
                "010-1111-2222",
                "apply@test.com",
                ApplyProcedure.서류제출,
                true,
                false,
                LocalDateTime.now(),
                false,
                true,
                "잘 웃어요,꼼꼼해요,집이 가까워요,통찰력이 좋아요,설명을 잘해요",
                0,
                List.of()
        );


        HashSet<String> keywordStandard= new HashSet<>(Arrays.asList("잘 웃어요","센스 좋아요","집이 가까워요","결근 안해요","일 잘해요"));


        applyManageService.calcKeywordScore(dto, keywordStandard);


        then(dto.getScore()).isEqualTo(2);
        then(dto.getKeywordList()).isEqualTo(List.of("잘 웃어요","꼼꼼해요","집이 가까워요","통찰력이 좋아요","설명을 잘해요"));
    }
}