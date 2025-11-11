# Bajaj Finserv Health| Qualifier 1| JAVA

# Duration: 1 Hour

## Task Overview

Build a Spring Boot app that:

- On startup, sends a POST request to generate a webhook.
- Based on the response, solves a SQL problem and stores the result.
- Sends the solution (a final SQL query) to the returned webhook URL using a JWT token.

## Step-by-Step Instructions

1. On app startup, send this POST request:

```
POST https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA
```
```
Body:
{
"name": "John Doe",
"regNo": "REG12347",
"email": "john@example.com"
}
```
2. You will receive:
- A ‘webhook‘ URL to submit your answer.
- An ‘accessToken‘ to use as JWT in the Authorization header.


3. # SQL Question – Background Tables & Problem Statement

## **1. DEPARTMENT Table**

| DEPARTMENT_ID | DEPARTMENT_NAME |
| ------------- | --------------- |
| 1             | HR              |
| 2             | Finance         |
| 3             | Engineering     |
| 4             | Sales           |
| 5             | Marketing       |
| 6             | IT              |

---

## **2. EMPLOYEE Table**

| EMP_ID | FIRST_NAME | LAST_NAME | DOB        | GENDER | DEPARTMENT |
| ------ | ---------- | --------- | ---------- | ------ | ---------- |
| 1      | John       | Williams  | 1980-05-15 | Male   | 3          |
| 2      | Sarah      | Johnson   | 1990-07-20 | Female | 2          |
| 3      | Michael    | Smith     | 1985-02-10 | Male   | 3          |
| 4      | Emily      | Brown     | 1992-11-30 | Female | 4          |
| 5      | David      | Jones     | 1988-09-05 | Male   | 5          |
| 6      | Olivia     | Davis     | 1995-04-12 | Female | 1          |
| 7      | James      | Wilson    | 1983-03-25 | Male   | 6          |
| 8      | Sophia     | Anderson  | 1991-08-17 | Female | 4          |
| 9      | Liam       | Miller    | 1979-12-01 | Male   | 1          |
| 10     | Emma       | Taylor    | 1993-06-28 | Female | 5          |

---

## **3. PAYMENTS Table**

| PAYMENT_ID | EMP_ID | AMOUNT   | PAYMENT_TIME            |
| ---------- | ------ | -------- | ----------------------- |
| 1          | 2      | 65784.00 | 2025-01-01 13:44:12.824 |
| 2          | 4      | 62736.00 | 2025-01-06 18:36:37.892 |
| 3          | 1      | 69437.00 | 2025-01-01 10:19:21.563 |
| 4          | 3      | 67183.00 | 2025-01-02 17:21:57.341 |
| 5          | 2      | 66273.00 | 2025-02-01 11:49:15.764 |
| 6          | 5      | 71475.00 | 2025-01-01 07:24:14.453 |
| 7          | 1      | 70837.00 | 2025-02-03 19:11:31.553 |
| 8          | 6      | 69628.00 | 2025-01-02 10:41:15.113 |
| 9          | 4      | 71876.00 | 2025-02-01 12:16:47.807 |
| 10         | 3      | 70098.00 | 2025-02-03 10:11:17.341 |
| 11         | 6      | 67827.00 | 2025-02-02 19:21:27.753 |
| 12         | 5      | 69871.00 | 2025-02-05 17:54:17.453 |
| 13         | 2      | 72984.00 | 2025-03-05 09:37:35.974 |
| 14         | 1      | 67982.00 | 2025-03-01 06:09:51.983 |
| 15         | 6      | 70198.00 | 2025-03-02 10:34:35.753 |
| 16         | 4      | 74998.00 | 2025-03-02 09:27:26.162 |

---

# **Table Descriptions**

### **DEPARTMENT**

* **DEPARTMENT_ID** (Primary Key)
* **DEPARTMENT_NAME**

### **EMPLOYEE**

* **EMP_ID** (Primary Key)
* **FIRST_NAME**
* **LAST_NAME**
* **DOB**
* **GENDER**
* **DEPARTMENT** (FK → DEPARTMENT_ID)

### **PAYMENTS**

* **PAYMENT_ID** (Primary Key)
* **EMP_ID** (FK → EMP_ID)
* **AMOUNT**
* **PAYMENT_TIME**

---

# **Problem Statement**

Find the **highest salary** that was credited to an employee **excluding transactions made on the 1st day of any month**. Along with the salary, you are also required to extract the employee data like name (combine first name and last name into one column), age and department who received this salary.

i.e Along with the salary amount, extract:

1. **NAME** → `FIRST_NAME + ' ' + LAST_NAME`
2. **AGE** → Age of the employee on the payment date
3. **DEPARTMENT_NAME** → From DEPARTMENT table

---

# **Output Format**

The output should contain:

| Column              | Description                                                 |
| ------------------- | ----------------------------------------------------------- |
| **SALARY**          | Highest salary credited **not on the 1st day** of any month |
| **NAME**            | Combined first and last name                                |
| **AGE**             | Employee age at time of payment                             |
| **DEPARTMENT_NAME** | Department of the employee                                  |

---


4. Submit your solution (the final SQL query) as:
POST https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA

```
Headers:
Authorization: <accessToken>
Content -Type: application/json
```
```
Body:
{
"finalQuery ": "YOUR_SQL_QUERY_HERE"
}
```


## Requirements

- Use ‘RestTemplate‘ or ‘WebClient‘ with Spring Boot.
- No controller/endpoint should trigger the flow
- Use JWT in the Authorization header for the second API

## Submission Checklist

Include:

- Include the public GitHub repo with:
    - Code
    - Final JAR output
    - RAW downloadable GitHub link to the JAR
- Public JAR file link (downloadable)

```
Format:
```
- GitHub: https://github.com/your-username/your-repo.git

## Good Luck!
