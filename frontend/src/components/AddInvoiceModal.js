import React, { useState } from 'react';

const EMPTY = { custName: '', custId: '', invNo: '', invAmt: '', dueDa: '', notes: '' };

export default function AddInvoiceModal({ onSave, onClose }) {
  const [form, setForm]   = useState(EMPTY);
  const [error, setError] = useState('');

  const set = (k, v) => setForm(f => ({ ...f, [k]: v }));

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!form.custName || !form.invNo || !form.invAmt || !form.dueDa) {
      setError('Customer Name, Invoice #, Amount and Due Date are required.');
      return;
    }
    try {
      await onSave(form);
    } catch {
      setError('Failed to save. Check that the backend is running.');
    }
  };

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal" onClick={e => e.stopPropagation()}>
        <h2>Add Invoice</h2>
        {error && <div className="error-bar">{error}</div>}
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Customer Name</label>
            <input value={form.custName} onChange={e => set('custName', e.target.value)} />
          </div>
          <div className="form-group">
            <label>Customer ID</label>
            <input value={form.custId} onChange={e => set('custId', e.target.value)} />
          </div>
          <div className="form-group">
            <label>Invoice Number</label>
            <input value={form.invNo} onChange={e => set('invNo', e.target.value)} />
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
            <button type="submit" className="btn btn-add">Save</button>
          </div>
        </form>
      </div>
    </div>
  );
}
