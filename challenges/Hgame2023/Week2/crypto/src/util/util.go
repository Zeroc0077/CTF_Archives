package util

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/base64"
	"errors"
)

var key = make([]byte, 16)
var iv = make([]byte, 16)

func init() {
	_, _ = rand.Read(key)
	_, _ = rand.Read(iv)
}

func Encrypt(u string) (string, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}
	plainText := []byte(u)
	blockMode := cipher.NewCTR(block, iv)
	cipherText := make([]byte, len(plainText))
	blockMode.XORKeyStream(cipherText, plainText)
	return base64.StdEncoding.EncodeToString(cipherText), nil
}

func Decrypt(cipherText string) (string, error) {
	decodeData, err := base64.StdEncoding.DecodeString(cipherText)
	if err != nil {
		return "", errors.New("invalid base64")
	}
	block, err := aes.NewCipher(key)
	blockMode := cipher.NewCTR(block, iv)
	plainText := make([]byte, len(decodeData))
	blockMode.XORKeyStream(plainText, decodeData)
	return string(plainText), nil
}
