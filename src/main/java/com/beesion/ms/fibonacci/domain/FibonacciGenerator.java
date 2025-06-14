package com.beesion.ms.fibonacci.domain;

import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;

@Slf4j
public class FibonacciGenerator {

    public static int[] generateSequence(int[] signature, int n) {
        if (n == 0) {
            return new int[0];
        }

        if (signature == null || signature.length != 2) {
            throw new IllegalArgumentException("La firma debe contener exactamente 2 elementos");
        }

        if (n < 0) {
            throw new IllegalArgumentException("n debe ser un número no negativo");
        }

        // Si n es menor o igual a 2, devolvemos solo los primeros n elementos de la firma
        if (n <= 2) {
            return Arrays.copyOf(signature, n);
        }

        int[] sequence = new int[n];
        sequence[0] = signature[0];
        sequence[1] = signature[1];

        for (int i = 2; i < n; i++) {
            sequence[i] = sequence[i-1] + sequence[i-2];
        }

        return sequence;
    }
}

