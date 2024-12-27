package user

import (
	"errors"
	"strconv"

	"github.com/gofiber/fiber/v2"
	"gorm.io/gorm"
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
			"status": "failed",
			"message": "Gagal Mendapatkan data Pengguna",
			"error": "Format ID tidak valid",
        })
    }

	user, err :=  service.repo.FindOne(uint(id))
	if err != nil {
		if errors.Is(err, gorm.ErrRecordNotFound) {
			return ctx.Status(fiber.StatusNotFound).JSON(fiber.Map{
				"status": "failed",
				"message": "Gagal Mendapatkan data Pengguna",
				"error": "Pengguna  tidak ditemukan",
			})
		}


		return ctx.Status(fiber.StatusInternalServerError).JSON(fiber.Map{
			"status": "failed",
			"message": "Gagal Mendapatkan data Pengguna",
			"error": err,
		})
	}

	return ctx.JSON(user)
}

func (service *service) CreateUser(ctx *fiber.Ctx) error {
	var user User
	
	if err := ctx.BodyParser(&user); err != nil {
		return ctx.Status(fiber.StatusBadRequest).JSON(fiber.Map{
			"status": "failed",
			"message": "Gagal memproses data user yang diberikan",
			"err": err,
		})
	}

	if err := service.repo.SaveUser(&user); err != nil {
		return ctx.Status(fiber.StatusInternalServerError).JSON(fiber.Map{
			"status": "failed",
			"message": "Gagal menyimpan data yang diberikan",
			"error": err,
		})
	}

	return ctx.Status(fiber.StatusCreated).JSON(fiber.Map{
		"status": "success",
		"message": "Berhasil mendaftar pengguna",
		"pengguna": user,
	})
}