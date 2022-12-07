package cn.auroralab.devtrack.util;

import org.springframework.core.io.DefaultResourceLoader;

import java.io.*;

/**
 * 资源文件加载器。
 *
 * @author Guanyu Hu
 * @since 2022-10-22
 */
public class ResourceFileLoader {
    /**
     * 读取resource资源文件夹下的文件内容。
     *
     * @param classPath resources资源文件夹下的相对路径。
     * @author Guanyu Hu
     * @since 2022-10-22
     */
    public static String readFile(String classPath) {
        StringBuilder res = new StringBuilder();
        try {
            InputStream is = new DefaultResourceLoader().getResource("classpath:" + classPath).getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data;
            int lineCount = 0;
            while ((data = br.readLine()) != null) {
                if (lineCount > 0) res.append(System.lineSeparator());
                res.append(data);
                lineCount++;
            }
            br.close();
            isr.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    /**
     * 将resource资源文件夹下的图片转换为字节流。
     *
     * @param classPath resources资源文件夹下的相对路径。
     * @return 字节流。
     * @author Guanyu Hu
     * @since 2022-11-02
     */
    public static byte[] imageToBytes(String classPath) {
        byte[] ans = null;
        try {
            File image = new DefaultResourceLoader().getResource("classpath:" + classPath).getFile();
            try (
                    FileInputStream fis = new FileInputStream(image);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream(1024)
            ) {
                byte[] bytes = new byte[1024];
                int n;
                while ((n = fis.read(bytes)) != -1)
                    bos.write(bytes, 0, n);
                ans = bos.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
