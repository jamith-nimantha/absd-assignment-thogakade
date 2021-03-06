package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Item")
public class Item extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("_METHOD").equals("POST")) {

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String desc = request.getParameter("desc");
            String price = request.getParameter("price");

            String[] lst = {id,name,desc,price};

            if (!(id.isEmpty() && name.isEmpty() && desc.isEmpty() && price.isEmpty())) { // check parameters are not empty
//                for (int i=0;i<lst.length;i++){
//                    System.out.println(lst[i]);
//                }
                request.setAttribute("list",lst);    // set attribute "list" so it can be added to the item list in dashboard.jsp
                request.getRequestDispatcher("dashboard.jsp").forward(request,response);
            } else {
                request.getRequestDispatcher("dashboard.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lst = request.getParameter("list");
        String[] list = lst.split(",");

        request.setAttribute("list",list);
        request.getRequestDispatcher("viewitem.jsp").forward(request,response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String price = req.getParameter("price");

        String li =  req.getParameter("upList");
        String [] l = li.split(",");

        String[] lst = {id,name,desc,price};

        if (!(id.isEmpty() && name.isEmpty() && desc.isEmpty() && price.isEmpty())) { // check parameters are not empty

            req.setAttribute("upLst",l);
            req.setAttribute("updatedList",lst);    // set attribute "list" so it can be added to the item list in dashboard.jsp
            req.getRequestDispatcher("dashboard.jsp").forward(req,resp);
        } else {
            req.getRequestDispatcher("dashboard.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String list = req.getParameter("list");
        String[] lst = list.split(",");

        req.setAttribute("delLst", lst);
        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.service(req,resp);

        if (req.getParameter("_METHOD").equals("PUT")){
            doPut(req,resp);
        }

        if (req.getParameter("_METHOD").equals("DELETE")){
            doDelete(req,resp);
        }


    }
}
