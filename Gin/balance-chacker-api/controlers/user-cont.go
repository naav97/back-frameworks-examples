package controlers

import (
  "balance-checker-api/models"
  "github.com/gin-gonic/gin"
  "net/http"
)

var userIdCount = 0

func CreateUser(c *gin.Context) {
  var user models.User
  if err := c.ShouldBindJSON(&user); err != nil {
    c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
    return
  }
  user.Id = userIdCount
  userIdCount++
  models.AddUser(user)
  c.JSON(http.StatusOK, user)
}

func LogIn(c *gin.Context) {
  var credentials struct {
    Username string `json:"username"`
    Password string `json:"password"`
  }

  if err := c.ShouldBindJSON(&credentials); err != nil {
    c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
    return
  }

  user, err := models.GetUserByUsername(credentials.Username)
  if err != nil || user.Password != credentials.Password {
    c.JSON(http.StatusBadRequest, gin.H{"error": "invalid credentials"})
    return
  }

  c.JSON(http.StatusOK, gin.H{"message": "Login successful"})
}

func AddBalance(c *gin.Context) {
  Username := c.Param("userId")

  var details struct {
    Amount float64 `json:"amount"`
  }

  if err := c.ShouldBindJSON(&details); err != nil {
    c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
    return
  }

  user, err := models.GetUserByUsername(Username)
  if err != nil {
    c.JSON(http.StatusNotFound, gin.H{"error": err.Error()})
    return
  }

  user.Balance = user.Balance + details.Amount

  c.JSON(http.StatusOK, gin.H{"message": "Balance updated successfully"})
}
