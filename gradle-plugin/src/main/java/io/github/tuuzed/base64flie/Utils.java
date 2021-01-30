package io.github.tuuzed.base64flie;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

final class Utils {

    private static final Charset CHARSET = Charset.forName("ISO_8859_1");

    @NotNull
    public static byte[] decode(@NotNull String base64, boolean unzip) throws IOException {
        byte[] data = Base64.getDecoder().decode(base64.getBytes(CHARSET));
        if (unzip) {
            data = unzip(data);
        }
        return data;
    }

    @NotNull
    public static String[] encode(@NotNull File file, boolean zip) throws IOException {
        return encode(readBytes(file), zip);
    }

    @NotNull
    public static String[] encode(@NotNull byte[] data, boolean zip) throws IOException {
        if (zip) {
            data = zip(data);
        }
        final String base64 = new String(Base64.getEncoder().encode(data), CHARSET);
        final List<String> list = new ArrayList<>();
        final int chunkSize = 65534;
        StringBuilder tmp = new StringBuilder(chunkSize);
        for (int i = 1; i <= base64.length(); i++) {
            tmp.append(base64.charAt(i - 1));
            if (i % chunkSize == 0) {
                list.add(tmp.toString());
                tmp = new StringBuilder(chunkSize);
            }
        }
        if (tmp.length() != 0) {
            list.add(tmp.toString());
        }
        final String[] rst = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            rst[i] = list.get(i);
        }
        return rst;
    }

    @NotNull
    private static byte[] zip(@NotNull byte[] data) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        GZIPOutputStream zipStream = new GZIPOutputStream(buffer);
        zipStream.write(data);
        zipStream.close();
        return buffer.toByteArray();
    }

    @NotNull
    private static byte[] unzip(@NotNull byte[] bytes) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        GZIPInputStream unzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = unzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        unzip.close();
        return out.toByteArray();
    }

    @NotNull
    public static byte[] readBytes(@NotNull File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;
        byte[] buf = new byte[256];
        while ((len = fis.read(buf)) != -1) {
            bos.write(buf, 0, len);
        }
        fis.close();
        return bos.toByteArray();
    }

}
