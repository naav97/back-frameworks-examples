package controlers

import (
  "balance-checker-api/models"
  "github.com/gin-gonic/gin"
  "net/http"
  "golang.org/x/crypto/bcrypt"
)

var userIdCount = 0

func CreateUser(c *gin.Context) {
  var user models.User
  if err := c.ShouldBindJSON(&user); err != nil {
    c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
    return
  }
  hash, err := bcrypt.GenerateFromPassword([]byte(user.Password), bcrypt.DefaultCost)
  if err != nil {
    c.JSON(http.StatusInternalServerError, gin.H{"error":"Failed to hash password"})
    return
  }
  user.Password = string(hash)
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
  if err != nil {
    c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid credentials"})
    return
  }

  err = bcrypt.CompareHashAndPassword([]byte(user.Password), []byte(credentials.Password))
  if err != nil {
    c.JSON(http.StatusUnauthorized, gin.H{"error": "Invalid credentials"})
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
  models.UpdateUserByUsername(Username, *user)

  c.JSON(http.StatusOK, gin.H{"message": "Balance updated successfully"})
}

func ListAllUsers(c *gin.Context) {
  c.JSON(http.StatusOK, models.GetAllUsers())
}
