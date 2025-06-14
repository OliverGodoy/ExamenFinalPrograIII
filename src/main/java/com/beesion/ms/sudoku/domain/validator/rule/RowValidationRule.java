package com.beesion.ms.sudoku.domain.validator.rule;

public class RowValidationRule implements SudokuValidationRule {
    @Override
    public boolean isValid(int[][] board) {
        for (int row = 0; row < 9; row++) {
            boolean[] used = new boolean[10];
            for (int col = 0; col < 9; col++) {
                int num = board[row][col];
                if (num != 0) {
                    if (used[num]) {
                        return false;
                    }
                    used[num] = true;
                }
            }
        }
        return true;
    }
}

