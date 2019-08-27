<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {

		// add multiple select / deselect functionality
		$("#selectall").click(function() {
			$('.ID').attr('checked', this.checked);
		});

		$(".ID").click(function() {

			if ($(".ID").length == $(".ID:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}

		});
	});
</SCRIPT>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="container">
		iloscWejsc ${iloscWejsc}


		<div class="tab-content">
			<form class="form-horizontal" action="/osoby/delete" method="get">
				<div class="form-group">
					<table style="width: 700px;" class="table table-bordered">
						<thead>


							<tr>
								<th style="width: 39px;"><input type="checkbox"
									id="selectall" /></th>
								<th style="width: 75px;">IMIE</th>
								<th style="width: 70px;">NAZWISKO</th>
								<th style="width: 70px;">EMAIL</th>
								<th style="width: 55px;">BRANZA</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="e" items="${osoby}">
								<tr>

									<td><input type="checkbox" class="ID" name="ID"
										value="${e.id}" /></td>
									<td>${e.imie}</td>
									<td>${e.nazwisko}</td>
									<td>${e.email}</td>
									<td>${e.branza}</td>

								</tr>
							</c:forEach>
					</table>
				</div>
				<input type="submit" value="Usun" />

			</form>
		

		</div>
	</div>
</body>