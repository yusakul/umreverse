package com.umeng.commonsdk.internal.crash;

import java.io.*;

public class a
{
    public static String a(final Throwable t) {
        if (t == null) {
            return null;
        }
        String string = null;
        try {
            final StringWriter out = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(out);
            t.printStackTrace(printWriter);
            for (Throwable t2 = t.getCause(); t2 != null; t2 = t2.getCause()) {
                t2.printStackTrace(printWriter);
            }
            string = out.toString();
            printWriter.close();
            out.close();
        }
        catch (Exception ex) {}
        return string;
    }
}
