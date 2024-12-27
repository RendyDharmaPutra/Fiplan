package auth

import (
	"github.com/gofiber/fiber/v2"
)

func AuthController(group fiber.Router, service Service) {
	group.Post("/register", func (ctx *fiber.Ctx) error {
		var request struct {
			Username string `json:"username"`
			Password string `json:"password"`
		}
		
		if err := ctx.BodyParser(&request); err != nil {
			return ctx.Status(fiber.StatusBadRequest).JSON(fiber.Map{
				"status": "Failed",
				"message": "Gagal mendaftarkan pengguna",
				"error": "Gagal memproses data yang diberikan",
			})
		}

		if err := service.Register(request.Username, request.Password); err != nil {
			return ctx.Status(fiber.StatusInternalServerError).JSON(fiber.Map{
				"status": "Failed",
				"message": "Gagal mendaftarkan pengguna",
				"error": err.Error(),
			})
		}

		return ctx.Status(fiber.StatusCreated).JSON(fiber.Map{
			"status": "success",
			"message": "Berhasil mendaftarkan pengguna",
		})
	})
}