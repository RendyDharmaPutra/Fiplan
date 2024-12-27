package user

import (
	"strconv"

	"github.com/gofiber/fiber/v2"
)

func UserController(group fiber.Router, service Service) {
	group.Get("/", func(ctx *fiber.Ctx) error {
		users, err := service.GetAllUsers()
		
		if err != nil {
			return ctx.Status(fiber.StatusInternalServerError).JSON(fiber.Map{
				"status": "failed",
				"message": "Gagal Mendapatkan data Pengguna",
				"error": err.Error(),
			})
		}

		return ctx.Status(fiber.StatusCreated).JSON(fiber.Map{
			"status": "success",
			"message": "Berhasil mendapatkan pengguna",
			"pengguna": users,
		})
	})

	group.Get("/:id", func(ctx *fiber.Ctx) error {
		id, err := strconv.Atoi(ctx.Params("id"))
    	if err != nil {
			return ctx.Status(fiber.StatusBadRequest).JSON(fiber.Map{
				"status": "failed",
				"message": "Gagal mendapatkan data Pengguna",
				"error": "Format ID tidak valid",
        	})
    	}

		user, err := service.GetUser(uint(id))
		if err != nil {
			return ctx.Status(fiber.StatusInternalServerError).JSON(fiber.Map{
				"status": "failed",
				"message": "Gagal mendapatkan data Pengguna",
				"error": err.Error(),
			})
		}

		return ctx.Status(fiber.StatusCreated).JSON(fiber.Map{
			"status": "success",
			"message": "Berhasil mendapatkan pengguna",
			"pengguna": user,
		})
	})

	group.Post("/", func (ctx *fiber.Ctx) error {
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

		if err := service.CreateUser(request.Username, request.Password); err != nil {
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