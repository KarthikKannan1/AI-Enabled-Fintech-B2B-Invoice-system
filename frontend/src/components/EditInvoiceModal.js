import React, { useState } from 'react';

export default function EditInvoiceModal({ invoice, onSave, onClose }) {
  const [form, setForm] = useState({
    slNo:     invoice.sl_no,
    custName: invoice.name_customer  || '',
    invAmt:   invoice.total_open_amount || '',
    dueDa:    invoice.due_in_date    ? invoice.due_in_date.slice(0, 10) : '',
    notes:    invoice.notes          || '',
  });
  const [error, setError] = useState('');

  const set = (k, v) => setForm(f => ({ ...f, [k]: v }));

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await onSave(form);
    } catch {
      setError('Failed to save. Check that the backend is running.');
    }
  };

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal" onClick={e => e.stopPropagation()}>
        <h2>Edit Invoice #{invoice.invoice_id}</h2>
        {error && <div className="error-bar">{error}</div>}
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Customer Name</label>
            <input value={form.custName} onChange={e => set('custName', e.target.value)} />
          </div>
          <div className="form-group">
            <label>Invoice Amount</label>
            <input value={form.invAmt} onChange={e => set('invAmt', e.target.value)} />
          </div>
          <div className="form-group">
            <label>Due Date</label>
            <input type="date" value={form.dueDa} onChange={e => set('dueDa', e.target.value)} />
          </div>
          <div className="form-group">
            <label>Notes</label>
            <textarea value={form.notes} onChange={e => set('notes', e.target.value)} />
          </div>
          <div className="modal-actions">
            <button type="button" className="btn-cancel" onClick={onClose}>Cancel</button>
            <button type="submit" className="btn btn-add">Update</button>
          </div>
        </form>
      </div>
    </div>
  );
}
