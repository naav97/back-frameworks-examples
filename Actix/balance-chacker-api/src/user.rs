use actix_web::{web, HttpResponse, Responder};
use bcrypt::{hash, verify, DEFAULT_COST};
use uuid::Uuid;
use crate::models::User;
use crate::AppState;

#[derive(serde::Deserialize)]
pub struct UserInput {
    pub username: String,
    pub password: String,
}

#[derive(serde::Deserialize)]
pub struct BalanceInput {
    pub amount: f64,
}

async fn create_user(data: web::Data<AppState>, input: web::Json<UserInput>) -> impl Responder {
    let pass_hash = hash(&input.password, DEFAULT_COST).unwrap();
    let user = User {
        id: Uuid::new_v4(),
        username: input.username.clone(),
        password: pass_hash,
        balance: 0.0,
    };

    let mut users = data.users.lock().unwrap();
    users.push(user.clone());

    HttpResponse::Ok().json(user)
}

async fn login(data: web::Data<AppState>, input: web::Json<UserInput>) -> impl Responder {
    let users = data.users.lock().unwrap();

    if let Some(user) = users.iter().find(|u| u.username == input.username) {
        if verify(&input.password, &user.password).unwrap() {
            return HttpResponse::Ok().body("Login Successful");
        }
    }

    HttpResponse::Unauthorized().body("Invalid credentials")
}

async fn add_balance(data: web::Data<AppState>, input: web::Json<BalanceInput>, user_id: web::Path<String>) -> impl Responder {
    let mut users = data.users.lock().unwrap();

    if let Some(user) = users.iter_mut().find(|u| u.username == *user_id) {
        user.balance = user.balance + input.amount;
        return HttpResponse::Ok().json(user);
    }

    HttpResponse::NotFound().body("User not found")
}

async fn list(data: web::Data<AppState>) -> impl Responder {
    let users = data.users.lock().unwrap();
    HttpResponse::Ok().json(&*users)
}

pub fn user_routes(cfg: &mut web::ServiceConfig) {
    cfg.service(
        web::scope("/user")
            .route("/create", web::post().to(create_user))
            .route("/login", web::post().to(login))
            .route("/{user_id}/addb", web::put().to(add_balance))
            .route("/", web::get().to(list))
    );
}
