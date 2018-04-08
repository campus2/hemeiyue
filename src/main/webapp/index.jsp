<html>
<body>
<h2>Hello World!</h2>
<script type="text/javascript">
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/admin/login", true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.setRequestHeader("kbn-version", "5.3.0");
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4) {
			if (xhr.status == 200) {
				alert("logon success");
			}else {
				alert("failed");
			}
		}
	};
	xhr.send(JSON.stringify({
		"account":"12345678",
		"password":"123456"
		}));
</script>
<img alt="" src="http://localhost:8080/hemeiyue/activity/getQrCode?activityId=6" />
<a href="http://localhost:8080/hemeiyue/activity/down?id=8">Excel</a>
</body>
</html>
