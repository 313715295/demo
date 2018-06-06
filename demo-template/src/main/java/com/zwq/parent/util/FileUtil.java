package com.zwq.parent.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * created by zwq on 2018/5/19
 */

public class FileUtil {

    public static void uploadFile(byte[] file, String targetFilePath, String fileName) throws IOException {
        File targetFile = new File(targetFilePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(targetFilePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
