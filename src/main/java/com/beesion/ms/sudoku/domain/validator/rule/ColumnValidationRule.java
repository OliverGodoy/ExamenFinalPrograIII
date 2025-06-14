package com.beesion.ms.sudoku.domain.validator.rule;

public class ColumnValidationRule implements SudokuValidationRule {
    @Override
    public boolean isValid(int[][] board) {
        for (int col = 0; col < 9; col++) {
            boolean[] used = new boolean[10];
            for (int row = 0; row < 9; row++) {
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

