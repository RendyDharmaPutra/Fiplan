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
	Login(useranme, password string) (int, string, error)
	Profile(id interface{}) (*user.User, error)
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
	}
	if err := service.repo.SaveUser(&user); err != nil {
		return err
	}

	return nil
}

func (service *service) Login(username, password  string) (int, string, error) {
	statusCode := 500
	
	user, err :=  service.repo.FindOne(map[string]interface{}{"Username": username})
	if err != nil {
		if err.Error() == "pengguna tidak ditemukan" {
			statusCode = 400
			err = errors.New("username tidak ditemukan")
		}

		return statusCode, "", err
	}

	if err := utils.VerifyPassword(user.Password, password); err != nil {
		if err.Error() == "password tidak cocok" {
			statusCode = 400
		}

		return statusCode, "", err
	}

	token, err := utils.GenerateJWT(user.ID)
	if err != nil {
		return statusCode, "", err
	}

	return statusCode, token, err
}

func (service *service) Profile(id interface{}) (*user.User, error) {
	user, err := service.repo.FindOne(map[string]interface{}{"id": id})
	if err !=  nil {
		return nil, err
	}

	return user, err
}