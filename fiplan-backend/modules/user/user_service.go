package user

import (
	"errors"
	"fiplan-backend/utils"
	"fmt"

	"gorm.io/gorm"
)


type service struct {
	repo Repository
}

type Service interface {
	GetAllUsers() ([]User, error)
	GetUser(id uint) (*User, error)
	CreateUser(username, password string) error
}


func NewService(repo Repository) Service {
	return &service{repo: repo}
}

func (service *service) GetAllUsers() ([]User, error) {
	users, err := service.repo.FindAll()
	if err != nil {
		return nil, fmt.Errorf("gagal mendapatkan data pengguna, %s", err.Error())
	}

	return users, nil
}

func (service *service) GetUser(id uint) (*User, error){
	user, err :=  service.repo.FindOne(id)
	if err != nil {
		if errors.Is(err, gorm.ErrRecordNotFound) {
			return nil, fmt.Errorf("pengguna tidak ditemukan")
		}

		return nil, fmt.Errorf("terjadi kesalahan, %s", err.Error())
	}

	return user, nil
}

func (service *service) CreateUser(username, password string) error {
	hashedPassword, err := utils.HashPassword(password) 
	if err != nil {
		return fmt.Errorf("gagal Hash Password, %s", err)
	}

	user := User{
		Username: username,
		Password: hashedPassword,
		Token: "",
	}
	if err := service.repo.SaveUser(&user); err != nil {
		return fmt.Errorf("gagal menyimpan Pengguna, %s", err)
	}

	return nil
}