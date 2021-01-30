package base64file.gradle.plugin;

import base64file.Base64FileUtils;

import java.io.File;
import java.io.IOException;

public class Base64File {

    public String from(File file) throws IOException {
        return from(file, false);
    }

    public String from(File file, boolean zip) throws IOException {
        String[] array = Base64FileUtils.encode(file, zip);
        return from(array);
    }

    private String from(String[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("new java.lang.String[]{");
        boolean first = true;
        for (String it : array) {
            if (!first) {
                sb.append(",");
            }
            sb.append("\"");
            sb.append(it);
            sb.append("\"");
            first = false;
        }
        sb.append("}");
        return sb.toString();
    }
}
