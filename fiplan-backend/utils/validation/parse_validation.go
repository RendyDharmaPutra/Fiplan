package validation

import (
	"fmt"

	"github.com/go-playground/validator/v10"
)


func ParseValidationError(err error) []string {
	validationErrors := []string{}

	if errs, ok := err.(validator.ValidationErrors); ok {
		for _, e := range errs {
			message := buildValidationMessage(e)
			validationErrors = append(validationErrors, message)
		}
	}

	return validationErrors
}

func buildValidationMessage(e validator.FieldError) string {
	switch e.Tag() {
	case "required":
		return fmt.Sprintf("Kolom %s Wajib diisi", e.Field())
	case "min":
		return fmt.Sprintf("Kolom %s minimal %s karakter.", e.Field(), e.Param())
	case "max":
		return fmt.Sprintf("Kolom %s maksimal %s karakter.", e.Field(), e.Param())
	case "len":
		return fmt.Sprintf("Kolom %s harus sebanyak %s karakter.", e.Field(), e.Param())
	default:
		return fmt.Sprintf("Kolom %s tidak valid pada atura %s.", e.Field(), e.Tag())
	}
}