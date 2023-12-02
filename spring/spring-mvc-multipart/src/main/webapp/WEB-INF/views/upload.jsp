<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Upload File</title>
    <style>
      table {
        border-spacing: 5px;
        background-color: #FFFFF0;
      }

      td {
        padding: 5px;
        text-align: left;
      }

      h4.error {
        color: #ff0000;
      }
    </style>
</head>
<body>
<h3>Spring MVC 5 Servlet 3.0 MultipartElement Example </h3>
<br>
<c:if test="${not empty error}">
    <h4 class=error>An error occurred: ${error}</h4>
    <br>
</c:if>

<form:form method="POST"
           action="${pageContext.request.contextPath}/uploadFile"
           enctype="multipart/form-data">
    <table>
        <tr>
            <td>Select a file to upload</td>
            <td><input type="file" name="file"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Upload"></td>
            <td></td>
        </tr>
    </table>
</form:form>
</body>
</html>
