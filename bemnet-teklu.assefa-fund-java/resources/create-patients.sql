CREATE TABLE PATIENTS (
    pat_num_HC BIGINT PRIMARY KEY,
    pat_lastname VARCHAR(255),
    pat_firstname VARCHAR(255),
    pat_address VARCHAR(255),
    pat_tel VARCHAR(20),
    pat_insurance_id INT,
    pat_subscription_date DATE
);
