<?php
include("util.php");
if (!isset($_COOKIE["user"])) {
    header("Location: /login.php");
    die();
} else {
    $user = unserialize(base64_decode($_COOKIE["user"]));
}
?>
<head>
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
        crossorigin="anonymous"
    >
    <style>
        .profile-pic {
            margin: 10px;
            width: 100px;
            height: 100px;
            border-radius: 50%;
        }
        .username {
            font-size: 30px;
        }
    </style>
</head>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<body>
    <div class="container">
        <br>
        <div class="row">
            <?php
            echo $user->get_profile();
            ?>
            <div class="col-xs-6">
                <br>
                <br>
                <?php
                echo $user->get_posts();
                ?>
            </div>
            <div class="col-xs-6">
                <br>
                <button type="button" class="btn btn-outline-primary" onclick="window.location = '/create.php'">Create post</div>
            </div>
        </div>
    </div>
</body>
