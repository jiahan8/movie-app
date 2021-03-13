package com.jiahan.fave.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MathHelper {
    private MathHelper() {

    }

    public static int compare(long x, long y) {
        return Long.compare(x, y);
    }

    public static int[] randomize(int total, int n) {
        List<Integer> number = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            number.add(i);
        }
        Collections.shuffle(number);
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = number.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}