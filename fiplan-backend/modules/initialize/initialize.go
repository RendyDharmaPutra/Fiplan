package initialize

import (
	"fiplan-backend/modules/user"

	"gorm.io/gorm"
)

type Module struct {
	UserService user.Service
}

func InitializeModules(db *gorm.DB) *Module {
	repositories := InitializeRepositories(db)

	return &Module{
		UserService: user.NewService(repositories.UserRepo),
	}
}