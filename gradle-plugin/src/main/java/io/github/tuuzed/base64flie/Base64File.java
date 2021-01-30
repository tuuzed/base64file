package io.github.tuuzed.base64flie;

import java.io.File;
import java.io.IOException;

public class Base64File {

    public String from(File file) throws IOException {
        return from(file, false);
    }

    public String from(File file, boolean zip) throws IOException {
        return from(Utils.encode(file, zip));
    }

    private String from(String[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("new java.lang.String[]{\n");
        boolean first = true;
        for (String it : array) {
            if (!first) {
                sb.append(",\n");
            }
            sb.append("\"");
            sb.append(it);
            sb.append("\"");
            first = false;
        }
        sb.append("\n}");
        return sb.toString();
    }

}
