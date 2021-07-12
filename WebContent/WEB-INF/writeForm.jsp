<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   
   <h1>전화번호 등록폼</h1>
   <p>
      전화번호를 등록하려면 <br>
      아래 항목을 기입하고 "등록" 버튼을 클릭하세요.
   </p>
   <!-- 
   http://localhost:8088/phonebook1/insert.jsp?name=황일영&hp=010-2222-2222&company=02-2222-2222
    -->
   <form action="/phonebook2/pbc?action=insert" method="get">
      이름(name): <input type="text" name="name" value=""> <br>
      핸드폰(hp): <input type="text" name="hp" value=""> <br>
      회사(company): <input type="text" name="company" value=""> <br>
      구분 : <input type="hidden" name="action" value="insert"> <br>
      <button type="submit">등록</button>
   </form>
   
   
   
   

</body>
</html>