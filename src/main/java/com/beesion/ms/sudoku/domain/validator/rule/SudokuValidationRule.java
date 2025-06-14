package com.beesion.ms.sudoku.domain.validator.rule;

public interface SudokuValidationRule {
    boolean isValid(int[][] board);
}
