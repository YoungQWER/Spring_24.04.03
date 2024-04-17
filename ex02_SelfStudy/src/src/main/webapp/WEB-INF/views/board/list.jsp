<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tables</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Board List page
				<button id="regBtn" type="button"
					class="btn btn-xs btn-primary pull-right">Rigister New
					Board</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${list}">
							<tr class="odd gradeX">
								<td>${board.bno}</td>

								<td><a class="move" href='${board.bno}' /> ${board.title}</td>

								<td>${board.writer}</td>
								<td><fmt:formatDate value="${board.regdate}"
										pattern="yyyy-MM-dd" /></td>
								<td><fmt:formatDate value="${board.updateDate}"
										pattern="yyyy-MM-dd" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- /.table-responsive -->

				<!-- 모달창 추가 -->
				<div class="modal" id="myModal">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Modal Title</h4>
							</div>
							<!-- Modal body -->
							<div class="modal-body">처리가 완료되었습니다.</div>
							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /모달창 추가 -->

				<!-- 페이징 처리 -->
				<div class="container">
					<ul class="pagination justify-content-end">

						<c:if test="${pageMaker.prev}">
							<li class="page-item"><a class="page-link"
								href="${pageMaker.startPage-1}">Previous</a></li>
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li
								class="page-item ${pageMaker.cri.pageNum == num? 'active' : '' }">
								<a class="page-link" href="${num}">${num}</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next}">
							<li class="page-item"><a class="page-link"
								href="${pageMaker.endPage + 1}">Next</a></li>
						</c:if>
					</ul>
				</div>
				<!-- End 페이징 처리 -->

				<form id="actionForm" action="/board/list" method="get">
					<input type="hidden" name="pageNum"
						value="${pageMaker.cri.pageNum}"> <input type="hidden"
						name="amount" value="${pageMaker.cri.amount}">
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
	$(document)
			.ready(
					function() {

						var result = '<c:out value="${result}" />';

						checkModal(result);

						history.replaceState({}, null, null);

						//모달창
						function checkModal(result) {
							if (result === "" || history.state) {
								return;
							}
							if (parseInt(result) > 0) {
								$(".modal-body").html(
										"게시글 " + parseInt(result)
												+ "번이 등록되었습니다.");
							} else if (result === "modify") {
								$(".modal-body").html("게시글 수정되었습니다.");
							} else if (result === "delete") {
								$(".modal-body").html("게시글 삭제되었습니다.");
							}
							$("#myModal").modal("show");
						}

						//register 호출
						$("#regBtn").on("click", function() {
							self.location = "/board/register";
						});

						/* document.getElementById("regBtn").addEventListener("click",function(){
							window.location.href = "/board/register";
						}) */

						var actionForm = $("#actionForm");

						/* 페이지 번호 클릭하면 페이지 이동 */
						$(".page-item a").on(
								"click",
								function(e) {
									e.preventDefault();
									console.log("click");
									actionForm.find("input[name='pageNum']")
											.val($(this).attr("href"));
									actionForm.submit();
								});

						/* 상세페이지(get,jsp)이동 시 pageNum, amount값을 가지고 이동 */
						$(".move")
								.on(
										"click",
										function(e) {
											e.preventDefault();
											actionForm
													.append("<input type='hidden' name='bno' value= '"
															+ $(this).attr(
																	"href")
															+ "'>")
											actionForm.attr("action",
													"/board/get");
											actionForm.submit();
										});

					});
</script>












<%@ include file="../includes/footer.jsp"%>