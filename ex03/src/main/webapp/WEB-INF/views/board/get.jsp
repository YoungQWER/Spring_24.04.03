<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../includes/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">상세 페이지</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board Get Page
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">

           		<div class="form-group">
            		<label>Bno</label>
            		<input class="form-control" name="bno" value="${board.bno}" readonly="readonly">
           		</div>

           		<div class="form-group">
            		<label>Title</label>
            		<input class="form-control" name="title" value="${board.title}" readonly="readonly">
           		</div>
           		
           		<div class="form-group">
            		<label>Text Area</label>
            		<textarea rows="3" class="form-control" name="content" readonly="readonly">
            		${board.content}</textarea>
           		</div>
           		
           		<div class="form-group">
            		<label>Writer</label>
            		<input class="form-control" name="writer" value="${board.writer}" readonly="readonly">
           		</div>
           		
           		<button data-oper='modify' class="btn btn-primary">Modify</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           	
           		<button data-oper='list' class="btn btn-warning">List</button>


				<!-- 히든으로 bno로 modify에 값을 보내면서 이동 / 자바스크립트 사용 ( 하나만 다른곳으로 보낼때 )-->            		
            	<form id="operForm" action="/board/modify" method="get">
            		<input type="hidden" id="bno" name="bno" value='${board.bno}'>
            		<input type="hidden" name="pageNum" value='${cri.pageNum}'>
            		<input type="hidden" name="amount" value='${cri.amount}'>
            		<input type="hidden" name="type" value='${cri.type}'>
					<input type="hidden" name="keyword" value='${cri.keyword}'>
            	</form>
            	
            	
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<script>
 	$(document).ready(function(){
 		
 		let operForm = $("#operForm");
 		
		<!-- <button data-oper='modify' 클릭하면 -->
 		$("button[data-oper='modify']").on("click",function(e){
 			operForm.submit();
 		})

 		$("button[data-oper='list']").on("click",function(e){
 			operForm.find("#bno").remove();
 			operForm.attr("action", "/board/list")
 			operForm.submit();
 		})
 		
 	});
</script>

<%@include file="../includes/footer.jsp" %>      