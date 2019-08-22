package starsoft.hln.tis.mic.s02_download;

import starsoft.hln.tis.mic.config.AppConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DownloadForm {

    private static Map<String, DownloadFileEditor> DOWNLOADER = new ConcurrentHashMap<>();
    static {

        DOWNLOADER.put("1", new DownloadFileEditorOriginal());
        DOWNLOADER.put("2", new DownloadFileEditorExcel());
    }

    private String path;
    private String type;


    public boolean validate() {
        if (path == null || path.trim().isEmpty()) {
            //TODO error
            return false;
        }

        if (type == null || type.trim().isEmpty()) {
            //TODO error
            return false;
        }

        path = path.trim();
        type = type.trim();

        return true;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
        String basePath = AppConfig.get("if_base_path");

        if (basePath.charAt(basePath.length() -1) == '\\') {
            basePath = basePath.substring(0, basePath.length() - 2);
        }

        String fullPath = basePath + path;


        File file = new File(fullPath);
        if (!file.isFile()) {
            //TODO error
            return;
        }


        DownloadFileEditor editor = DOWNLOADER.get(type);
        if (editor == null) {
            //TODO error
            return;
        }

        editor.setInputDataFile(file);

        String fileName = file.getName() + "." + editor.getFileExtension();
        resp.setHeader("Content-Disposition","attachment;filename=\"" + fileName + "\"");

        editor.saveResultTo(resp.getOutputStream());

    }
}
