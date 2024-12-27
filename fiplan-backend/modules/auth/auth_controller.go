package auth

import (
	"fiplan-backend/modules/user"

	"github.com/gofiber/fiber/v2"
)

func AuthController(group fiber.Router, service user.Service) {
	group.Post("/register", func (ctx *fiber.Ctx) error {
		return service.CreateUser(ctx)
	})
}