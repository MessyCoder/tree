package starsoft.hln.tis.mic.s02_download;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public interface DownloadFileEditor {

    void setInputDataFile(File inputDataFile);
    void saveResultTo(OutputStream outputStream)throws IOException;
    String getFileExtension();
}
