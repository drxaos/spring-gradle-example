<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Last messages</h2>
<table border="1" cellpadding="5">
    <tr>
        <th>Date</th>
        <th>User</th>
        <th>Message</th>
    </tr>
    <c:forEach var="m" items="${messages}">
        <tr>
            <td><c:out value="${m.date}"/></td>
            <td><c:out value="${m.user.login}"/></td>
            <td><c:out value="${m.message}"/></td>
        </tr>
    </c:forEach>
</table>
