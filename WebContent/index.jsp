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
				<button class="btn btn-primary btn-lg" id="emp_create" >Create</button>
				<button class="btn btn-danger btn-lg">Delete</button>
			</div>
		</div>
		
		<!-- 新增的模态框 -->
		<div class="modal fade" tabindex="-1" role="dialog" id="create_modal">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">new employee.....</h4>
		      </div>
		      <div class="modal-body">
		       
		        <!-- 表单 -->
		        <form class="form-horizontal" id="empInfo">
		        <div class="form-group">
				    <label for="inputName" class="col-sm-2 control-label">name</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="name" id="inputName" placeholder="name">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail" class="col-sm-2 control-label">email</label>
				    <div class="col-sm-10">
				      <input type="email" class="form-control" name="email" id="inputEmail" placeholder="Email">
				    </div>
				  </div>
				  
				  <div class="form-group">
					  <label class="radio-inline col-sm-offset-2">
						  <input type="radio" name="gender" id="inlineRadiobox1" value="f"> female
						</label>
						<label class="checkbox-inline " >
						  <input type="radio" name="gender" id="inlineRadiobox2" value="m"> male
						</label>
					 </div>
		
					<div class="form-group">
					  	<label for="inputEmail" class="col-sm-2 control-label">department</label>
				  		<div class="col-sm-4 col-sm-offest-5" >
					  		<select id="deptList" class="form-control" name="departmentId">
							</select>
						</div>
					</div>
					
				</form>
		        
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" id="savebtn">Save</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		
		
		
		
		
		
		
			<!--表格数据-->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
						<th>
							<input type="checkbox" id="check_all">
						</th> 
							<th>#</th>
							<th>empname</th>
							<th>gender</th>
							<th>email</th>
							<th>deptName</th>
							<th>操作</th>
						</tr>
					</thead>
					
				<tbody>
				</tbody>			
				
				</table>
			
			</div>
		</div>
				<!--分页信息-->
			<div class="row">
				<!--分页文字信息-->
				<div class="col-md-6" id="page_info_area"></div>
				<!--分页条信息-->
				<div class="col-md-6" id="page_nav"></div>
			</div>
		
	</div>

    <script src="${APP_PATH}/static/js/jquery-3.1.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    
    
    <script type="text/javascript">
    
    	var totalRecord;
    	
    	$(function(){
    		bindingEvent();
    		to_page(1);
    		
    	});
    	
    	function bindingEvent(){
    		//设置部门信息
    		setDepts();
    		//绑定create button的模态框事件
    		$("#emp_create").click(function(){
    			$("#create_modal").modal({
    				backdrop:'static'
    			})
    		});
    		//绑定提交事件
    		$("#savebtn").click(function(){
 
	    			$.ajax({
	    				url:"${APP_PATH}/employee/save",
	    				type:"POST",
	    				data: $('#empInfo').serialize(),				
	    				success:function(result){
	    					//关闭模态框
	    					$("#create_modal").modal('hide');
	    					//到最后一页,设置reasonable属性为true后，传个很大的数就行了，pageInfo自动定位到最后一页
	    					to_page(totalRecord);//以总记录数作为页数...
	    				}
	    			});
    		});
    	}
    	
    	function setDepts(){
    		
    		$.get("${APP_PATH}/department/list",function(result){
    			
    			var depts = result.data.depts;
    			var deptsel = $('#deptList');
    			depts.forEach(function(val){
    				$('<option></option>').append(val.name).attr("value",val.id).appendTo(deptsel);
    			});
    			
    			
    		})
    		
    	}
    	
    	//翻页事件方法
		function to_page(pn) {
			$.ajax({
				url : "${APP_PATH}/employee/list/"+pn,
				type : "GET",
				success : function(result) {
					//console.log(result);
					//1.解析并显示员工数据
					build_emps_table(result);
					//2.解析并显示分页信息
					build_page_info(result);
					//解析显示分页条数据
					build_page_nav(result);
				}
			});
		}
    	
    	function build_emps_table(result) {
			//清空table 表格
			//emps_table tbody
			$("#emps_table tbody").empty();
			var emps = result.data.pageInfo.list;
			$.each(emps, function(index, item) {
				var checkBoxTd=$("<td><input type='checkbox' class='check_item'/></td>");
				var empIdTd = $("<td></td>").append(item.id);
				var empNameTd = $("<td></td>").append(item.name);
				var genderTd = $("<td></td>").append(
						item.gender == 'm' ? "male" : "female");
				var emailTd = $("<td></td>").append(item.email);
				var deptName = $("<td></td>").append(item.department.name);
				var editBtn = $("<button></button>").addClass(
						"btn btn-primary btn-sm edit_btn").append(
						$("<span></span>").addClass(
								"glyphicon glyphicon-pencil")).append("edit");
				
				//为编辑按钮添加一个自定义的属性,来表示当前员工的id
				editBtn.attr("edit-id",item.id);
			 
				var delBtn = $("<button></button>").addClass(
						"btn btn-danger btn-sm delete_btn").append(
						$("<span></span>")
								.addClass("glyphicon glyphicon-trash"))
								.append("delete");
				
				//为删除按钮添加一个自定义的属性来表示当前员工的删除id
				delBtn.attr("del-id",item.id);
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(
						delBtn);
				//append方法执行完成以后还是返回原来的元素
				$("<tr></tr>").append(checkBoxTd)
							.append(empIdTd)
							.append(empIdTd)
							.append(empNameTd)
					     	.append(genderTd)
					    	.append(emailTd)
					    	.append(deptName)
						    .append(btnTd)
						    .appendTo("#emps_table tbody")
			})
		}
		//解析显示分页信息
		function build_page_info(result) {
			//page_info_area
			$("#page_info_area ").empty();
			$("#page_info_area").append(
					"current page:" + result.data.pageInfo.pageNum + ",   total pages:"
							+ result.data.pageInfo.pages + ",  total records:"
							+ result.data.pageInfo.total );
		 totalRecord=result.data.pageInfo.total; //保存总记录数
		 currentPage = result.data.pageInfo.pageNum;
		}
		
		//解析显示分页条
		function build_page_nav(result) {
			//page_nav
			$("#page_nav").empty();
			var ul = $("<ul></ul>").addClass("pagination");
			//构建元素
			var firstPageLi = $("<li></li>").append(
					$("<a></a>").append("first").attr("href", "#"));
			var prePageLi = $("<li></li>").append(
					$("<a></a>").append("&laquo;").attr("href", "#"));
			if (result.data.pageInfo.hasPreviousPage == false) {
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			}
			//为元素添加点击翻页的事件
			firstPageLi.click(function() {
				to_page(1);
			})
			prePageLi.click(function() {
				if(result.data.pageInfo.pageNum > 1)
					to_page(result.data.pageInfo.pageNum - 1);
			})
			var nextPageLi = $("<li></li>").append(
					$("<a></a>").append("&raquo;").attr("href", "#"));
			var lastPageLi = $("<li></li>").append(
					$("<a></a>").append("last").attr("href", "#"));
			if (result.data.pageInfo.hasNextPage == false) {
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			} else {
				nextPageLi.click(function() {
					to_page(result.data.pageInfo.pageNum + 1);
				})
				lastPageLi.click(function() {
					to_page(result.data.pageInfo.pages);
				})
			}
			//添加首页和前一页的提示
			ul.append(firstPageLi).append(prePageLi);
			//1.2.3遍历给ul中添加页码提示
			$.each(result.data.pageInfo.navigatepageNums, function(index,
					item) {
				var numLi = $("<li></li>").append($("<a></a>").append(item).attr("href", "#"));
				if (result.data.pageInfo.pageNum == item) {
					numLi.addClass("active");
				}
				numLi.click(function() {
					to_page(item);
				})
				ul.append(numLi);
			});
			//添加下一页和末页提示
			ul.append(nextPageLi).append(lastPageLi);
			//把ul加入到nav元素中
			var navEle = $("<nav></nav>").append(ul);
			navEle.appendTo("#page_nav");
		}
    	
    </script>
    
    
</body>
</html>