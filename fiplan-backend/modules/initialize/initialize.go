package initialize

import (
	"fiplan-backend/modules/auth"
	"fiplan-backend/modules/user"

	"gorm.io/gorm"
)

type Module struct {
	UserService user.Service
	AuthService auth.Service
}

func InitializeModules(db *gorm.DB) *Module {
	repositories := InitializeRepositories(db)

	return &Module{
		UserService: user.NewService(repositories.UserRepo),
		AuthService: auth.NewService(repositories.UserRepo),
	}
}