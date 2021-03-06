package com.example.shoouji_tsyrdean.mandarin;

import java.util.Properties;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileInputStream;

import android.content.res.Resources;


import static com.example.shoouji_tsyrdean.mandarin.Conversion.gr2py;
import static com.example.shoouji_tsyrdean.mandarin.Conversion.py2gr;

public class Cedict {
    public static Properties entries;
    public static Properties fuzzyGR;
    
    static {
        try {
        	
        	entries = new Properties();
            fuzzyGR = new Properties();
            
            
            // InputStream inputStream = Cedict.class.getResourceAsStream("cedict.properties");
            // entries.load(inputStream);
            //entries.load(new InputStreamReader(new FileInputStream("assets/cedict.properties"), "UTF-8"));
            //fuzzyGR.load(new InputStreamReader(new FileInputStream("assets/fuzzygr.properties"), "UTF-8"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getEntry(int index) {
        return entries.getProperty(String.valueOf(index));
    }

    public static String[] findByFuzzy(String fuzzyInput) {
        String[] result = {"no matches"};
        
        String[] syllables = fuzzyInput.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < syllables.length; i++) {
            String tonePy = gr2py(syllables[i]);
            
            sb.append(py2gr(tonePy.substring(0, tonePy.length()-1) + "1"));
            sb.append("_");
        }

        String tonelessGR = sb.toString();
        tonelessGR = tonelessGR.substring(0, tonelessGR.length()-1);

        String entryIndicesStr = fuzzyGR.getProperty(tonelessGR);

        if (entryIndicesStr != null) {
            String[] entryIndices = entryIndicesStr.split(",");
            int numberOfMatches = entryIndices.length;
            result = new String[numberOfMatches];
            for (int i = 0; i < numberOfMatches; i++) {
                result[i] = getEntry(Integer.parseInt(entryIndices[i]));
            }
        }
        
        return result;
    }
}
