<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <script src="myScript.js"></script>
</head>
<body>
<form>
    <div>
        <label for="code_snippet"></label><textarea id="code_snippet">// write your code here</textarea>
    </div>
    <div>
        <label for="time">Time restriction: </label>
        <input type="text" id="time" name="views"/>
    </div>
    <div>
        <label for="views">Views restriction: </label>
        <input type="text" id="views" name="views"/>
    </div>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
</form>
</body>
</html>