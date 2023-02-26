$('.message a').click(function () {
   $('form').animate({ height: "toggle", opacity: "toggle" }, "slow");
})

$('#register').click(e => {
   e.preventDefault()
   const username = $('#username').val()
   axios.post("/user/register", { username }).then(res => {
      const { token } = res.data
      localStorage.setItem("token", token)
      if (token) {
         window.location = "/button/edit"
      }
   })
})