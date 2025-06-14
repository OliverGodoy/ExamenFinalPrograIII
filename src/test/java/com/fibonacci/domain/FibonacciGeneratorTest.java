package com.fibonacci.domain;

import com.beesion.ms.fibonacci.domain.FibonacciGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas del Generador de Fibonacci")
class FibonacciGeneratorTest {

    @Test
    @DisplayName("Debería generar secuencia correcta con firma [0,1] y n=9")
    void shouldGenerateCorrectSequenceWithDefaultSignature() {
        int[] signature = {0, 1};
        int n = 9;
        int[] expected = {0, 1, 1, 2, 3, 5, 8, 13, 21};

        int[] result = FibonacciGenerator.generateSequence(signature, n);

        assertArrayEquals(expected, result);
    }

    @Test
    @DisplayName("Debería devolver array vacío cuando n=0")
    void shouldReturnEmptyArrayWhenNIsZero() {
        int[] signature = {0, 1};
        int n = 0;

        int[] result = FibonacciGenerator.generateSequence(signature, n);

        assertEquals(0, result.length);
    }

    @Test
    @DisplayName("Debería devolver primer elemento cuando n=1")
    void shouldReturnFirstElementWhenNIsOne() {
        int[] signature = {0, 1};
        int n = 1;
        int[] expected = {0};

        int[] result = FibonacciGenerator.generateSequence(signature, n);

        assertArrayEquals(expected, result);
    }

    @Test
    @DisplayName("Debería devolver firma completa cuando n=2")
    void shouldReturnFullSignatureWhenNIsTwo() {
        int[] signature = {0, 1};
        int n = 2;

        int[] result = FibonacciGenerator.generateSequence(signature, n);

        assertArrayEquals(signature, result);
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando n es negativo")
    void shouldThrowExceptionWhenNIsNegative() {
        int[] signature = {0, 1};
        int n = -1;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FibonacciGenerator.generateSequence(signature, n);
        });

        assertEquals("n debe ser un número no negativo", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando la firma es nula")
    void shouldThrowExceptionWhenSignatureIsNull() {
        int[] signature = null;
        int n = 5;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FibonacciGenerator.generateSequence(signature, n);
        });

        assertEquals("La firma debe contener exactamente 2 elementos", exception.getMessage());
    }

    @ParameterizedTest(name = "Firma {0}, n={1}, esperado {2}")
    @MethodSource("provideDifferentSignatures")
    @DisplayName("Debería generar secuencias correctas con diferentes firmas")
    void shouldGenerateCorrectSequencesWithDifferentSignatures(int[] signature, int n, int[] expected) {
        int[] result = FibonacciGenerator.generateSequence(signature, n);
        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> provideDifferentSignatures() {
        return Stream.of(
                Arguments.of(new int[]{2, 1}, 5, new int[]{2, 1, 3, 4, 7}),
                Arguments.of(new int[]{1, 1}, 6, new int[]{1, 1, 2, 3, 5, 8}),
                Arguments.of(new int[]{3, 2}, 4, new int[]{3, 2, 5, 7})
        );
    }
}

