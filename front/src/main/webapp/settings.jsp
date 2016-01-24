<%@page import="covoiturage.db.Tarification"%>
<%@page import="java.util.List"%>
<%@ page language="java" isErrorPage="true"
	contentType="text/html; 
    charset=UTF-8" pageEncoding="UTF-8"%>
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
		function add(){
			document.getElementById("formTarif").submit();
		}
	</script> 
</head>
<body>
	<% List<Tarification> tarifs = (List<Tarification>)request.getAttribute("tarifs"); %>
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
            <li><a href="/voiture">Calendrier</a></li>
          	<li class="active"><a href="/settings">Configuration</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#about">About</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

	<div class="container">
		<form action="/settings" method="post" id="formTarif">
			<div class="panel panel-default">
	            <div class="panel-heading">
	              <h3 class="panel-title">Ajout d'un tarif</h3>
	            </div>
	            <div class="panel-body">
					<div class="row">	
						<div class="col-sm-4">
							Nom
						</div>
						<div class="col-sm-4">
							Montant
						</div>
						<div class="col-sm-4">
							&nbsp;
						</div>
					</div>
					<div class="row">	
						<div class="col-sm-4">
							<input name="name" type="text"></input>
						</div>
						<div class="col-sm-4">
							<input name="value" type="text"></input>
						</div>
						<div class="col-sm-4">
							<button class="btn btn-xs btn-primary" type="button" onclick="javascript:add();">Ajouter</button>
						</div>
					</div>
	            </div>
	       	</div>

			<div class="panel panel-default">
	            <div class="panel-heading">
	              <h3 class="panel-title">Tarifs existant</h3>
	            </div>
	            <div class="panel-body">
					<div class="row">	
						<div class="col-sm-4">
							Nom
						</div>
						<div class="col-sm-4">
							Montant
						</div>
						<div class="col-sm-4">
							&nbsp;
						</div>
					</div>

			<%
			if( tarifs != null ){
				for( Tarification t : tarifs ){
					%>
					<div class="row">	
						<div class="col-sm-4">
							<%= t.getName() %>
						</div>
						<div class="col-sm-4">
							<%= t.getAmount() %>
						</div>
						<div class="col-sm-4">
							&nbsp;
						</div>
					</div>
					<%
				}
			}
			%>
	            </div>
	       	</div>			
		</form>
	</div>
      
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script>window.jQuery || document.write('<script src="js/jquery.min.js"><\/script>')</script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>