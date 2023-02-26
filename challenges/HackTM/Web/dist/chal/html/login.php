<?php
include("util.php");
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $username = $_POST["username"];
    $password = $_POST["password"];
    $conn = new Conn;
    $conn->queries = array(new Query(
        "select username from users where username = :username and password = :password",
        array(":username" => $username, ":password" => $password)
    ));
    $result = $conn();
    if ($result[0] !== false && $result[0]->fetchArray()) {
        $user = new User($username);
        setcookie("user", base64_encode(serialize($user)));
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
<body>
    <div class="container-sm my-5">
        Please enter your credentials
        <br>
        <br>
        <form action="/login.php" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <?php
            if ($_SERVER["REQUEST_METHOD"] === "POST") {
                echo "<p>Wrong credentials, try again.</p>";
            }
            ?>
            <div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
            <br>
            <a href="/register.php">Register here!</a>
        </form>
    </div>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"
    ></script>
</body>
