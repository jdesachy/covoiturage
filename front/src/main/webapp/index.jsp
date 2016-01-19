<%@page import="covoiturage.CalendrierJspHelper"%>
<%@ page language="java" isErrorPage="true"
	contentType="text/html; 
    charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="covoiturage.calendrier.Calendrier" %>
<%@ page import="covoiturage.CalendrierJspHelper" %>
<%@ page import="covoiturage.calendrier.Jour" %>
<%@ page import="covoiturage.calendrier.Semaine" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<link href="css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
<script language="Javascript" type="text/javascript">
	$(document).ready(function() {
	
	    //checks for the button click event
	    $("select[name^='tarif']").change(function(e){
		    $.ajax({
		        type: "POST",
		        url: "update",
		        data: {
		        	value: this.value ,
		        	name: this.name },
		        contentType: "application/json; charset=utf-8",
		        dataType: "json",
		        success: function (result) { alert("successful!" + result.d); }
		    });
	    });
	});
</script>
  
</head>
<body>
	<% 
	Calendrier cal = (Calendrier)request.getAttribute("calendrier"); 
	CalendrierJspHelper helper = (CalendrierJspHelper)request.getAttribute("helper"); 
	%>
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Covoiturage</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Calendrier</a></li>
            <li><a href="#about">About</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
          	<li><a href="#">Configuration</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

	<div class="container">
		<%
		Integer week = (Integer)request.getAttribute("week");
		%>
		<div class="link">
			<a href="<%=request.getContextPath() %>?week=<%=week-1%>"><button type="button" class="btn btn-xs btn-primary link-left">Précédent</button></a>
			<a href="<%=request.getContextPath() %>?week=<%=week+1%>"><button type="button" class="btn btn-xs btn-primary link-right">Suivant</button></a>
			&nbsp;
		</div>
		<div style="clear: both"></div>
		<div class="row">
		<% List<Semaine> semaines = cal.getSemaines(); 
			for( Semaine s : semaines ){
			%>
			<div class="col_cal">
				<div class="panel panel-default">
	            	<div class="panel-heading">
	              		<h3 class="panel-title">Semaine <%=s.getSemaine() %></h3>
	            	</div>
	            	<div class="panel-body body-center <%=s.isCurrent()?"current":"" %>">
						<ul class="list-group">
			<% 	for( Jour j : s.getJours() ){
					%>
				            <li class="list-group-item <%=j.isCurrent()?"current":"" %>">
				            <h4><span class="label label-default"><%=j.toString() %></span></h4>
				            	<select name="tarif_<%=helper.getId(j) %>">
				            		<option value="PLEIN">JD/JB 100%</option>
				            		<option value="REDUC">JD/JB 75%</option>
				            		<option value="AUCUN">-</option>
				            	</select>
				            </li>
					<%	
				}
				%>
				        </ul>
		            </div>
		         </div>
			</div>
				<%
			}
		%>
		</div>
	</div>
	
      
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
	<script src="bootstrap/js/bootstrap.min.js"></script>
	
	<script>window.jQuery || document.write('<script src="js/jquery.min.js"><\/script>')</script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>