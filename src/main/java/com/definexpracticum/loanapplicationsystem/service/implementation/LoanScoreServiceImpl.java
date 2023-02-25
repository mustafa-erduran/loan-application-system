package com.definexpracticum.loanapplicationsystem.service.implementation;

import com.definexpracticum.loanapplicationsystem.service.LoanScoreService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class LoanScoreServiceImpl implements LoanScoreService {

    private static List<Integer> scoreList = Arrays.asList(450, 330, 3000, 500, 750, 1000, 100, 2000, 850);

    private Integer randomIndexGenerator() {
        Random randomIndex = new Random();
        return randomIndex.nextInt(8);
    }

    @Override
    public Integer getLoanScore() {
        return scoreList.get(randomIndexGenerator());
    }
}
