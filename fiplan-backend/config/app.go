package config

import "github.com/gofiber/fiber/v2"

func SetupApp() *fiber.App {
	app := fiber.New(fiber.Config{
			Prefork:       false,
		CaseSensitive: true,
		StrictRouting: true,
		ServerHeader:  "Fiber",
		AppName:       "Fiplan v1.0",
		})

		return app

}