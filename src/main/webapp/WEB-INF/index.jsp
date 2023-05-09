<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title> Ninja Gold </title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body>
   	<main class="container mt-5">
   		<div>
   			<h1>Your Gold:<span class="border border-1 rounded ms-2 px-2"><c:out value="${gold}"/></span></h1>
   			<div class="row mt-5">
   				<form class="col-3 rounded p-2" method="POST" action="/farm">
	   				<h2>Farm</h2>
	   				<h4>(Earns 10-20 gold)</h4>
	   				<button class="btn btn-primary">Find Gold!</button>
	   			</form>
	   			<form class="col-3 rounded p-2" method="POST" action="/cave">
	   				<h2>Cave</h2>
	   				<h4>(Earns 5-10 gold)</h4>
	   				<button class="btn btn-primary">Find Gold!</button>
	   			</form>
	   			<form class="col-3 rounded p-2" method="POST" action="/house">
	   				<h2>House</h2>
	   				<h4>(Earns 2-5 gold)</h4>
	   				<button class="btn btn-primary">Find Gold!</button>
	   			</form>
	   			<form class="col-3 rounded p-2" method="POST" action="/casino">
	   				<h2>Casino!</h2>
	   				<h4>(Earns/ takes 0-50 gold)</h4>
	   				<button class="btn btn-warning">Find Gold!</button>
	   			</form>
	   			<form class="col-12 rounded p-2" method="POST" action="/spa">
	   				<h2>Spa</h2>
	   				<h4>(Takes 5-20 gold)</h4>
	   				<button class="btn btn-info">Expend Gold</button>
	   			</form>
	   			<div 
	   				class="border border-1 rounded col mt-4" 
	   				style="
	   					height: 200px;
	   					overflow-y: auto;
	   				"
	   				id="records"
	   			>
	   				<c:forEach items="${records}" var="record">
 						<p class="mb-1"><c:out value="${record}"/></p>
				 	</c:forEach>
	   			</div>
	   			<form class="mt-4" method="POST" action="/restart">
	   				<button class="btn btn-danger float-end">Restart</button>
	   			</form>
   			</div>
   		</div>
   	</main>
</body>
<script>
	var records = document.querySelector("#records");
	records.scrollTop = records.scrollHeight;
</script>
</html>