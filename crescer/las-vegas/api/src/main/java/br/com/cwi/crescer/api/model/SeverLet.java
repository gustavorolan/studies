package br.com.cwi.crescer.api.model;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public class SeverLet {
    @WebServlet(name = "Servletmodel", value = "/Servletmodel")
    public class ServletModel extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) {
            //doGet
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) {
            //doPost
        }
    }
}
