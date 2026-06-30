package highradius;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InvoiceCount")
public class InvoiceCount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        try {
            Connection con = DBConnection.createConnection();
            Statement  st  = con.createStatement();
            ResultSet  rs  = st.executeQuery("SELECT COUNT(*) AS total FROM winter_internship");

            int total = 0;
            if (rs.next()) total = rs.getInt("total");

            rs.close(); st.close(); con.close();

            int totalPages = (int) Math.ceil(total / 10.0);
            response.getWriter().write("{\"total\":" + total + ",\"pages\":" + totalPages + "}");

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }
}
