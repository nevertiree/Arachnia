<%--
  Created by IntelliJ IDEA.
  User: Lance Wang
  Date: 2016/12/31
  Time: 9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
  <head>
    <title>豆瓣书籍统计</title>
  </head>

  <body>

  根据书籍ISBN查询信息
    <form action="/api/query/isbn" method="get">
        <input type="text" name="isbn">
        <input type="submit" value="submit">
    </form>

  根据书籍名称查询信息
  <form action="/api/query/name" method="get">
      <input type="text" name="name">
      <input type="submit" value="submit">
  </form>


  根据读者总量排名
  <form action="/api/query/voteDesc" method="get">
      <input type="text" name="topN">
      <input type="submit" value="submit">
  </form>

  根据评分高低排名
  <form action="/api/query/scoreDesc" method="get">
      <input type="text" name="topN">
      <input type="submit" value="submit">
  </form>

  </body>
</html>
