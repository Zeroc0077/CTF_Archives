<html lang="en">
    <head>
        <title>Tell Me</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="./static/style.css">      
    </head>
    <body>
        <div id="form-main">
            <div id="form-div">
              <form class="form" id="form1">
                <p class="name">
                  <input name="name" type="text" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="Name" id="name" />
                </p>
                
                <p class="email">
                  <input name="email" type="text" class="validate[required,custom[email]] feedback-input" id="email" placeholder="Email" />
                </p>
                
                <p class="text">
                  <textarea name="text" class="validate[required] feedback-input" id="content" placeholder="Content"></textarea>
                </p>
                <div class="submit">
                  <button type="button" id="button-blue" onclick="javascript:post()">SEND</button>
                  <div class="ease"></div>
                </div>
              </form>
              <div align="center"><span id="result"></span></div>
            </div>
            <script src = "./static/js/jquery.js"></script>
            <script>
              function post(){
                var name = $("#name").val()
                var email = $("#email").val()
                var content = $("#content").val()
                console.log(name)
                console.log(email)
                console.log(content)

                if (name == "" || email == "" || content == ""){
                  alert("name,email,content cannot be empty")
                  return 
                }

                var res = "<user><name>" + name + "</name><email>" + email + "</email><content>" + content + "</content></user>"
                console.log(res)
                
                $.ajax({
                  url: "./send.php",
                  type: "POST",                  
                  contentType: "application/xml;charset=utf-8",
                  data: res,
                  processData: false,
                  cache: false,
                  success: function(result){
                     $("#result").text(result)
                     setTimeout(function () {
                      document.getElementById("result").text = "";
                    },5000)
                  },
                  error: function (){
                    $("#result").text("something error occurred")
                    setTimeout(function () {
                      document.getElementById("result").text = "";
                    },5000)
                  }
                })
              }
            </script>
    </body>
    <!-- hint: ./www.zip -->
</html>
