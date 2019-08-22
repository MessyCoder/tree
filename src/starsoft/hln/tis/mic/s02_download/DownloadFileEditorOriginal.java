package starsoft.hln.tis.mic.s02_download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadFileEditorOriginal implements DownloadFileEditor{

    File targetFile;


    @Override
    public void setInputDataFile(File inputDataFile) {
        this.targetFile = inputDataFile;
    }


    @Override
    public void saveResultTo(OutputStream outputStream) throws IOException{
        byte[] buffer = new byte[2048];
        try(FileInputStream fis = new FileInputStream(targetFile)) {

            int read;
            while((read = fis.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
        }
    }

    @Override
    public String getFileExtension() {
        return "";
    }
}
