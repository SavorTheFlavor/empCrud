<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
 	pageContext.setAttribute("APP_PATH",request.getContextPath());
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>employee....</title>


	<!--  没有/开头的，是以当前资源为基准，易出问题 
			以/开头的，是以服务器路径为标准（http://localhost:8080/
	-->
    <!-- Bootstrap -->
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<!-- 栅格系统  -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>SSM_CRUD</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">Create</button>
				<button class="btn btn-danger">Delete</button>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table table-striped">
					<tr>
						<th>#</th>
						<th>name</th>
						<th>gender</th>
						<th>email</th>
						<th>departmentName</th>
					</tr>
					
					<tr>
						<td>1</td>
						<td>jack</td>
						<td>male</td>
						<td>aweaw@qq.com</td>
						<td>yahoo</td>
						
						<td>
								<button class="btn btn-primary btn-sm">
									Create
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								</button>
								<button class="btn btn-danger btn-sm">
								Delete
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
								</button>
						</td>
					</tr>					
				
				</table>
			
			</div>
		</div>
		<div class="row">
			<div class="col-md-6"></div>
			
			
			<div class="col-md-6">
				<nav aria-label="Page navigation">
					  <ul class="pagination">
					    <li>
					      <a href="#" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    <li><a href="#">first</a></li>
					    <li><a href="#">1</a></li>
					    <li><a href="#">2</a></li>
					    <li><a href="#">3</a></li>
					    <li><a href="#">4</a></li>
					    <li><a href="#">last</a></li>
					    <li>
					      <a href="#" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				  </ul>
				</nav>	
					
			</div>
		</div>
		
	</div>

    <script src="${APP_PATH}/static/js/jquery-3.1.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>