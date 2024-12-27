package main

import (
	"fiplan-backend/config"
	"fiplan-backend/modules/initialize"
	"fiplan-backend/routes"
	"log"
	"os"
)

func main() {
    config.LoadEnv()

    app := config.SetupApp()

    db := config.InitDB()

    module := initialize.InitializeModules(db)

    routes.SetupRoutes(app, module)

    log.Fatal(app.Listen(os.Getenv("APP_PORT")))
}