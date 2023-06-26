package com.itsnake.uitls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

    public static List<Integer> StringToIntList(String str) {
        String[] arr = str.split(",");
        int[] arr1 =  Arrays.asList(arr).stream().mapToInt(Integer::parseInt).toArray();
        List<Integer> list = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        return list;
    }
}

