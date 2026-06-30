-- AI-Enabled FinTech B2B Invoice Processing System
-- Database schema setup
-- Run: mysql -u <user> -p < schema.sql

CREATE DATABASE IF NOT EXISTS grey_goose
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE grey_goose;

-- -------------------------------------------------------
-- winter_internship
-- Primary invoice ledger (SAP FI-AR style export).
-- Served by GET /DataRetrieval?page=N
-- ML model writes back to predicted_payment_date.
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS winter_internship (
    sl_no                  INT          NOT NULL AUTO_INCREMENT,
    business_code          VARCHAR(20),
    cust_number            INT,
    name_customer          VARCHAR(100),
    clear_date             DATE,
    buisness_year          VARCHAR(4),
    doc_id                 VARCHAR(50),
    posting_date           DATE,
    document_create_date   DATE,
    due_in_date            DATE,
    invoice_currency       VARCHAR(10),
    document_type          VARCHAR(10),
    posting_id             INT,
    total_open_amount      VARCHAR(30),
    baseline_create_date   DATE,
    cust_payment_terms     VARCHAR(50),
    invoice_id             BIGINT,
    predicted_payment_date DATE,
    notes                  TEXT,
    PRIMARY KEY (sl_no)
);

-- -------------------------------------------------------
-- Sample data — remove before loading the real dataset
-- -------------------------------------------------------
INSERT INTO winter_internship
    (business_code, cust_number, name_customer, clear_date, buisness_year, doc_id,
     posting_date, document_create_date, due_in_date, invoice_currency,
     document_type, posting_id, total_open_amount, baseline_create_date,
     cust_payment_terms, invoice_id, predicted_payment_date, notes)
VALUES
    ('U001', 2523532, 'Andrea James',  '2021-05-02', '2021', '73457346735',
     '2021-01-15', '2021-01-14', '2021-01-23', 'USD',
     'RV', 1, '122870.00', '2021-01-15', 'NT30', 73457346735, NULL,       'Lorem ipsum dolor sit...'),

    ('U001', 3523312, 'Jessica Joe',   NULL,         '2021', '54723243652',
     '2020-12-20', '2020-12-19', '2021-01-15', 'USD',
     'RV', 1, '1870.00',   '2020-12-20', 'NT30', 54723243652, NULL,       'Lorem ipsum dolor sit...'),

    ('U002', 9088757, 'Teresa Hawkins','2021-04-10', '2021', '76531467265',
     '2020-12-10', '2020-12-09', '2021-01-05', 'USD',
     'RV', 2, '22870.00',  '2020-12-10', 'NT30', 76531467265, '2021-06-03','Lorem ipsum dolor sit...'),

    ('U001', 4523426, 'Dominic White', NULL,         '2021', '57635634855',
     '2020-12-25', '2020-12-24', '2021-03-22', 'USD',
     'RV', 1, '55700.00',  '2020-12-25', 'NT60', 57635634855, '2021-05-31','Lorem ipsum dolor sit...');
