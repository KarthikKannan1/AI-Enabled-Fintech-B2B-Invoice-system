package highradius;

public class Response {
    private int    sl_no;
    private String business_code;
    private int    cust_number;
    private String name_customer;
    private String clear_date;
    private String buisness_year;
    private String doc_id;
    private String posting_date;
    private String document_create_date;
    private String due_in_date;
    private String invoice_currency;
    private String document_type;
    private int    posting_id;
    private String total_open_amount;
    private String baseline_create_date;
    private String cust_payment_terms;
    private int    invoice_id;
    private String predicted_payment_date;
    private String notes;

    public int getSl_no()                            { return sl_no; }
    public void setSl_no(int v)                      { this.sl_no = v; }

    public String getBusiness_code()                 { return business_code; }
    public void setBusiness_code(String v)           { this.business_code = v; }

    public int getCust_number()                      { return cust_number; }
    public void setCust_number(int v)                { this.cust_number = v; }

    public String getName_customer()                 { return name_customer; }
    public void setName_customer(String v)           { this.name_customer = v; }

    public String getClear_date()                    { return clear_date; }
    public void setClear_date(String v)              { this.clear_date = v; }

    public String getBuisness_year()                 { return buisness_year; }
    public void setBuisness_year(String v)           { this.buisness_year = v; }

    public String getDoc_id()                        { return doc_id; }
    public void setDoc_id(String v)                  { this.doc_id = v; }

    public String getPosting_date()                  { return posting_date; }
    public void setPosting_date(String v)            { this.posting_date = v; }

    public String getDocument_create_date()          { return document_create_date; }
    public void setDocument_create_date(String v)    { this.document_create_date = v; }

    public String getDue_in_date()                   { return due_in_date; }
    public void setDue_in_date(String v)             { this.due_in_date = v; }

    public String getInvoice_currency()              { return invoice_currency; }
    public void setInvoice_currency(String v)        { this.invoice_currency = v; }

    public String getDocument_type()                 { return document_type; }
    public void setDocument_type(String v)           { this.document_type = v; }

    public int getPosting_id()                       { return posting_id; }
    public void setPosting_id(int v)                 { this.posting_id = v; }

    public String getTotal_open_amount()             { return total_open_amount; }
    public void setTotal_open_amount(String v)       { this.total_open_amount = v; }

    public String getBaseline_create_date()          { return baseline_create_date; }
    public void setBaseline_create_date(String v)    { this.baseline_create_date = v; }

    public String getCust_payment_terms()            { return cust_payment_terms; }
    public void setCust_payment_terms(String v)      { this.cust_payment_terms = v; }

    public int getInvoice_id()                       { return invoice_id; }
    public void setInvoice_id(int v)                 { this.invoice_id = v; }

    public String getPredicted_payment_date()        { return predicted_payment_date; }
    public void setPredicted_payment_date(String v)  { this.predicted_payment_date = v; }

    public String getNotes()                         { return notes; }
    public void setNotes(String v)                   { this.notes = v; }
}
