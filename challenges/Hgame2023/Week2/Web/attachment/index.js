const express = require("express")
const jwt = require("jsonwebtoken")
const puppeteer = require('puppeteer')
const querystring = require('node:querystring')

const app = express()

app.use(express.static("./static"))
app.use(express.json())
app.set("view engine", "ejs")
app.set("views", "views")
app.use(express.urlencoded({ extended: false }))

const secret = "secret_here"

function auth(req, res, next) {
  const token = req.headers["authorization"]
  if (!token) {
    return res.redirect("/")
  }
  try {
    const decoded = jwt.verify(token, secret) || {}
    req.user = decoded
  } catch {
    return res.status(500).json({ msg: "jwt decode error" })
  }
  next()
}

app.get("/", (req, res) => {
  res.render("register")
})

app.post("/user/register", (req, res) => {
  const username = req.body.username
  let flag = "hgame{fake_flag_here}"
  if (username == "admin" && req.ip == "127.0.0.1" || req.ip == "::ffff:127.0.0.1") {
    flag = "hgame{true_flag_here}"
  }
  const token = jwt.sign({ username, flag }, secret)
  res.json({ token })
})

app.get("/user/info", auth, (req, res) => {
  res.json({ username: req.user.username, flag: req.user.flag })
})

app.post("/button/save", auth, (req, res) => {
  req.user.style = {}
  for (const key in req.body) {
    req.user.style[key] = req.body[key]
  }
  const token = jwt.sign(req.user, secret)
  res.json({ token })
})

app.get("/button/get", auth, (req, res) => {
  const style = req.user.style
  res.json({ style })
})

app.get("/button/edit", (req, res) => {
  // render a button
  res.render("button")
})

app.post("/button/share", auth, async (req, res) => {
  const browser = await puppeteer.launch({
    headless: true,
    executablePath: "/usr/bin/chromium",
    args: ['--no-sandbox']
  });
  const page = await browser.newPage()
  const query = querystring.encode(req.body)
  await page.goto('http://127.0.0.1:9090/button/preview?' + query)
  await page.evaluate(() => {
    return localStorage.setItem("token", "jwt_token_here")
  })
  await page.click("#button")
  res.json({ msg: "admin will see it later" })
})

app.get("/button/preview", (req, res) => {
  const blacklist = [
    /on/i, /localStorage/i, /alert/, /fetch/, /XMLHttpRequest/, /window/, /location/, /document/
  ]
  for (const key in req.query) {
    for (const item of blacklist) {
      if (item.test(key.trim()) || item.test(req.query[key].trim())) {
        req.query[key] = ""
      }
    }
  }
  res.render("preview", { data: req.query })
})

app.listen(9090)