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
	<form class="form-signin" method="post" action="${pageContext.request.contextPath}/user/login">
		<h1 class="h3 mb-3 font-weight-normal">用户登录</h1>
		<label for="inputName" class="sr-only">请输出名字</label>
		<input type="text" id="inputName" name="name" class="form-control" placeholder="请输出名字" required autofocus>
		<label for="inputPassword" class="sr-only">Password</label>
		<input type="password" id="inputPassword" name="password" class="form-control" placeholder="请输入密码" required>


		<div class="checkbox mb-3">
			<label> <input type="checkbox" value="remember-me">
				Remember me
			</label>

		</div>
		<label>
			<a href="user-add.jsp">用户注册</a>
		</label>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		<p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
	</form>
</div>
</body>