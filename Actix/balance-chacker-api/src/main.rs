use actix_web::{web, App, HttpServer};
use std::sync::Mutex;
mod models;
mod user;
mod trans;
use models::{User, Transaction};

struct AppState {
    users: Mutex<Vec<User>>,
    transactions: Mutex<Vec<Transaction>>,
}

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    let data = web::Data::new(AppState {
        users: Mutex::new(Vec::new()),
        transactions: Mutex::new(Vec::new()),
    });

    HttpServer::new(move ||  {
        App::new()
            .app_data(data.clone())
            .configure(user::user_routes)
            .configure(trans::trans_routes)
    })
    .bind("127.0.0.1:8080")?
    .run()
    .await
}
