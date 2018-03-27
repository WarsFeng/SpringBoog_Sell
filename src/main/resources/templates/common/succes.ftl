<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>成功页面</title>
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="alert alert-dismissable alert-success">

                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    成功!
                </h4> <strong>${msg}</strong><a href="${url}" id="alert-link">3秒后自动跳转</a>
            </div>
        </div>
    </div>
</div>
<script>
    setTimeout('location.href="${url}"',3000)
</script>
</body>
</html>