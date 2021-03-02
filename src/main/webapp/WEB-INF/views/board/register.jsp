<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>

<title>Insert title here</title>
</head>
<body>

<u:topNav />

<!-- responsive web 디자인 -->

  <div class="conteainer-sm">
<!--   핸드폰 화면 같은 작은 공간에서 : conteainer가 주는 공간이 되려 낭비. -->

    <div class="row">
	  	<div class="col-12 col-lg-6 offset-lg-3">
	
	      <h1>게시물 작성</h1>
	    </div>
  	</div>
  	<div class="col-12 col-lg-6 offset-lg-3">
      <form method="post"<%-- action="${pageContext.request.contextPath }/board/register" --%> >
        <div class="form-group">
          <label for="Input1">제목</label>
          <input name="title" type="text" class="form-control" id="Input1" placeholder="제목을 입력해주세요.">
        </div>

        <div class="form-group">
          <label for="Textarea1">내용</label>
          <textarea name="content" class="form-control" id="Textarea1" rows="3" placeholder="내용을 입력해주세요."></textarea>
        </div>

        <div class="form-group">
          <label for="Input2">작성자</label>
          <input name="writer" type="text" class="form-control" id="Input2" placeholder="작성자">
        </div>
        
        <button type="submit" class="btn btn-primary mb-2">등록!</button>
      </form>
     </div>
  </div>
</body>
</html>