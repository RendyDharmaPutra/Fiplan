package auth

import (
	"fiplan-backend/modules/user"
	"fiplan-backend/utils"
	"fmt"
)

type service struct {
	repo user.Repository
}

type Service interface {
	Register(useranme, password string) error
}

func NewService(repo user.Repository) Service  {
	return &service{repo: repo}
}

func (service *service) Register(username, password string) error {
	hashedPassword, err := utils.HashPassword(password) 
	if err != nil {
		return fmt.Errorf("gagal Hash Password, %s", err)
	}

	user := user.User{
		Username: username,
		Password: hashedPassword,
		Token: "",
	}
	if err := service.repo.SaveUser(&user); err != nil {
		return fmt.Errorf("gagal menyimpan Pengguna, %s", err)
	}

	return nil
}
