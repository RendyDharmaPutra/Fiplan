package user

import (
	"strconv"

	"github.com/gofiber/fiber/v2"
)


type service struct {
	repo Repository
}

type Service interface {
	GetAllUsers(ctx *fiber.Ctx) error
	GetUser(ctx *fiber.Ctx) error
	CreateUser(ctx *fiber.Ctx) error
}


func NewService(repo Repository) Service {
	return &service{repo: repo}
}

func (service *service) GetAllUsers(ctx *fiber.Ctx) error {
	users, err := service.repo.FindAll()
	
	if err != nil {
		return ctx.Status(fiber.StatusInternalServerError).JSON(fiber.Map{
			"error": "Gagal Mendapatkan data User",
		})
	}

	return ctx.JSON(users)
}

func (service *service) GetUser(ctx  *fiber.Ctx) error {
	id, err := strconv.Atoi(ctx.Params("id"))
    if err != nil {
        return ctx.Status(fiber.StatusBadRequest).JSON(fiber.Map{
            "error": "Format ID tidak valid",
        })
    }

	user, err :=  service.repo.FindOne(uint(id))
	if err != nil {
		return ctx.Status(fiber.StatusInternalServerError).JSON(fiber.Map{
			"error": "Gagal Mendapatkan data User",
		})
	}

	return ctx.JSON(user)
}


func (service *service) CreateUser(ctx *fiber.Ctx) error {
	var user User
	
	if err := ctx.BodyParser(&user); err != nil {
		return ctx.Status(fiber.StatusBadRequest).JSON(fiber.Map{
			"error": "Gagal memproses data user yang diberikan",
		})
	}

	if err := service.repo.SaveUser(&user); err != nil {
		return ctx.Status(fiber.StatusInternalServerError).JSON(fiber.Map{
			"error": "Gagal menyimpan data yang diberikan",
		})
	}

	return ctx.Status(fiber.StatusCreated).JSON(fiber.Map{
		"status": "success",
		"message": "Berhasil mendaftar pengguna",
	})
}