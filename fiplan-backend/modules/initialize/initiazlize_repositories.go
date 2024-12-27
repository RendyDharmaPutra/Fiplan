package initialize

import (
	"fiplan-backend/modules/user"

	"gorm.io/gorm"
)

type Repositories struct {
	UserRepo user.Repository
}

func InitializeRepositories(db *gorm.DB) *Repositories {
	return &Repositories{
		UserRepo: user.NewRepository(db),
	}
}