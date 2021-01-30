package io.github.tuuzed.base64flie;

import java.io.File;
import java.io.IOException;

public class Base64File {

    public String from(File file) throws IOException {
        return from(file, false);
    }

    public String from(File file, boolean zip) throws IOException {
        String[] array = Utils.encode(file, zip);
        return from(array);
    }

    private String from(String[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("new java.lang.StringBuilder()");
        for (String it : array) {
            sb.append("\n");
            sb.append(".append(\"").append(it).append("\")");
        }
        sb.append("\n.toString()");
        return sb.toString();
    }

}
