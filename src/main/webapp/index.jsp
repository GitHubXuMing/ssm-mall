<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试文件上传功能</title>
</head>
<body>
<h3>商品图片上传</h3>
<form action="/manage/product/upload.do" method="post"
      enctype="multipart/form-data">
    <input type="file" name="multipartFile">
    <input type="submit" value="上传">
</form>
<h3>富文本图片上传</h3>
<form action="/manage/product/upload_richtext_img.do" method="post"
      enctype="multipart/form-data">
    <input type="file" name="multipartFile">
    <input type="submit" value="上传">
</form>
</body>
</html>
