package controller;

import model.Data;
import repository.ZipRepository;
import view.Menu;

/**
 *
 * @author ASUS
 */
public class ZipController extends Menu{
    static String[] mc = {"Compression", "Extraction", "Exit"};
    Data data;
    ZipRepository zr;
    
    public ZipController(){
        super("Zipper Program", mc);
        data = new Data();
        zr = new ZipRepository();
    }

    @Override
    public void execute(int choice) {
        switch(choice){
            case 1:
                System.out.println("---------- Compression --------");
                zr.compressTo(data.getPathSrc(), data.getFileZipName(), data.getPathCompress());
                break;
            case 2:
                System.out.println("---------- Extraction ---------");
                zr.extractTo(data.getPathZipFile(), data.getPathExtract());
                break;
            case 3:
                System.out.println("Closed..");
                System.exit(0);
            default:
                System.out.println("Choose again.");
                break;
        }
    }
    
    
}
