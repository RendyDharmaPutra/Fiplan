package user

type User struct {
	ID uint `gorm:"primaryKey"`
	Username string `gorm:"unique;not null"`
	Password string `gorm:"not null"`
	Token    string `gorm:"not null"`
}