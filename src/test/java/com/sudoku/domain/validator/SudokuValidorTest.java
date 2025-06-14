package com.sudoku.domain.validator;

import com.beesion.ms.sudoku.domain.validator.rule.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.smallrye.common.constraint.Assert.assertFalse;
import static io.smallrye.common.constraint.Assert.assertTrue;

@DisplayName("Sudoku Validator Tests")
class SudokuValidatorTest {

    private SudokuValidator validator;

    @BeforeEach
    void setUp() {
        List<SudokuValidationRule> rules = Arrays.asList(
                new RowValidationRule(),
                new ColumnValidationRule(),
                new SubGridValidationRule()
        );
        validator = new SudokuValidator(rules);
    }

    @Test
    @DisplayName("Debería validar un tablero de Sudoku válido")
    void shouldValidateCorrectSudokuBoard() {
        int[][] validBoard = {
                {5,3,0,0,7,0,0,0,0},
                {6,0,0,1,9,5,0,0,0},
                {0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},
                {4,0,0,8,0,3,0,0,1},
                {7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,8,0,0,7,9}
        };

        assertTrue(validator.isValidSudoku(validBoard));
    }

    @Test
    @DisplayName("Debería detectar un tablero de Sudoku inválido con fila duplicada")
    void shouldDetectInvalidRowInSudokuBoard() {
        int[][] invalidBoard = {
                {5,3,5,0,7,0,0,0,0}, // 5 duplicado en la primera fila
                {6,0,0,1,9,5,0,0,0},
                {0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},
                {4,0,0,8,0,3,0,0,1},
                {7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,8,0,0,7,9}
        };

        assertFalse(validator.isValidSudoku(invalidBoard));
    }

    @Test
    @DisplayName("Debería detectar un tablero de Sudoku inválido con columna duplicada")
    void shouldDetectInvalidColumnInSudokuBoard() {
        int[][] invalidBoard = {
                {5,3,0,0,7,0,0,0,0},
                {5,0,0,1,9,5,0,0,0}, // 5 duplicado en la primera columna
                {0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},
                {4,0,0,8,0,3,0,0,1},
                {7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,8,0,0,7,9}
        };

        assertFalse(validator.isValidSudoku(invalidBoard));
    }

    @Test
    @DisplayName("Debería detectar un tablero de Sudoku inválido con sub-cuadrícula duplicada")
    void shouldDetectInvalidSubGridInSudokuBoard() {
        int[][] invalidBoard = {
                {5,3,0,0,7,0,0,0,0},
                {6,5,0,1,9,5,0,0,0}, // 5 duplicado en la primera sub-cuadrícula 3x3
                {0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},
                {4,0,0,8,0,3,0,0,1},
                {7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,8,0,0,7,9}
        };

        assertFalse(validator.isValidSudoku(invalidBoard));
    }
}

