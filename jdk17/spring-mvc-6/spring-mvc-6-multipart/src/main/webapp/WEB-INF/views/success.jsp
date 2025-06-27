<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>File Uploaded Successfully</title>
</head>
<body>
<h3>File "${filename}" was uploaded successfully!</h3>

Click to view or save the file. <a href='<c:url value="/resources/files/${ filename }" />'>${filename}</a>
<br>
<br>
Return to <a href="<c:url value='/upload' />">Upload File</a>
</body>
</html>
