<html>

<head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>

<body>
	<div>All Device Detils </div>
	<c:if test="${not empty devices}">

		<ul>
			<c:forEach var="device" items="${devices}">
				<li>${device.deviceIp}</li>
			</c:forEach>
		</ul>

	</c:if>
</body>

</html>