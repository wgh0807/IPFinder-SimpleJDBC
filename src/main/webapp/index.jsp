<%--
  Created by IntelliJ IDEA.
  User: wgh
  Date: 2018/9/29
  Time: 上午10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>IP 地理位置信息查询1</title>
  </head>
  <body>
  <h1>ip 地址查询系统</h1>

    <form action="IPServer" method="post">
        <input type="text" name="ip" placeholder="待查询的ip地址" />
        <input type="submit" value="查询">
    </form>
  <div style="color: #ff0000">

      <c:choose>
          <c:when test="${sessionScope.find eq null}">${sessionScope.find}请输入您所要查询的IP</c:when>
          <c:otherwise>
              <c:choose>
                  <c:when test="${sessionScope.result ne null}" >查询结果：${sessionScope.result};</c:when>
                  <c:otherwise>
                      抱歉，未找到相关信息
                  </c:otherwise>
              </c:choose>
              <c:remove var="find" scope="session" ></c:remove>
          </c:otherwise>

      </c:choose>


  </div>

  </body>
</html>
