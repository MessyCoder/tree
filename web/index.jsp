<%@ page import="starsoft.hln.tis.mic.config.AppConfig" %><%--
  Created by IntelliJ IDEA.
  User: 潘　国峰
  Date: 2019/8/17
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
<html>
  <head>
      <title>トリー表示</title>

      <link rel="stylesheet" href="jquery/jquery-ui.min.css" />
      <link rel="stylesheet" href="css/theme.css" />
      <link rel="stylesheet" href="jstree/themes/default/style.css" />
      <script src="jquery/jquery.min.js"></script>
      <script src="jquery/jquery-ui.min.js"></script>
      <script src="jstree/jstree.min.js"></script>
      <script src="javascript/setup.js"></script>
  </head>
  <body>
  <p class="the-title">このアプリでは、指定ディレクトリーのツリー表示を行う。</p>
  <div style="margin: 5px 0">
    <span class="fs-path"><%=AppConfig.get("if_base_path")%> </span>
  </div>
  <div id="fs-tree">
  </div>

  </body>
</html>
