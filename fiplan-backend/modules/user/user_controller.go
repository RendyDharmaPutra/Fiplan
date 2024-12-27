package user

import "github.com/gofiber/fiber/v2"

func UserController(group fiber.Router, service Service) {
	group.Get("/", func(ctx *fiber.Ctx) error {
		return service.GetAllUsers(ctx)
	})

	group.Get("/:id", func(ctx *fiber.Ctx) error {
		return service.GetUser(ctx)
	})

	group.Post("/", func (ctx *fiber.Ctx) error {
		return service.CreateUser(ctx)
	})
}