<?php
class Post {
    public $title;
    public $content;
    public $comments;

    public function __construct($title, $content) {
        $this->title = $title;
        $this->content = $content;
    }

    public function __toString() {
        $comments = $this->comments;
        // comments are bugged for now, but in future it might be re-implemented
        // when it is, just append $comments_fallback to $out
        if ($comments !== null) {
            $comments_fallback = $this->$comments;
        }

        $conn = new Conn;
        $conn->queries = array(new Query(
            "select id from posts where title = :title and content = :content",
            array(":title" => $this->title, ":content" => $this->content)
        ));
        $result = $conn();
        if ($result[0] === false) {
            return "";
        } else {
            return "
            <div class='card'> 
                <h3 class='card-header'>{$this->title}</h3>
                <div class='card-body'>
                    <p class='card-text'>{$this->content}</p>
                </div>
                <div class='card-footer'>
                    <input class='input-group-text' style='font-size: 12px;' disabled value='Commenting is disabled.' />
                </div>
            </div>
            ";
        }
    }
}

class User {
    public $profile;
    public $posts = array();

    public function __construct($username) {
        $this->profile = new Profile($username);
    }

    // get user profile
    public function get_profile() {
        // some dev apparently mixed up user and profile... 
        // so this check prevents any more errors
        if ($this->profile instanceof User) {
            return "@i_use_vscode please fix your code";
        } else {
            // quite unnecessary to assign to a variable imho
            $profile_string = "
            <div>{$this->profile}</div>
            ";
            return $profile_string;
        }
    }

    public function get_posts() {
        // check if we've already fetched posts before to save some overhead
        // (our poor sqlite db is dying)
        if (sizeof($this->posts) !== 0) {
            return "Please reload the page to fetch your posts from the database";
        }

        // get all user posts
        $conn = new Conn;
        $conn->queries = array(new Query(
            "select title, content from posts where user = :user",
            array(":user" => $this->profile->username)
        ));

        // get posts from database
        $result = $conn();
        if ($result[0] !== false) {
            while ($row = $result[0]->fetchArray(1)) {
                $this->posts[] = new Post($row["title"], $row["content"]);
            }
        }

        // build the return string
        $out = "";
        foreach ($this->posts as $post) {
            $out .= $post;
        }
        return $out;
    }

    // who put this?? git blame moment (edit: i checked, it's @i_use_vscode as usual)
    public function __toString() {
        $profile = $this->profile;
        return $profile();
    }
}

class Profile {
    public $username;
    public $picture_path = "images/real_programmers.png";

    public function __construct($username) {
        $this->username = $username;
    }

    // hotfix for @i_use_vscode (see line 97)
    // when removed, please remove this as well
    public function __invoke() {
        if (gettype($this->picture_path) !== "string") {        
            return "<script>window.location = '/login.php'</script>";
        }

        $picture = base64_encode(file_get_contents($this->picture_path));

        // check if user exists
        $conn = new Conn;
        $conn->queries = array(new Query(
            "select id from users where username = :username",
            array(":username" => $this->username)
        ));
        $result = $conn();
        if ($result[0] === false || $result[0]->fetchArray() === false) {
            return "<script>window.location = '/login.php'</script>";
        } else {
            return "
            <div class='card'>
                <img class='card-img-top profile-pic' src='data:image/png;base64,{$picture}'> 
                <div class='card-body'>
                    <h3 class='card-title'>{$this->username}</h3>
                </div>
            </div>
            ";
        }
    }

    // this is the correct implementation :facepalm:
    public function __toString() {
        if (gettype($this->picture_path) !== "string") {        
            return "";
        }

        $picture = base64_encode(file_get_contents($this->picture_path));

        // check if user exists
        $conn = new Conn;
        $conn->queries = array(new Query(
            "select id from users where username = :username",
            array(":username" => $this->username)
        ));
        $result = $conn();
        if ($result[0] === false || $result[0]->fetchArray() === false) {
            return "<script>window.location = '/login.php'</script>";
        } else {
            return "
            <div class='card'>
                <img class='card-img-top profile-pic' src='data:image/png;base64,{$picture}'> 
                <div class='card-body'>
                    <h3 class='card-title'>{$this->username}</h3>
                </div>
            </div>
            ";
        }
    }
}

class Conn {
    public $queries;

    // old legacy code - idk what it does but not touching it...
    public function __invoke() {
        $conn = new SQLite3("/sqlite3/db");
        $result = array();

        // on second thought, whoever wrote this is a genius
        // its gotta be @i_use_neovim
        foreach ($this->queries as $query) {
            if (gettype($query->query_string) !== "string") {
                return "Invalid query.";
            }
            $stmt = $conn->prepare($query->query_string);
            foreach ($query->args as $param => $value) {
                if (gettype($value) === "string" || gettype($value) === "integer") {
                    $stmt->bindValue($param, $value);
                } else {
                    $stmt->bindValue($param, "");
                }
            }
            $result[] = $stmt->execute();
        }

        return $result;
    }
}

class Query {
    public $query_string = "";
    public $args;

    public function __construct($query_string, $args) {
        $this->query_string = $query_string;
        $this->args = $args;
    }

    // for debugging purposes
    public function __toString() {
        return $this->query_string;
    }
}
?>
