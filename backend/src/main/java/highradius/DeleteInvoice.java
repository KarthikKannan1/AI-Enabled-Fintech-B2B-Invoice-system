package highradius;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteInvoice")
public class DeleteInvoice extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");

        try {
            int slNo = Integer.parseInt(request.getParameter("sl_no"));

            Connection con = DBConnection.createConnection();
            PreparedStatement st = con.prepareStatement(
                "DELETE FROM winter_internship WHERE sl_no = ?"
            );
            st.setInt(1, slNo);
            st.executeUpdate();
            st.close();
            con.close();

            response.getWriter().write("{\"status\":\"ok\"}");

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }
}
