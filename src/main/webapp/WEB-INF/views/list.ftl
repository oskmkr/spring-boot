<html>
<head>
    <title>event list</title>
</head>
<body>
<#--
<#list doctcEvent as event>
${event.title}
</#list>
-->
${doctcEvent.title}, ${doctcEvent.link}
<a href="${doctcEvent.link}">링크</a>
</body>
</head>
</html>