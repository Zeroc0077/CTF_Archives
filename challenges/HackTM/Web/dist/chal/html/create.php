<?php
include("util.php");
if (!isset($_COOKIE["user"])) {
    header("Location: /login.php");
    die();
} else {
    $user = unserialize(base64_decode($_COOKIE["user"]));
}

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $title = $_POST["title"];
    $content = $_POST["content"];
    $conn = new Conn;
    $conn->queries = array(new Query(
        "insert into posts (title, content, user) values (:title, :content, :user)",
        array(":title" => $title, ":content" => $content, ":user" => $user->profile->username)
    ));

    $result = $conn();
    if ($result[0] !== false) {
        echo "
        <script>
            window.location = '/index.php'
        </script>";
    }
}
?>
<head>
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
        crossorigin="anonymous"
    >
</head>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<body>
    <div class="container-sm my-5">
        Create a post:
        <br>
        <br>
        <form action="/create.php" method="post">
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text" class="form-control" id="title" name="title">
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">Content</label>
                <textarea class="form-control" id="content" name="content"></textarea>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Create post</button>
            </div>
        </form>
    </div>
</body>
