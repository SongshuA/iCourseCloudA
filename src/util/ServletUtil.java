package util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ServletUtil {
    static void sendError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);

        request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
    }
}
