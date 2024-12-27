package routes

import (
	"fiplan-backend/modules/auth"
	"fiplan-backend/modules/initialize"
	"fiplan-backend/modules/user"

	"github.com/gofiber/fiber/v2"
)


func SetupRoutes(app *fiber.App, module *initialize.Module) {
	authGroup :=  app.Group("/auth")
	auth.AuthController(authGroup, module.AuthService)

	userGroup := app.Group("/users")
	user.UserController(userGroup, module.UserService)
}