package controlers

import (
  "balance-checker-api/models"
  "github.com/gin-gonic/gin"
  "net/http"
  "time"
)

var transIdCount = 0

func CreateTrans(c *gin.Context) {
  var trans models.Transaction

  if err := c.ShouldBindJSON(&trans); err != nil {
    c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
    return
  }

  user_from, erruf := models.GetUserByUsername(trans.UserFrom)
  if erruf != nil || user_from.Balance < trans.Amount {
    c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid user or low balance"})
    return
  }

  user_to, errut := models.GetUserByUsername(trans.UserTo)
  if errut != nil {
    c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid user"})
    return
  }

  user_from.Balance -= trans.Amount
  user_to.Balance += trans.Amount
  trans.Id = transIdCount
  transIdCount++
  trans.Time = time.Now()
  models.UpdateUserByUsername(trans.UserFrom, *user_from)
  models.UpdateUserByUsername(trans.UserTo, *user_to)
  models.AddTrans(trans)

  c.JSON(http.StatusOK, gin.H{"message": "Transaction created"})
}

func GetTrans(c *gin.Context) {
  username := c.Query("user")

  if username == "" {
    c.JSON(http.StatusBadRequest, gin.H{"error": "username is required"})
    return
  }

  transs := models.GetUserTrans(username)

  c.JSON(http.StatusOK, transs)
}
