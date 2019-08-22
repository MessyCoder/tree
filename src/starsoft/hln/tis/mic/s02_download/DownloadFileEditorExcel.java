package starsoft.hln.tis.mic.s02_download;

import java.io.IOException;
import java.io.OutputStream;

public class DownloadFileEditorExcel extends DownloadFileEditorOriginal {


    @Override
    public void saveResultTo(OutputStream outputStream) throws IOException {

    }


    @Override
    public String getFileExtension() {
        return ".xlsx";
    }
}
