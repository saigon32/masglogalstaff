# masglogalstaff
proyecto masglogalstaff practica proceso de ingreso a la compañia: masglobal
Analista: Andres Jurado

Create a simple web application following the next steps:
1. Create a data access layer that consumes the following API (that you can test through swagger) as your data repository.
2. Create a Business Logic layer so you can retrieve the employees’ information including a calculated Annual Salary following these rules:
- Create your DTO (Data Transfer Object) Classes depending on the type of Contract that a given employee has (Hourly or Monthly).
- Make use of a simple Factory Method to create the concrete classes so you can calculate the salary depending on the type of contract.
- Use the builder pattern, lambdas, functional interfaces or Streams to simplify your algorithm.
- Employees can have to 2 types of Contracts: Hourly Salary Contract and Monthly Salary Contract.
● For Hourly Salary Employees the Annual Salary is:
120 * HourlySalary * 12
● For Monthly Salary Employees the Annual Salary is:
MonthtlySalary * 12
3. Create a “WEB SERVICE”/API that can return information for a given employee by it’s ID or multiple employees.
4. Create a web page (view) using the following guidelines: You can use the front-end technologies you are familiar with (JSP, JavaScript, JQuery, HTML, etc.)
- The view must contain a textbox, so the user can type the id of an employee.
- The view must contain a Get Employees button.
- If the textbox is empty, when the Get Employees button is clicked, retrieve the information for all the employees including the calculated Annual Salaries by calling your API.
- If the textbox has the id of a given employee, retrieve only the information for that employee including the calculated Annual Salary by calling your API.
- Information must be displayed in a table and can be manually styled or using Bootstrap.

