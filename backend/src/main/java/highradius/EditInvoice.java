package highradius;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditInvoice")
public class EditInvoice extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");

        try {
            int    slNo      = Integer.parseInt(request.getParameter("sl_no"));
            String custName  = request.getParameter("custName");
            String invoiceAmt = request.getParameter("invAmt");
            String dueDate   = request.getParameter("dueDa");
            String notes     = request.getParameter("not");

            Connection con = DBConnection.createConnection();
            PreparedStatement st = con.prepareStatement(
                "UPDATE winter_internship " +
                "SET name_customer = ?, total_open_amount = ?, due_in_date = ?, notes = ? " +
                "WHERE sl_no = ?"
            );
            st.setString(1, custName);
            st.setString(2, invoiceAmt);
            st.setString(3, dueDate);
            st.setString(4, notes);
            st.setInt(5, slNo);
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
