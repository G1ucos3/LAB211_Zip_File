package repository;

/**
 *
 * @author ASUS
 */
public interface IZip {

    void compressTo(String pathSrc, String fileZipName, String pathCompress);

    void extractTo(String pathZipFile, String pathExtract);
    
}
