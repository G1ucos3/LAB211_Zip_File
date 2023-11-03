package dataAccess;

import common.Validate;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import model.Data;

/**
 *
 * @author ASUS
 */
public class ZipDAO {

    private static ZipDAO instance = null;
    Validate validate = new Validate();
    Data data = new Data();

    public static ZipDAO Instance() {
        if (instance == null) {
            synchronized (ZipDAO.class) {
                if (instance == null) {
                    instance = new ZipDAO();
                }
            }
        }
        return instance;
    }

    public boolean compressTo(String pathSrc, String fileZipName, String pathCompress) {
        pathSrc = validate.checkString("Enter Source Folder");
        pathCompress = validate.checkString("Enter Destination Folder");
        fileZipName = validate.checkString("Enter Name");
        try {
            String pathFos = pathCompress + "\\" + fileZipName + ".zip";
            FileOutputStream fos = new FileOutputStream(pathFos);
            ZipOutputStream zos = new ZipOutputStream(fos);
            File f = new File(pathSrc);
            File[] files = f.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    FileInputStream fis = new FileInputStream(file);
                    System.out.println("File name: " + file.getName());
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                    fis.close();
                }
            }
            zos.close();
            fos.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean extractTo(String pathZipFile, String pathExtract) {
        pathZipFile = validate.checkString("Enter Source File");
        pathExtract = validate.checkString("Enter Destination Folder");
        try {
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(pathZipFile);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                System.out.println("File name: " + fileName);
                File file = new File(pathExtract + "\\" + fileName);
                FileOutputStream fos = new FileOutputStream(file);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
