<%@ page import = "task.ToDo,jakarta.servlet.*,jakarta.servlet.http.* "%>
<%@ page import ="java.util.ArrayList"%>

<%@ page import ="java.util.Date"%>
<%@ page import ="java.util.Locale"%>
<%@ page import ="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>To Do List</title>
    <link rel="stylesheet" href="./styles.css">


</head>
<body>
<%

if(request.getParameter("id") !=null){
	String id= request.getParameter("id").toString();
	ToDo td = new ToDo();
	td.setId(id);
	td.DeleteToDo();
}
if(request.getParameter("taskname") !=null){
String taskname= request.getParameter("taskname").toString();
String  duedate= request.getParameter("duedate").toString();
String desc= request.getParameter("desc").toString();
if(taskname.length()>0 && duedate.length()>0 && desc.length()>0){
	ToDo td = new ToDo("0",taskname,desc,duedate);
	td.addToDo();	
}
}
%>
    <table >

        <tr>
		<td colspan=2>
                <h1>To Do List</h1>
                
            </td>
        </tr>
        <tr>

            <td width="50%">
                <div class="container">
                    <form method="post" action="form.jsp" name="ToDo">

                        <label for="taskname">Task Name</label><br>
                        <input type="text" id="taskname" name="taskname" placeholder="Task name here..." >
<br>
                        <label for="duedate">Due Date (yyyy/mm/dd)</label><br>
                        <input type="text" id="duedate" name="duedate" placeholder="When is it due.." >
<br>
                        <label for="desc">Description</label><br>
                        <textarea id="desc" name="desc" placeholder="Write a description.." style="height:200px" ></textarea>
<br>
                        <input type="submit" value="Submit">

                    </form>
                </div>

            </td>
	<td width="50%">
	<%
	ToDo td1 = new ToDo();
	ArrayList<ToDo> myArray = td1.getToDoList();

	if(!myArray.isEmpty()){
		%><table>
		<%
		for (ToDo td2 : myArray){
			%><Tr>
			<Td>
			Task: <%=td2.getName() %><br>
			Date: <%=td2.getDate() %><br>
			Description: <%=td2.getDescription() %><br>
			<hr></Td><td>
			<form  method="post" action="form.jsp" name="form_delete<%=td2.getId()  %>" >
			<input type="hidden" name="id" id="id" value="<%=td2.getId() %>">
			<input type="submit" value="Delete"></form>
			  </td></Tr>
		<%}	
		%></table>
	<% }
	%>


	</td>
        </tr>
    </table>

    
</body>
</html>
