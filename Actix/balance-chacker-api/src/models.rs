use serde::{Deserialize, Serialize};
use uui::Uuid;

#[derive(Serialize, Deserialize, Clone)]
pub struct User {
    pub id: Uuid,
    pub username: String,
    pub password: String,
    pub balance: f64,
}

#[derive(Serialize, Deserialize, Clone)]
pub struct Transaction {
    pub id: Uuid,
    pub user_from: String,
    pub user_to: String,
    pub amount: f64,
    pub time: String,
}
