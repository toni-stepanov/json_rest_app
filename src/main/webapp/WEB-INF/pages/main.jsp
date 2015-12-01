<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>

<strong>Upload file</strong><br/>
<form method="POST" action="file_upload" enctype="multipart/form-data">
    File to upload: <input type="file" name="file"><br/>
    <input type="submit" value="Upload"> Press here to upload the file!
</form>

</br></br>

<strong>Save user</strong><br/>
<form method="POST" action="/saving">
    Name: <input name="name"></br>
    Email: <input name="email"></br>
    Avatar's URI: <input name="uid"></br>
    <button type="submit">Submit</button>
</form>

</br></br>

<strong>Get user by requestId</strong><br/>
<form method="GET" action="/get_user">
    Enter ID user:<input name="requestId"></br>
    <button type="submit">Submit!</button>
</form>

</br></br>

<strong>change status</strong><br/>
<form method="post" action="/changeStatus">
    Enter ID user:<input name="requestId"></br>
    Status(online?)<input type="checkbox" name="isOnline"></br>
    <button type="submit">Submit!</button>
</form>


</br></br>

<strong>Statistic</strong><br/>
<form method="get" action="/staistic">
    Request ID:<input name="requestId"></br>
    Status(online?)<input type="checkbox" name="isOnline"></br>
    <button type="submit">Submit!</button>
</form>

</body>
</html>
