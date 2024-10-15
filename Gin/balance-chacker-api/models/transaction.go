package models

import (
  "time"
)

type Transaction struct {
  Id int `json:"id"`
  UserFrom string `json:"user_from"`
  UserTo string `json:"user_to"`
  Amount float64 `json:"amount"`
  Time time.Time `json:"time"`
}

var Transactions = []Transaction{}

func AddTrans(trans Transaction) {
  Transactions = append(Transactions, trans)
}

func GetUserTrans(un string) []Transaction {
  var re []Transaction
  for _, t := range Transactions {
    if t.UserFrom == un || t.UserTo == un {
      re = append(re, t)
    }
  }
  return re
}
