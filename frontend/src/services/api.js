const BASE = 'http://localhost:8080/invoice-system';

export async function fetchInvoices(page) {
  const res = await fetch(`${BASE}/DataRetrieval?page=${page}`);
  if (!res.ok) throw new Error('Failed to fetch invoices');
  return res.json();
}

export async function fetchCount() {
  const res = await fetch(`${BASE}/InvoiceCount`);
  if (!res.ok) throw new Error('Failed to fetch count');
  return res.json();
}

export async function addInvoice({ custName, custId, invNo, invAmt, dueDa, notes }) {
  const body = new URLSearchParams({ custName, custId, invNo, invAmt, dueDa, not: notes });
  const res = await fetch(`${BASE}/AddForm`, { method: 'POST', body });
  if (!res.ok) throw new Error('Failed to add invoice');
}

export async function editInvoice({ slNo, custName, invAmt, dueDa, notes }) {
  const body = new URLSearchParams({ sl_no: slNo, custName, invAmt, dueDa, not: notes });
  const res = await fetch(`${BASE}/EditInvoice`, { method: 'POST', body });
  if (!res.ok) throw new Error('Failed to edit invoice');
}

export async function deleteInvoice(slNo) {
  const body = new URLSearchParams({ sl_no: slNo });
  const res = await fetch(`${BASE}/DeleteInvoice`, { method: 'POST', body });
  if (!res.ok) throw new Error('Failed to delete invoice');
}
