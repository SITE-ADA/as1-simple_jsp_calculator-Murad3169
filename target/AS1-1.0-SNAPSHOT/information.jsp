<html>
<body>
<table BORDER="1">
    <thead>
    <tr><td>User Information</td><td><a href="index.jsp">RETURN</a></td></tr>
    </thead>
    <tbody STYLE="text-align: center">
        <tr>
            <td COLSPAN="2"><p>Browser: <%=request.getAttribute("infoB")%></p></td>
        </tr>
    <tr>
        <td COLSPAN="2"><p>OS: <%=request.getAttribute("infoO")%></p></td>
    </tr>
    </tbody>
</table>
</body>
</html>
