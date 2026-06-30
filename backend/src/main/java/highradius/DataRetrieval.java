package highradius;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

@WebServlet("/DataRetrieval")
public class DataRetrieval extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        try {
            int page   = Integer.parseInt(request.getParameter("page"));
            int offset = (page - 1) * 10;

            Connection con = DBConnection.createConnection();
            PreparedStatement st = con.prepareStatement(
                "SELECT sl_no, business_code, cust_number, name_customer, clear_date, " +
                "buisness_year, doc_id, posting_date, document_create_date, due_in_date, " +
                "invoice_currency, document_type, posting_id, total_open_amount, " +
                "baseline_create_date, cust_payment_terms, invoice_id, " +
                "predicted_payment_date, notes " +
                "FROM winter_internship LIMIT ?, 10"
            );
            st.setInt(1, offset);
            ResultSet rs = st.executeQuery();

            ArrayList<Response> results = new ArrayList<>();
            while (rs.next()) {
                Response r = new Response();
                r.setSl_no(rs.getInt("sl_no"));
                r.setBusiness_code(rs.getString("business_code"));
                r.setCust_number(rs.getInt("cust_number"));
                r.setName_customer(rs.getString("name_customer"));
                r.setClear_date(rs.getString("clear_date"));
                r.setBuisness_year(rs.getString("buisness_year"));
                r.setDoc_id(rs.getString("doc_id"));
                r.setPosting_date(rs.getString("posting_date"));
                r.setDocument_create_date(rs.getString("document_create_date"));
                r.setDue_in_date(rs.getString("due_in_date"));
                r.setInvoice_currency(rs.getString("invoice_currency"));
                r.setDocument_type(rs.getString("document_type"));
                r.setPosting_id(rs.getInt("posting_id"));
                r.setTotal_open_amount(rs.getString("total_open_amount"));
                r.setBaseline_create_date(rs.getString("baseline_create_date"));
                r.setCust_payment_terms(rs.getString("cust_payment_terms"));
                r.setInvoice_id(rs.getInt("invoice_id"));
                r.setPredicted_payment_date(rs.getString("predicted_payment_date"));
                r.setNotes(rs.getString("notes"));
                results.add(r);
            }

            rs.close(); st.close(); con.close();

            String json = new GsonBuilder().serializeNulls().create().toJson(results);
            response.getWriter().write(json);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
