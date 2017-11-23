<html>  
  <body>  
	          尊敬的客户:</br>
		您设置的绑定邮箱地址是： ${email?if_exists},  请点击一下链接验证您的邮箱地址:</br>
		<a href="${url?if_exists}?email=${emailEnc?if_exists}&userId=${userId?if_exists}&verifiCode=${verifiCode?if_exists}&type=${type?if_exists}">${url?if_exists}?email=${emailEnc?if_exists}&userId=${userId?if_exists}&verifiCode=${verifiCode?if_exists}&type=${type?if_exists}</a></br>
		您收到此通知是由于您设置或修改绑定邮箱. 请注意如果在24小时内未完成邮箱验证，将会导致您绑定邮箱设置或修改失败.</br>
		(如果以上链接无法点击，请将其复制粘贴到浏览器地址栏后访问)
  </body>  
</html>  