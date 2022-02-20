<html>
<body>
<table border="1" style="width: 500px;margin: 0 auto">
    <thead>
    <tr>
    <th colspan="2">
        <b>Calculation</b>
    </th>
    <th colspan="2">
        User info: <a href="Evaluate-Servlet">LINK</a>
    </th>
    </tr>
    </thead>
    <tbody style="text-align: center;">
    <form action="Evaluate-Servlet" method="post">
    <tr>
    <td  colspan="2">1st</td>
    <td  colspan="2">2st</td>
    </tr>
    <tr>
    <td colspan="2"><input  placeholder=" Enter" style="width: 100%;" name="N1" type="text"></td>
    <td colspan="2"><input  placeholder=" Enter" style="width: 100%;" name="N2" type="text"></td>
    </tr>
    <tr>
    <td><input type="radio" name="symbol" checked value="+">+</td>
    <td><input type="radio" name="symbol" value="-">-</td>
    <td><input type="radio" name="symbol" value="*">*</td>
    <td><input type="radio" name="symbol" value="/">/</td>
    </tr>
    <tr>
    <td colspan="4">
    <button type="submit" style="width: 100%;">Get Result</button>
</td>
</tr>
<tr>
<td colspan="4">
<h4>ANSWER:<%=request.getAttribute("answer")%> </h4>
    <% if (request.getAttribute("e")!=null){%>
          <h4><%=request.getAttribute("e")%></h4>
    <%}%>
</td>
</tr>
</form>
</tbody>
</table>
</body>
</html>