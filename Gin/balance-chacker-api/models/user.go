package models

import "errors"

type User struct {
  Id int `json:"id"`
  Username string `json:"username"`
  Password string `json:"password"`
  Balance float64 `json:"balance"`
}

var Users = []User{}

func AddUser(user User) {
  Users = append(Users, user);
}

func GetUserByUsername(un string) (*User, error) {
  for _, u := range Users {
    if u.Username == un {
      return &u, nil
    }
  }
  return nil, errors.New("User not found")
}

func UpdateUserByUsername(un string, nu User) {
  for i, u := range Users {
    if u.Username == un {
      Users[i] = nu
      break
    }
  }
}
