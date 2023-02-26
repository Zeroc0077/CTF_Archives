package router

import (
	"AES/user"
	"AES/util"
	"encoding/json"
	"fmt"
	"github.com/gin-gonic/gin"
	"io"
	"net/http"
	"os"
	"time"
)

func homeController(c *gin.Context) {
	_, err := c.Cookie("token")
	if err != nil {
		c.Redirect(http.StatusFound, "/")
	}
	c.HTML(http.StatusOK, "shop.html", nil)
}

func loginController(c *gin.Context) {
	_, err := c.Cookie("token")
	if err == nil {
		c.Redirect(http.StatusFound, "/home")
	}
	userName := c.PostForm("username")
	if userName == "Vidar-Tu" {
		c.String(http.StatusForbidden, "兔兔才不可能是你呢！！")
	}
	User := user.User{Name: userName, Created: time.Now().Unix(), Uid: "230555433"}
	jsonUser, _ := json.Marshal(User)
	token, _ := util.Encrypt(string(jsonUser))
	c.SetCookie("token", token, 3600, "/", "", false, true)
	c.Redirect(http.StatusFound, "/home")
}

func rootController(c *gin.Context) {
	c.HTML(http.StatusOK, "index.html", nil)
}

func buyController(c *gin.Context) {
	method := c.Request.Method
	token, err := c.Cookie("token")
	if err != nil {
		c.String(http.StatusForbidden, "没有身份的人可不能来这儿买东西。")
	}
	jsonUser, err := util.Decrypt(token)
	if err != nil {
		c.String(http.StatusBadGateway, err.Error())
	}
	User := user.User{}
	err = json.Unmarshal([]byte(jsonUser), &User)
	if err != nil {
		c.String(http.StatusBadGateway, err.Error())
	}
	name := User.Name
	if method != http.MethodGet {
		c.String(http.StatusMethodNotAllowed, fmt.Sprintf("your method: %s. but only get method allowed", method))
	} else {
		product := c.Query("prod")
		if product == "flag" {
			if name != "Vidar-Tu" {
				c.String(http.StatusOK, "flag 可是特地为兔兔准备的！")
			} else {
				file, _ := os.Open("flag.txt")
				flag, _ := io.ReadAll(file)
				c.String(http.StatusOK, fmt.Sprintf("%s buy %s successfully\n%s", name, product, flag))
			}
		} else {
			c.String(http.StatusOK, fmt.Sprintf("%s buy %s successfully", name, product))
		}

	}
}

func InitRouter() *gin.Engine {
	gin.SetMode(gin.ReleaseMode)
	router := gin.Default()
	// 加载html模板
	router.LoadHTMLGlob("templates/*.html")
	// 加载静态文件渲染页面
	router.Static("static", "templates/static/")

	// 注册路由
	router.GET("/", rootController)
	router.POST("/login", loginController)
	router.GET("/home", homeController)
	router.GET("/buy", buyController)
	return router
}
