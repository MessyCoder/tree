package starsoft.hln.tis.mic.s02_download;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        String type = req.getParameter("type");

        DownloadForm downloadForm = new DownloadForm();

        downloadForm.setPath(path);
        downloadForm.setType(type);

        if(!downloadForm.validate()) {
            //todo error handling.
            return;
        }

        downloadForm.process(req, resp);
    }
}
