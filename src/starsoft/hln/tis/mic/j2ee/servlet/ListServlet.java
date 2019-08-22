package starsoft.hln.tis.mic.j2ee.servlet;

import com.google.gson.Gson;
import starsoft.hln.tis.mic.entity.FileItem;
import starsoft.hln.tis.mic.config.AppConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListServlet extends HttpServlet {

    private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = AppConfig.get("if_base_path");

        File pathFile = new File(path);

        Gson gson = new Gson();
        List<FileItem> fileItems = listAllWithSubDirectory("", pathFile);

        Map<String, Object>  responseJsonMap = new HashMap<>();
        responseJsonMap.put("base_path", path);
        responseJsonMap.put("root_items", fileItems);

        String json = gson.toJson(responseJsonMap);

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(json);
    }


    private static List<FileItem> listAllWithSubDirectory(String startPoint, File targetPath) {
        List<FileItem> result = new ArrayList<>();

        File[] list = targetPath.listFiles();
        if (list == null) {
            return result;
        }

        for (File file : list) {
            FileItem fileItem = new FileItem();
            fileItem.setName(file.getName());

            if (file.isFile()) {
                fileItem.setType("file");
                fileItem.setRelativePath(startPoint + File.separator + file.getName());
                fileItem.setLastModified(SDF.format(file.lastModified()));
            }

            if (file.isDirectory()) {
                fileItem.setType("directory");
                fileItem.setSub(listAllWithSubDirectory(startPoint + File.separator + file.getName(), file));
            }
            result.add(fileItem);
        }

        return result;
    }
}
