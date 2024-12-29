package routes

import (
	"fiplan-backend/modules/auth"
	"fiplan-backend/modules/initialize"
	"fiplan-backend/modules/user"

	"github.com/go-playground/validator/v10"
	"github.com/gofiber/fiber/v2"
)


func SetupRoutes(app *fiber.App, module *initialize.Module) {
	var validate =  validator.New()

	authGroup :=  app.Group("/auth")
	auth.AuthController(authGroup, module.AuthService, validate)

	userGroup := app.Group("/users")
	user.UserController(userGroup, module.UserService)
}