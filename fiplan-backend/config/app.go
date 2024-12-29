package config

import (
	"fiplan-backend/middleware"

	"github.com/gofiber/fiber/v2"
)

func SetupApp() *fiber.App {
	app := fiber.New(fiber.Config{
			Prefork:       false,
			CaseSensitive: true,
			StrictRouting: true,
			ServerHeader:  "Fiber",
			AppName:       "Fiplan Backend",
	})

	app.Use(middleware.JWTMiddleware())

	return app
}