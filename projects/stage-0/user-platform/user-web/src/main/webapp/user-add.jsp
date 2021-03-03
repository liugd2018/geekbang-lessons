<head>
<jsp:directive.include file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
	<title>My Home Page</title>
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
</head>
<body>
	<div class="container">
		<form class="form-user-add" action="${pageContext.request.contextPath}/user/register" method="post">
			<h1 class="h3 mb-3 font-weight-normal">用户注册</h1>
			<label for="inputName" class="sr-only">请输出名字</label>
			<input type="text" id="inputName" class="form-control" name="name"  placeholder="请输出名字" required autofocus>

			<label for="inputPhoneNum" class="sr-only">请输出电话号码</label>
			<input type="number" id="inputPhoneNum" class="form-control" name="phoneNum" placeholder="请输出电话号码" required autofocus>

			<label for="inputEmail" class="sr-only">请输出电子邮件</label>
			<input type="email" id="inputEmail" class="form-control" name="email" placeholder="请输入电子邮件" required autofocus>

			<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" id="inputPassword" class="form-control" name="password" placeholder="请输入密码" required>


			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>


			<p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
		</form>
	</div>
</body>