<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	The last 10 request on this URL are:
</h1>

<P>  1.  ${CharRequest[0]} </P>
<P>  2.  ${CharRequest[1]} </P>
<P>  3.  ${CharRequest[2]} </P>
<P>  4.  ${CharRequest[3]} </P>
<P>  5.  ${CharRequest[4]} </P>
<P>  6.  ${CharRequest[5]} </P>
<P>  7.  ${CharRequest[6]} </P>
<P>  8.  ${CharRequest[7]} </P>
<P>  9.  ${CharRequest[8]} </P>
<P>  10. ${CharRequest[9]} </P>
</body>
</html>
