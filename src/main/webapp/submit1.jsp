<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<script>
 $(document.)ready(function() {
	 $("#outside").click(function(){
		 $("#my-form").submit();
		 //자바스크립트를 이용하면, 밖에 있는 단추도 submit 기능을 이용할 수 있음.
	 });
	 $("#inside").click(function(e){ //e : 이벤트 객체
		 e.preventDefault(); //원래 하던 submit 기능을 하지않는다.
		 
		 //필요한 일들을 적고
		 console.log("인싸 버튼 클릭");
		 $("#my-form").submit();
		 //submit으로 보내면 됨.
	 //submit 하기전에 어떤 일을 하고싶을때도 가능
	 });
 });
 </script>

<title>Insert title here</title>
</head>
<body>
<h1> submit 예제 페이지</h1>
<h1>name : ${param.name }</h1>
<form action="">
	<input type="text" name="name" value="java" /> <br>
	<input type="submit" value="전송" />
	<button id="inside">또 다른 전송</button>
</form>

<button id="outside">밖에 있는 버튼</button>

</body>
</html>