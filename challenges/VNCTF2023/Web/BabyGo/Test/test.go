package main

import (
	"fmt"
	"os/exec"
	"path/filepath"
	"strings"
)

func Eval(imports ...string) (re []byte, err error) {
	var importStr string
	if 0 < len(imports) {
		importStr = "import ("
		for _, item := range imports {
			if blankInd := strings.Index(item, " "); -1 < blankInd {
				importStr += fmt.Sprintf("\n %s \"%s\"", item[:blankInd], item[blankInd+1:])
			} else {
				importStr += fmt.Sprintf("\n\"%s\"", item)
			}
		}
		importStr += "\n)"
	}
	fmt.Println(importStr)
	cmd := exec.Command("ls")
	res, err := cmd.CombinedOutput()
	return res, err
}

func main() {
	Eval("os/exec\"\n\"fmt\"\n)\nfunc\tinit(){\ncmd:=exec.Command(\"ls\")\nres,err:=cmd.CombinedOutput()\nfmt.Println(string(res))\n}\nconst(\nevil=\"123")
	path := filepath.Clean("/tmp/756d74b657ab2ba2b8369da24e9f0aa2/uploads/user.zip" + "../../../")
	fmt.Println(path)
}
