package ru.kpfu.itis.group11501.utkin.Helpers;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by user on 18.11.2016.
 */
public class AddPhoto {
    public static void addPhoto(Part filePart, String fileName) throws IOException {
        File file = new File("D:/mafiasite/mafiasite/web/images/" + fileName);
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        InputStream filecontent = filePart.getInputStream();
        int read;
        final byte[] bytes = new byte[1024];

        while ((read = filecontent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        out.close();
        filecontent.close();
    }
}
