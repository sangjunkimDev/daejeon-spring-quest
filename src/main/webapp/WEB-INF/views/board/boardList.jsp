<%@page import="kr.or.ddit.tboard.model.TboardVO"%>
<% %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath }/css/dashboard.css" rel="stylesheet">
<script type="text/javascript">
	$(function() {
		$('#changeTboard').on('click', function(){
			$('#tboardSelectForm').submit();
		})
		
		$('.notInfo').on('click', function(){
			alert('삭제된 게시글입니다')
		})
		
	})
</script>
	
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h2 class="sub-header">${tboardVO.tboard_title }</h2>
			<form action="${pageContext.request.contextPath }/main/boardlistpage" id="tboardSelectForm">
				<select id="tboardSelect" name="board_tboard_seq">
					<c:forEach items="${tboardList }" var="tboardVOtemp">
						<option value="${tboardVOtemp.tboard_seq }">${tboardVOtemp.tboard_title }</option>
					</c:forEach>
				</select>
				<button type="button" id="changeTboard" class="btn btn-sm btn-primary">게시판 이동</button>
			</form>
			<a href="${pageContext.request.contextPath }/main/boardInsertPage?tboard_title=${tboardVO.tboard_title }&tboard_seq=${tboardVO.tboard_seq }">게시글 추가</a>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>등록일자</th>
							<th>글쓴이</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${boardList }" var="boardVO">
							<tr>
								<td>${boardVO.board_seq }</td>
								<td>
									<c:choose>
										<c:when test="${boardVO.board_del_yn != 'N' }">
											<a href="boardInfo?board_seq=${boardVO.board_seq }&tboard_title=${tboardVO.tboard_title }">
												<c:forEach begin="1" end="${boardVO.LEVEL }" var="i">
													<c:choose>
														<c:when test="${i == boardVO.LEVEL && i != 1}">
															<c:out value="L"/>
														</c:when>
														<c:otherwise>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:otherwise>
													</c:choose>
												</c:forEach>
												${boardVO.board_title }
											</a>
										</c:when>
										<c:otherwise>
											<p class="notInfo">
												<c:forEach begin="1" end="${boardVO.LEVEL }" var="i">
													<c:choose>
														<c:when test="${i == boardVO.LEVEL && i != 1}">
															<c:out value="L"/>
														</c:when>
														<c:otherwise>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:otherwise>
													</c:choose>
												</c:forEach>
												삭제된 게시글입니다
											</p>
										</c:otherwise>
									</c:choose>
								</td>
								<td><fmt:formatDate value="${boardVO.board_reg_dt }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${boardVO.board_mem_id }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<!-- pagenation -->
			<nav aria-label="Page navigation example">
				<ul class="pagination">
					<%
						int pageCount = (Integer)request.getAttribute("pageCount");
						String pageNumStr = (String)request.getAttribute("pageNum");
						int pageNum = Integer.valueOf(pageNumStr);
						
						String code = "";
						
						if(pageCount != 0){
							TboardVO tboardVO = (TboardVO)request.getAttribute("tboardVO");
							String tboard_seq = tboardVO.getTboard_seq();
							
							if(pageNum != 1){
								code += "<li class='page-item'>";
								code += "	<a class='page-link' href='" + request.getContextPath() + "/main/boardlistpage?pageNum=1&board_tboard_seq=" + tboard_seq + "' aria-label='Previous'>";
								code += "		<span aria-hidden='true'>&laquo;</span>";
								code += "		<span class='sr-only'>Previous</span>";
								code += "	</a>";
								code += "</li>";
								code += "<li class='page-item'>";
								code += "	<a class='page-link' href='" + request.getContextPath() + "/main/boardlistpage?pageNum=" + (pageNum-1) +"&board_tboard_seq=" + tboard_seq + "'>Prev</a>";
								code += "</li>";
							}
							
							
							for(int i = 1; i <= pageCount; i++){
								if(i != pageNum) {
									code += "<li class='page-item'>";
								} else {
									code += "<li class='page-item active'>";
								}
								code += "<a class='page-link' href='" + request.getContextPath() + "/main/boardlistpage?pageNum="
										+ i + "&board_tboard_seq=" + tboard_seq + "'>" + i + "</a></li>";
							}
							
							
							if(pageNum != pageCount){
								code += "<li class='page-item'>";
								code += "	<a class='page-link' href='" + request.getContextPath() + "/main/boardlistpage?pageNum=" + (pageNum+1) +"&board_tboard_seq=" + tboard_seq + "'>Next</a>";
								code += "</li>";
								code += "<li class='page-item'>";
								code += "	<a class='page-link' href='" + request.getContextPath() + "/main/boardlistpage?pageNum=" + pageCount + "&board_tboard_seq=" + tboard_seq + "' aria-label='Next'>";
								code += "		<span aria-hidden='true'>&raquo;</span>";
								code += "		<span class='sr-only'>Next</span>";
								code += "	</a>";
								code += "</li>";
							}
						}
						out.print(code);
					%>
							
				</ul>
			</nav>

		</div>
	</div>