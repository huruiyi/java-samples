<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
<link href="${pageContext.request.contextPath }/public/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body class="container">
 <form action="${pageContext.request.contextPath }/login/submit" method="post">
  <table>
   <tr>
    <td>
     用户名:
     <input name="username" type="text" />
    </td>
   </tr>
   <tr>
    <td>
     密码:
     <input name="pwd" type="password" />
    </td>
   </tr>
   <tr>
    <td>
     <input value="登录" type="submit" />
    </td>
   </tr>
  </table>
 </form>
</body>

</html>