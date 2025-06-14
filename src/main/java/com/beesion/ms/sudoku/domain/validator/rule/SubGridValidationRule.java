package com.beesion.ms.sudoku.domain.validator.rule;

public class SubGridValidationRule implements SudokuValidationRule {
    @Override
    public boolean isValid(int[][] board) {
        for (int box = 0; box < 9; box++) {
            boolean[] used = new boolean[10];
            int rowStart = (box / 3) * 3;
            int colStart = (box % 3) * 3;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int num = board[rowStart + i][colStart + j];
                    if (num != 0) {
                        if (used[num]) {
                            return false;
                        }
                        used[num] = true;
                    }
                }
            }
        }
        return true;
    }
}

