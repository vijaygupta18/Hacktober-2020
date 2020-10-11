<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>

<head> 

<title> Torc Block</title>
<link rel="stylesheet" type="text/css" href="sty.css">
<script src="jquery-3.3.1.min.js" type="text/javascript" language="JavaScript"></script>
<script src="jquery-ui.js"></script>
<script type="text/javascript">
jQuery(document).ready(function(){
   jQuery('button.button').click(function(){
       //alert();
     //jQuery('div.container').html("<div class='note'>" + jQuery('<div/>').text(jQuery('textarea.textInput').val()).html() + "</div>" + jQuery('div.container').html());
     $('form#notes').submit();
     jQuery('textarea.textInput').val('');
        
   });
   
   var message = jQuery('#messageDiv').html();
   if(message){
	   jQuery('#messageDiv').fadeIn('fast').delay('2000').fadeOut('fast');
   } 
   
   var array=[];
   
   jQuery('div.note').each(function(){
	   jQuery(this).html(jQuery('<div/>').text(jQuery(this).html()).html());
	   array.push(jQuery(this).html());
   });
   
   $( "#tags" ).autocomplete({
       source: array
   });
});

</script>
</head>



<body>
<h1>Torc Block</h1>
<a id="av" href="login.jsp" class="button" ">Login</a>
<a id="av" href="createUser.html" class="button">Signup</a>

<input id="tags" placeholder="Search..."> <br>

<div class="main">

<div>
<a id="av" href="LogoutServlet" class="button" ">LogOut</a>
<form id="notes" action="NoteServlet" method="POST">
<div id="messageDiv" style="display:none"><%=request.getAttribute("message")==null? "":request.getAttribute("message") %></div>
<textarea rows="4" cols="60" class="textInput" name="note">

</textarea> <br/>
<%-- <input type="hidden" name="user" value="${sessionScope.user}"/> --%>
</form>
<button type="button" id="submit" value="Enter" class="button">Save</button>
</div>
<br/>
<div id="notes" class="container">
<c:forEach var="entry" items="${requestScope.notes}">
   <div class='note' id="${entry.key}">${entry.value} </div>
</c:forEach>

</div>
</div>

</body>
</html>


 



