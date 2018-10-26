<%--
  Created by IntelliJ IDEA.
  User: watermelon
  Date: 2018/7/16
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        var time = 8; //时间,秒
        function Redirect() {
            window.location = "http://www.cssue.com/";
        }
        var i = 0;
        function dis() {
            document.all.s.innerHTML = "还剩" + (time - i) + "秒";
            i++;
        }
        timer = setInterval('dis()', 1000); //显示时间
        timer = setTimeout('Redirect()', time * 1000); //跳转
    </script>
</head>
<body>

</body>
</html>
