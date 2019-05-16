<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.neuedu.model.po.Emp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

    <!-- the whole point of having el and jstl, is to eliminate all the java source code from jsp -->

    <!-- get from info from request -->
    <!-- el expression lauguage, el expression is to get data from page, request, session, application, cookie -->
    
    <%--  <%= request.getAttribute("msg") %> --%>

     ${requestScope.msg} 
        
     <%--  <%
         List<Emp> list = (List)request.getAttribute("list");
         for(Emp e :list)
         {
     %>
          <%=e.getEname() %> , <%=e.getHiredatestr() %> 
     <%    	
         }  
     %> --%>
     
     <!-- jstl taglib is used together with el -->
     <table>
          <c:forEach items="${list}" var="item">
             <tr>
                 <td>${item.empno}</td>
                 <td>${item.ename}</td>
                 <td>${item.job}</td>
                 <td>${item.hiredatestr}</td>
             </tr>
          </c:forEach> 
     </table>
     
    
     
     
  
    
   

</body>
</html>