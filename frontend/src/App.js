import React, { useState, useEffect, useCallback } from 'react';
import Header from './components/Header';
import InvoiceTable from './components/InvoiceTable';
import AddInvoiceModal from './components/AddInvoiceModal';
import EditInvoiceModal from './components/EditInvoiceModal';
import { fetchInvoices, fetchCount, addInvoice, editInvoice, deleteInvoice } from './services/api';

export default function App() {
  const [invoices,    setInvoices]    = useState([]);
  const [totalPages,  setTotalPages]  = useState(1);
  const [currentPage, setCurrentPage] = useState(1);
  const [search,      setSearch]      = useState('');
  const [selected,    setSelected]    = useState(null);   // row checkbox selection
  const [showAdd,     setShowAdd]     = useState(false);
  const [showEdit,    setShowEdit]    = useState(false);
  const [loading,     setLoading]     = useState(false);
  const [error,       setError]       = useState('');

  const load = useCallback(async (page) => {
    setLoading(true);
    setError('');
    try {
      const [data, meta] = await Promise.all([fetchInvoices(page), fetchCount()]);
      setInvoices(data);
      setTotalPages(meta.pages || 1);
    } catch (e) {
      setError('Could not connect to the backend. Make sure Tomcat is running on port 8080.');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => { load(currentPage); }, [currentPage, load]);

  const handlePageChange = (p) => { setSelected(null); setCurrentPage(p); };

  const handleAdd = async (formData) => {
    await addInvoice(formData);
    setShowAdd(false);
    load(currentPage);
  };

  const handleEdit = async (formData) => {
    await editInvoice(formData);
    setShowEdit(false);
    setSelected(null);
    load(currentPage);
  };

  const handleDelete = async () => {
    if (!selected) return;
    if (!window.confirm(`Delete invoice #${selected.invoice_id}?`)) return;
    await deleteInvoice(selected.sl_no);
    setSelected(null);
    load(currentPage);
  };

  const displayed = search
    ? invoices.filter(inv => String(inv.invoice_id).includes(search))
    : invoices;

  return (
    <>
      <Header />
      <main className="main-content">
        <div className="page-title">Invoice List</div>

        {error && <div className="error-bar">{error}</div>}

        <div className="toolbar">
          <button className="btn btn-add" onClick={() => setShowAdd(true)}>+ Add</button>
          <button
            className="btn btn-edit"
            disabled={!selected}
            onClick={() => setShowEdit(true)}
          >
            Edit
          </button>
          <button
            className="btn btn-delete"
            disabled={!selected}
            onClick={handleDelete}
          >
            &minus; Delete
          </button>
          <div className="toolbar-right">
            <input
              className="search-input"
              placeholder="Search by Invoice Number"
              value={search}
              onChange={e => setSearch(e.target.value)}
            />
          </div>
        </div>

        {loading
          ? <div className="status-bar">Loading invoices...</div>
          : <InvoiceTable
              invoices={displayed}
              selected={selected}
              onSelect={setSelected}
            />
        }

        <div className="pagination">
          <button onClick={() => handlePageChange(currentPage - 1)} disabled={currentPage === 1}>
            &#8592;
          </button>
          <span>Page {currentPage} of {totalPages}</span>
          <button onClick={() => handlePageChange(currentPage + 1)} disabled={currentPage === totalPages}>
            &#8594;
          </button>
        </div>
      </main>

      {showAdd  && <AddInvoiceModal  onSave={handleAdd}                              onClose={() => setShowAdd(false)}  />}
      {showEdit && <EditInvoiceModal invoice={selected} onSave={handleEdit}          onClose={() => setShowEdit(false)} />}
    </>
  );
}
