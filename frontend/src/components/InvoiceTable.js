import React from 'react';

function isOverdue(dueDate) {
  if (!dueDate) return false;
  return new Date(dueDate) < new Date();
}

function fmt(dateStr) {
  if (!dateStr) return '--';
  const d = new Date(dateStr);
  return isNaN(d) ? dateStr : d.toLocaleDateString('en-GB', { day: '2-digit', month: 'short', year: 'numeric' });
}

export default function InvoiceTable({ invoices, selected, onSelect }) {
  if (!invoices.length) {
    return <div className="status-bar">No invoices found.</div>;
  }

  return (
    <div className="table-wrapper">
      <table>
        <thead>
          <tr>
            <th></th>
            <th>Customer Name</th>
            <th>Customer #</th>
            <th>Invoice #</th>
            <th>Invoice Amount</th>
            <th>Due Date</th>
            <th>Predicted Payment Date</th>
            <th>Notes</th>
          </tr>
        </thead>
        <tbody>
          {invoices.map(inv => {
            const overdue = isOverdue(inv.due_in_date) && !inv.clear_date;
            const isSelected = selected && selected.sl_no === inv.sl_no;
            return (
              <tr key={inv.sl_no} className={overdue ? 'overdue' : ''}>
                <td>
                  <input
                    type="checkbox"
                    className="row-checkbox"
                    checked={isSelected}
                    onChange={() => onSelect(isSelected ? null : inv)}
                  />
                </td>
                <td>{inv.name_customer || '--'}</td>
                <td>{inv.cust_number}</td>
                <td>{inv.invoice_id}</td>
                <td>{inv.total_open_amount}</td>
                <td>{fmt(inv.due_in_date)}</td>
                <td className={`predicted${inv.predicted_payment_date ? '' : ' empty'}`}>
                  {fmt(inv.predicted_payment_date)}
                </td>
                <td>{inv.notes || '--'}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}
