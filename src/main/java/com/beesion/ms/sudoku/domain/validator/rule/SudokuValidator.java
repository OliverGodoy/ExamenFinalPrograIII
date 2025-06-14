package com.beesion.ms.sudoku.domain.validator.rule;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SudokuValidator {
    private final List<SudokuValidationRule> rules;

    public SudokuValidator(List<SudokuValidationRule> rules) {
        this.rules = rules;
    }

    public boolean isValidSudoku(int[][] board) {
        return rules.stream().allMatch(rule -> rule.isValid(board));
    }
}

