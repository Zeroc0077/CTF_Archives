package main

import (
	"encoding/gob"
	"fmt"
	"os"
)

type User struct {
	Name  string
	Path  string
	Power string
}

func main() {
	userDir := "/tmp/756d74b657ab2ba2b8369da24e9f0aa2/"
	user := User{
		Name:  "ctfer",
		Path:  userDir,
		Power: "admin",
	}
	file, err := os.Create("./user.gob")
	if err != nil {
		fmt.Println("fail to create file", err.Error())
		return
	}
	defer file.Close()

	encoder := gob.NewEncoder(file)
	err = encoder.Encode(user)
	if err != nil {
		fmt.Println("fail to encode", err.Error())
		return
	} else {
		fmt.Println("encode successly")
	}
}
