<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品信息</title>
<link href="${pageContext.request.contextPath }/public/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath }/public/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		var params = '{"id": 1,"name": "测试商品","price": 99.9,"detail": "测试商品描述","pic": "123456.jpg"}';

		$.ajax({
			url : "${pageContext.request.contextPath }/json",
			data : params,
			contentType : "application/json;charset=UTF-8",
			type : "post",
			dataType : "json",
			success : function(data) {
				alert(data.name);
			}

		});
	});
</script>
</head>
<body class="container">
 <form id="itemForm" action="${pageContext.request.contextPath }/updateitem" method="post" enctype="multipart/form-data">
  <input type="hidden" name="items.id" value="${item.id }" />
  修改商品信息：
  <table class="table table-bordered">
   <tr>
    <td>商品名称</td>
    <td>
     <input class="form-control" type="text" name="items.name" value="${item.name }" />
    </td>
   </tr>
   <tr>
    <td>商品价格</td>
    <td>
     <input class="form-control" type="text" name="items.price" value="${item.price }" />
    </td>
   </tr>
   <tr>
    <td>商品图片</td>
    <td>
     <c:if test="${item.pic !=null}">
      <img src="/pic/${item.pic}" width=100 height=100 />
      <br />
     </c:if>
     <input class="form-control" type="file" name="pictureFile" />
    </td>
   </tr>
   <tr>
    <td>商品简介</td>
    <td>
     <textarea class="form-control" rows="3" cols="30" name="items.detail">${item.detail }</textarea>
    </td>
   </tr>
   <tr>
    <td colspan="2" align="center">
     <input type="submit" value="提交" />
    </td>
   </tr>
  </table>
 </form>
</body>

</html>