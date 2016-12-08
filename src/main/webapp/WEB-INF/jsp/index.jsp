<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML>
<html>
<t:head/>
<t:layout>
    <h2>
        Hello, ${name}
    </h2>

    <form>
        What is your name?
        <input type="text" name="name" value="${name}"/>
        <input type="submit">
    </form>

    <%@ include file="_messages.jsp" %>

</t:layout>
</html>
