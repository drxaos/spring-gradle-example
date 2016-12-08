<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML>
<html>
<t:head/>
<t:layout>
    <h2>
        Hello, ${name}
    </h2>

    <%@ include file="../_messages.jsp" %>

    <form method="post" action="/post">
        <input type="text" name="text" placeholder="Add message"/>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <input type="submit"/>
    </form>
</t:layout>
</html>
