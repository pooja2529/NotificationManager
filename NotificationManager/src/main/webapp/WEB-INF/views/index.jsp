<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<!--  Static resources form Webjars -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    
      <!-- <link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.css"> -->
      <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
      <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/css/bootstrap-datetimepicker.min.css"> 
      <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/css/bootstrap-datetimepicker-standalone.css"> 
      <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/js/bootstrap-datetimepicker.min.js"></script>
    

<script type="text/javascript">
$(function() {
	  $('#datetimepicker1').datetimepicker();
	});
function showDiv(divId, element)
{
    document.getElementById(divId).style.display = element.value == 1 ? 'block' : 'none';
}

function showSelectDiv()
{
	var x= document.getElementById("select");
	
	if(x.value === '1')
		{
		document.getElementById("hidden_selectDiv").style.display='none';
		document.getElementById("showdiv").style.display='block';
		
		}
	
	if(x.value === '2')
	{
	document.getElementById("hidden_selectDiv").style.display='block';
	document.getElementById("showdiv").style.display='none';
	
	}
	if(x.value === '')
		{
		document.getElementById("validselect").innerHTML = "Please select atleast one option  ";
		}
	
/*    document.getElementById(divId).style.display = element.value == 2 ? 'block' : 'none';
 */}
function redirectOnClick(){
    document.location ='sendlater';
}
</script>
<script type="text/javascript">
function validateOption()
{
  var first = document.getElementById("select").value;
  var type=document.getElementById("ntype").value;
  if(first===""){
 	 document.getElementById("validselect").innerHTML="please select notification type ";
 	 return false;
 }
  if(type===""){
	 	 document.getElementById("validtype").innerHTML="please select notification type ";
	 	 return false;
	 }
	 return true;
}

function checkForm(){
    var e=document.getElementById("selectany").value;//alert(e);
    var m=document.getElementById("mobselect").value;
    var msg=document.getElementById("msg").value;
    if(e===""){
    	 document.getElementById("validnotification").innerHTML="please select notification type ";
        return false;
    }

    if(m=="")
        {
    	document.getElementById("validmob").innerHTML="please enter mobile number. ";
        return false;
        }
    if(msg=="")
    {
	document.getElementById("validmsg").innerHTML="please enter message. ";
    return false;
    }
    return true;
}
</script>
<style type="text/css">
#hidden_selectDiv
{
 display: none;
}

#showdiv {
    display: none;
}
#hidden_div {
    display: none;
}
   .validation
    {
      color: red;
      margin-bottom: 20px;
    }
button {
	width: 100px;
	height: 40px;
	border-radius: 30px;
	color: white;
	background: green;
}

</style>
<meta charset="UTF-8">
<title>Notification</title>
</head>
<body>

	<div class="container">
  <div class="row">
		<h5 class="text-muted" style="color: green;">Communication</h5>
		<hr>
		<h4 style="font: bold;">Test Communication</h4>

 <div class="col-sm-6">
		<form:form name="notification"  modelAttribute="notification" onSubmit="return checkForm();" action="addNotification" enctype="multipart/form-data" acceptCharset="UTF-8">
			<div class="form-group row">
				<div class="col-sm-10">
					<form:select path="pn_type" name="ntype" class="form-control" id="selectany"  onchange="showDiv('hidden_div', this)">
						<form:option value="">---Select Notification Type---</form:option>
						<form:option value="1">IMAGE NOTIFICATION</form:option>
						<form:option value="pn">PUSH NOTIFICATION</form:option>
					</form:select>
					<h6 style="color: red;">
						<span id="validnotification"></span>
					</h6>
				</div>

			</div>
			<div class="form-group row">
				<div class="col-sm-10">
					<form:input type="text" path="pn_to" class="form-control" id="mobselect"
						placeholder="Mobile Number"/>
					<h6 style="color: red;">
						<span id="validmob"></span>
					</h6>
				</div>
			</div>
			
			<div class="form-group row" id="hidden_div">
				<div class="col-sm-10">
				<div class="custom-file"></div>
					Select Image File: 
					<form:input class="custom-file-input"  path="image" type="file" />
				</div>
			</div>
			
			<div class="form-group row">
				<div class="col-sm-10">
					<form:input type="text" path="pn_title" class="form-control"
						placeholder="Notification Title(optional)"/>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-10">
					<form:input type="text" path="bitlyurl" class="form-control"
						placeholder="Bitly URL(optional)"/>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-10">
					<form:textarea path="message" class="form-control" placeholder="Message"
						rows="5" cols="30" id="msg"></form:textarea>
					<h6 style="color: red;">
						<span id="validmsg"></span>
					</h6>
				</div>
			</div>
			<div>
				<button type="submit" id="validation">Send</button>
			</div>
		</form:form>
		</div>
		
		 <div class="col-sm-6">
		<h4 style="font: bold;">Bulk Communication</h4>
		<form:form name="bulknotification"  modelAttribute="notification"  action="addBulkNotification" enctype="multipart/form-data" acceptCharset="UTF-8">
		<!-- <div class="form-group row"> -->
				<div class="col-sm-10">
				
					<form:select path="pn_type" class="form-control" id="select"  onchange="showSelectDiv()" >
						<form:option value="">Excel file</form:option>
						<form:option value="1">Upload Excel file</form:option>
					<form:option value="2">Select contacts</form:option>	
					</form:select>
					<h6 style="color: red;">${exterror } ${emptyerr } ${present }
						<span id="validselect"></span>
					</h6>
				</div>
			<!-- </div> -->
					<div id="hidden_selectDiv"
						style="width:500px;height:100px;border:solid 1px grey;overflow:scroll;">
					
						<ul style="list-style-type:none;">
						<c:forEach var="menu" items="${moblist }">
						       <li>${menu } <form:checkbox class="form-check-input" path="selected" value="${menu }"/>  </li>

						</c:forEach>
						</ul>
					</div>

					<div class="form-group row">
			<div class="col-sm-10">
			<h5 style="font: bold;">please upload user data excel file only</h5>
			<a href="download"><h5 style="font: bold; color: blue;">Download sample template</h5></a>
			</div>
			</div>
			<div class="form-group row" id="showdiv" >
				<div class="col-sm-10">
				
					<form:input path="pn_to" class="custom-file-input"  type="file"/>
				</div>
			</div>
			<h5 style="color: red;">${mobmsg }</h5>
			
			<div class="form-group row">
				<div class="col-sm-10">
					<form:input type="text" path="image" class="form-control" id="mobselect"
						placeholder="Image" readonly="true"/>
					<h6 style="color: red;">
						<span id="validimg"></span>
					</h6>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-10">
					<form:input type="text" path="pn_title" class="form-control" id="mobselect"
						placeholder="Notification Name" readonly="true"/>
					<h6 style="color: red;">
						<span id="validnotificationname"></span>
					</h6>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-10">
					<form:input type="text" path="bitlyurl" class="form-control" id="mobselect"
						placeholder="Bitly URL" readonly="true"/>
					<h6 style="color: red;">
						<span id="validbitlyurl"></span>
					</h6>
				</div>
			</div>
			<div class="form-group row">
			<div class="col-sm-10">
			<h5 style="font: bold;">please upload language excel file only</h5>
			<a href="downloadfile"><h5 style="font: bold; color: blue;">Download sample template</h5></a>
			</div>
			</div>
			<div class="form-group row" >
				<div class="col-sm-10">template
					Select Image File: 
					<form:input class="custom-file-input"  path="template" type="file"  />
				</div>
			</div>
			<h5 style="color: red;">${conmsg }</h5>
			<div>
				<button type="submit"  id="validation">Send Now</button>
					 
					<button type="button" id="schedular" data-toggle = "modal" data-target = ".bd-example-modal-sm" >Send Later</button>
						<div class="modal fade bd-example-modal-sm" tabindex="-1"
							role="dialog" aria-labelledby="mySmallModalLabel"
							aria-hidden="true">
							<form:form action="addBulkNotification" modelAttribute="notification">
							<div class="modal-dialog modal-sm">
								<div class="modal-content">

									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Please
											select Date and time for sending notification.</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
									</div>

									<div class="modal-body">
										<div class='input-group date' id='datetimepicker1'>
											<form:input type='text' class="form-control" path="sendafter" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>

									<div class="modal-footer">
										<button type="submit" class="btn btn-info">Save</button>
										<button type="button" class="btn btn-info"
											data-dismiss="modal">Close</button>

									</div>

								</div>
							</div>
							</form:form>
						</div>
					</div>
		</form:form>
		</div>
		</div>
	</div>
</body>
</html>
