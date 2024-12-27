package user

import "gorm.io/gorm"

type repository struct {
	db *gorm.DB
}

type Repository interface {
	FindAll() ([]User, error)
	FindOne(id uint) (*User, error)
	SaveUser(user *User) error
}


func NewRepository(db *gorm.DB) Repository {
	return &repository{db: db}
}

func (repo *repository) FindAll() ([]User, error) {
	var users []User
	
	if err := repo.db.Find(&users).Error; err != nil {
		return nil, err
	}

	return users, nil
}

func (repo *repository) FindOne(id uint) (*User, error) {
	var user User

	if err := repo.db.First(&user, id).Error; err != nil {
		return nil, err
	}

	return &user, nil
} 

func (repo *repository) SaveUser(user *User) error {
	return repo.db.Create(user).Error
}