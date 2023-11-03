package repository;

import dataAccess.ZipDAO;

/**
 *
 * @author ASUS
 */
public class ZipRepository implements IZip {
    @Override
    public void compressTo(String pathSrc, String fileZipName, String pathCompress){
        if(!ZipDAO.Instance().compressTo(pathSrc, fileZipName, pathCompress)){
            System.out.println("Fail.");
        }
        else{
            System.out.println("Successfully.");
        }
    }
    
    @Override
    public void extractTo(String pathZipFile, String pathExtract){
        if(!ZipDAO.Instance().extractTo(pathZipFile, pathExtract)){
            System.out.println("Fail.");
        }
        else{
            System.out.println("Successfully.");
        }
    }
}
