package main

import "AES/router"

func main() {
	r := router.InitRouter()
	_ = r.Run(":8080")
}
