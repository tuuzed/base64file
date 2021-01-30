package base64file.gradle.plugin;

import base64file.Base64FileUtils;
import org.codehaus.groovy.runtime.InvokerHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Base64File {

    public String from(File file, boolean zip) throws IOException {
        String[] array = Base64FileUtils.encode(file, zip);
        List<String> list = new ArrayList<>(array.length);
        StringBuilder sb = new StringBuilder();
        sb.append("new java.lang.String[]{\n\"");
        sb.append(join(list, "\",\n\""));
        sb.append("\"\n}");
        return sb.toString();
    }

    private static String join(Iterable self, String separator) {
        StringBuilder buffer = new StringBuilder();
        boolean first = true;
        if (separator == null) {
            separator = "";
        }
        Object value;
        for (Iterator var4 = self.iterator(); var4.hasNext(); buffer.append(InvokerHelper.toString(value))) {
            value = var4.next();
            if (first) {
                first = false;
            } else {
                buffer.append(separator);
            }
        }
        return buffer.toString();
    }

}
