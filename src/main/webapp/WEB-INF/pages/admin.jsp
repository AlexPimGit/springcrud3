<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Users Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<%--<a href="../../login.jsp">Back to main menu</a>--%>
<a href="<c:url value="/"/>" target="_self">Back to main menu</a>

<br/>
<br/>

<h1>User List</h1>

<c:if test="${!empty listUsers}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
            <th width="120">Password</th>
            <th width="120">Position</th>
            <th width="120">Age</th>
            <th width="120">Email</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td><a href="/user/${user.id}" target="_blank">${user.name}</a></td>
                <td>${user.userPassword}</td>
                <td>${user.position}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td><a href="<c:url value='/admin/edit/${user.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/admin/remove/${user.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<h1>Add a User</h1>

<c:url var="addAction" value="/admin/add"/>
<form:form action="${addAction}" modelAttribute="user">
    <table>
        <c:if test="${!empty user.name}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="userPassword">
                    <spring:message text="Password"/>
                </form:label>
            </td>
            <td>
                <form:input path="userPassword"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="position">
                    <spring:message text="Position"/>
                </form:label>
            </td>
            <td>
                <form:input path="position"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="age">
                    <spring:message text="Age"/>
                </form:label>
            </td>
            <td>
                <form:input path="age"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="email">
                    <spring:message text="Email"/>
                </form:label>
            </td>
            <td>
                <form:input path="email"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty user.name}">
                    <input type="submit"
                           value="<spring:message text="Edit User"/>"/>
                </c:if>
                <c:if test="${empty user.name}">
                    <input type="submit"
                           value="<spring:message text="Add User"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>

