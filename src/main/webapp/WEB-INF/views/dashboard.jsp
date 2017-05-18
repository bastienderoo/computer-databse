<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="<c:url value="css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="css/font-awesome.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="css/main.css"/>" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="Dashboard"> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${numberComputers} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="search" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

					</tr>
				</thead>

				<tbody id="results">
					<c:forEach items="${computerList}" var="computer">
						<tr>


							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a id="id" href="editComputer?id=${computer.id}" onclick=""><c:out
										value="${computer.name}"></c:out></a></td>
							<td>${computer.dateIntroduced}</td>
							<td>${computer.dateDiscontinued}</td>
							<td>${computer.company}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${page gt 1}">
					<li><a href="?page=${page-1}" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>

					</a></li>
				</c:if>
				<c:choose>
					<c:when test="${page lt 4}">
						<c:forEach var="i" begin="1" end="5">
							<li><a href="?page=${i}">${i}</a></li>
						</c:forEach>

					</c:when>
					<c:when test="${page > numberPage-3}">
						<c:forEach var="i" begin="1" end="5">
							<li><a href="?page=${numberPage-5 +i}">${numberPage-5 +i}</a></li>
						</c:forEach>

					</c:when>
					<c:otherwise>
						<c:forEach var="i" begin="1" end="5">
							<li><a href="?page=${page + i -3}">${page+i-3}</a></li>
						</c:forEach>


					</c:otherwise>
				</c:choose>

				<c:if test="${page lt numberPage}">
					<li><a href="?page=${page+1}" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>

					</a></li>
				</c:if>

			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<ul class="pagination">


					<li><a href="?numberElements=10">10</a></li>
					<li><a href="?numberElements=50">50</a></li>
					<li><a href="?numberElements=100">100</a></li>


				</ul>
			</div>
		</div>
	</footer>
	<script src="<c:url value="../js/jquery.min.js"/>"></script>
	<script src="<c:url value="../js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="../js/dashboard.js"/>"></script>

</body>
</html>