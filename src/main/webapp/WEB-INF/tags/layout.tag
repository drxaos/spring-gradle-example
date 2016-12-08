<%@tag description="Layout Tag" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Demo Application</h1>

<a href="/">home</a>

<c:if test="${__admin}">
    | <a href="/users">users</a>
</c:if>

<c:if test="${__user || __admin}">
    (<a href="/logout">logout</a>)
</c:if>
<c:if test="${__anonymous}">
    (<a href="/login">login</a>)
</c:if>

<hr/>

<div>
    <jsp:doBody/>
</div>
