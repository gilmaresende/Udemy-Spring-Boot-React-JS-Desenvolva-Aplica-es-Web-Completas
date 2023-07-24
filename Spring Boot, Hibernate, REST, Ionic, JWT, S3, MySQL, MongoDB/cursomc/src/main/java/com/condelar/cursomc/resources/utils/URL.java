package com.condelar.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

//import java.util.Arrays;

//import java.util.stream.Collectors;

public class URL {

    public static List<Integer> decodeIntList(String s) {
        String[] vet = s.split(",");
        List<Integer> res = new ArrayList<>();
        for (String c : vet) {
            res.add(Integer.parseInt(c));
        }
        //return Arrays.asList(s.split(",")).stream().map(m-> Integer.parseInt(m)).collect(Collectors.toList());
        return res;

    }

    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
