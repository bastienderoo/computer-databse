
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/webapp/Dashboard"> Application -
				Computer Database </a>
			<div class="pull-right" style="margin-top: 10px;">
				<form id="logout" action="logout" method="POST">

					<input type="submit" name="logout" value="logout" /> <input
						type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id: ${computerId}
					</div>
					<h1>Edit Computer</h1>

					<form action="editComputer" method="POST">
						<input type="hidden" value="${computerId}" name="id" id="id" />

						<fieldset>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" name="name"
									id="computerName" value="${computerName}">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label>
								<c:choose>
									<c:when test="${introduced == null}">
										<input type="date" class="form-control" name="introduced"
											id="introduced" placeholder="Introduced date">
									</c:when>
									<c:otherwise>
										<input type="date" class="form-control" name="introduced"
											id="introduced" value="${dateIntroduced}">
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label>
								<c:choose>
									<c:when test="${discontinued == null}">
										<input type="date" class="form-control" name="discontinued"
											id="discontinued" placeholder="Discontinued date">
									</c:when>
									<c:otherwise>
										<input type="date" class="form-control" name="discontinued"
											id="discontinued" value="${dateDiscontinued}">
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" name="idCompany" id="companyId">
									<option value="0">--</option>
									<c:forEach items="${companyId}" var="company">
										<option value="${company.id}">${company.getName()}</option>

									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="Dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>