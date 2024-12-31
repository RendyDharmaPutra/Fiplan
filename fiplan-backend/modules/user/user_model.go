package user

type User struct {
	ID string `gorm:"type:char(36);primaryKey"`
	Username string `gorm:"unique;not null" validate:"required,min=3,max=32"`
	Password string `gorm:"not null" validate:"required,min=6"`
}