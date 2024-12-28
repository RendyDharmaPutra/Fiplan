package auth

import (
	"errors"
	"fiplan-backend/modules/user"
	"fiplan-backend/utils"
)

type service struct {
	repo user.Repository
}

type Service interface {
	Register(useranme, password string) error
	Login(useranme, password string) (string, error)
}

func NewService(repo user.Repository) Service  {
	return &service{repo: repo}
}

func (service *service) Register(username, password string) error {
	hashedPassword, err := utils.HashPassword(password) 
	if err != nil {
		return err
	}

	user := user.User{
		Username: username,
		Password: hashedPassword,
		Token: "",
	}
	if err := service.repo.SaveUser(&user); err != nil {
		return err
	}

	return nil
}

func (service *service) Login(username, password  string) (string, error) {
	filters := map[string]interface{}{"Username": username}
	user, err :=  service.repo.FindOne(filters)
	if err != nil {
		if err.Error() == "pengguna tidak ditemukan" {
			err = errors.New("username tidak ditemukan")
		}

		return "", err
	}

	if err := utils.VerifyPassword(user.Password, password); err != nil {
		return "", err
	}

	token, err := utils.GenerateRandomToken()
	if err != nil {
		return "", err
	}
	
	if err := service.repo.UpdateUser(user.ID, map[string]interface{}{"Token": token}); err != nil {
		return "", err
	}

	return token, err
}
