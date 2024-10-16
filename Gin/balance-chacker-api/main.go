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
  router.POST("/api/trans/create", controlers.CreateTrans)
  router.GET("/api/users", controlers.ListAllUsers)
  router.GET("/api/trans/get", controlers.GetTrans)

  router.Run(":8080")
}
