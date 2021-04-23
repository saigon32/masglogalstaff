<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Search Employee</title>
</head>
<body>
	<form role="form" class="form-inline">
		<div class="form-group">
			<label for="Search Employee">ID Employee</label> <input type="text"
				class="form-control" placeholder="Id Employee" name="search_employee">
			<button type="submit" class="btn btn-primary" onclick="/mgstaff/employeesList">Search Employee</button>
		</div>
	</form>
	<table class="table table-responsive table-striped">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Role Name</th>
				<th>Role Id</th>
				<th>Role Description</th>
				<th>Contract Type Name</th>
				<th>Hourly Salary</th>
				<th>Monthly Salary</th>
				<th>Salary</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${employeesList}" var="employee" varStatus="status">
				<tr>
					<td>${employee.id}</td>
					<td>${employee.name}</td>
					<td>${employee.roleName}</td>
					<td>${employee.roleId}</td>
					<td>${employee.roleDescription}</td>
					<td>${employee.contractTypeName}</td>
					<td>${employee.hourlySalary}</td>
					<td>${employee.monthlySalary}</td>
					<td>${employee.salary}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>