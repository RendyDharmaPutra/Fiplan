package utils

import (
	"crypto/rand"
	"encoding/hex"
	"errors"
	"log"
)

func GenerateRandomToken() (string, error) {
	bytes := make([]byte, 32)
	
	_, err := rand.Read(bytes)
	if err != nil {
		log.Printf("Gagal membuat token, error tidak diketahui  : %v", err.Error())

		return "", errors.New("error tidak diketahui")
	}

	return hex.EncodeToString(bytes), nil
}