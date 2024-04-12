<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@include file = "../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Modify</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board Modify Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
	                      <form role="form" action="/board/modify" method="post">
		                       	<div class="form-group">
		                        	<label>Bno</label>
		                        	<input name="bno" class="form-control" value="${board.bno}" readonly="readonly">
		                       	</div>
		                       	
		                       	<div class="form-group">
		                        	<label>Title</label>
		                        	<input name="title" class="form-control" value="<c:out value='${board.title}'/>" >
		                       	</div>
								
								<div class="form-group">
		                        	<label>Text Area</label>
		                        	<textarea name="content" rows="3" class="form-control" 
		                        	style= "text-align:left " >
		                        	${board.content}
		                        	</textarea>
		                       	</div>
		                       	
	                       	 	<div class="form-group">
		                        	<label>Writer</label>
		                        	<input name="writer" class="form-control" value="${board.writer}" readonly="readonly">
		                       	</div>
		                       	
		                       	<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
		                       	<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
		                       	<button type="submit" data-oper='list' class="btn btn-info">List</button>
	                       	</form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
<script type="text/javascript">
$(document).ready(function(){
	
	var formObj = $("form");
	
	$('button').on("click", function(e){
		e.preventDefault();
		
		var operation = $(this).data("oper");
		console.log("operation : " + operation);
		
		if(operation === 'remove'){
			formObj.attr("action", "/board/remove");
		}else if(operation === 'list'){
			formObj.attr("action", "/board/list").attr("method","get");
			formObj.empty();
		}
		formObj.submit();
	})
});
</script>

       
 <%@ include file="../includes/footer.jsp" %>