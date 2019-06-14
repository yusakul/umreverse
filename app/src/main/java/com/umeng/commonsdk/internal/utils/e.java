package com.umeng.commonsdk.internal.utils;

import java.util.*;
import java.io.*;

public class e
{
    public ArrayList a(final a a) {
        final ArrayList<String> list = new ArrayList<String>();
        Process exec;
        try {
            exec = Runtime.getRuntime().exec(a.b);
        }
        catch (Exception ex) {
            return null;
        }
        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(exec.getOutputStream()));
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        }
        catch (Exception ex2) {}
        return list;
    }
    
    public enum a
    {
        a(new String[] { "/system/xbin/which", "su" });
        
        String[] b;
        
        private a(final String[] b) {
            this.b = b;
        }
    }
}
