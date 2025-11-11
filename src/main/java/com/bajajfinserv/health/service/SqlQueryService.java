package com.bajajfinserv.health.service;

import org.springframework.stereotype.Service;

@Service
public class SqlQueryService {

    /**
     * Generates the SQL query to find the highest salary excluding payments on the 1st of any month
     * 
     * Problem: Find the highest salary that was credited to an employee excluding transactions 
     * made on the 1st day of any month. Extract employee name (first + last), age at payment date, 
     * and department name.
     * 
     * Expected output for the given data:
     * - SALARY: 74998.00
     * - NAME: Emily Brown
     * - AGE: 32 (age at 2025-03-02, DOB: 1992-11-30)
     * - DEPARTMENT_NAME: Sales
     */
    public String getSqlQuery() {
        return "SELECT " +
                "p.AMOUNT AS SALARY, " +
                "CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, " +
                "TIMESTAMPDIFF(YEAR, e.DOB, p.PAYMENT_TIME) AS AGE, " +
                "d.DEPARTMENT_NAME " +
                "FROM PAYMENTS p " +
                "INNER JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID " +
                "INNER JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID " +
                "WHERE DAY(p.PAYMENT_TIME) != 1 " +
                "ORDER BY p.AMOUNT DESC " +
                "LIMIT 1";
    }
}
