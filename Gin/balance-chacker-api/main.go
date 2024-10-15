package main

import (
  "balance-checker-api/controlers"
  "github.com/gin-gonic/gin"
)

func main() {
  router := gin.Default()

  router.POST("/api/user/create", controlers.CreateUser)
  router.POST("/api/user/login", controlers.LogIn)
  router.PUT("/api/user/:userId/addb", controlers.AddBalance)

  router.Run(":8080")
}
