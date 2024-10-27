use actix_web::{web, HttpResponse, Responder};
use uuid::Uuid;
use crate::models::Transaction;
use crate::AppState;
use chrono::Utc;

#[derive(serde::Deserialize)]
pub struct TransInput {
    pub user_from: String,
    pub user_to: String,
    pub amount: f64,
}

#[derive(serde::Deserialize)]
struct TransactionQuery {
    user_id: String,
}

async fn create_trans(data: web::Data<AppState>, input: web::Json<TransInput>) -> impl Responder {
    let mut users = data.users.lock().unwrap();
    let mut transs = data.transactions.lock().unwrap();

    let (mut user_f, mut user_t) = users.iter_mut().fold((None, None), |(from, to), user| {
        match user.username.as_str() {
            name if name == input.user_from => (Some(user), to),
            name if name == input.user_to => (from, Some(user)),
            _ => (from, to),
        }
    });

    if let (Some(user_f), Some(user_t)) = (user_f, user_t) {
        if user_f.balance >= input.amount {
            let trans = Transaction {
                id: Uuid::new_v4(),
                user_from: input.user_from.clone(),
                user_to: input.user_to.clone(),
                amount: input.amount.clone(),
                time: Utc::now().to_string(),
            };

            user_f.balance -= input.amount;
            user_t.balance += input.amount;

            transs.push(trans.clone());

            return HttpResponse::Ok().json(trans);
        }

        return HttpResponse::BadRequest().body("Insufficient funds");

    }

    HttpResponse::NotFound().body("User not found")
}

async fn list(data: web::Data<AppState>, query: web::Query<TransactionQuery>) -> impl Responder {
    let trans = data.transactions.lock().unwrap();
    let u_trans: Vec<_> = trans.iter().filter(|t| t.user_from == query.user_id || t.user_to == query.user_id).clone().collect();
    HttpResponse::Ok().json(&*u_trans)
}

pub fn trans_routes(cfg: &mut web::ServiceConfig) {
    cfg.service(
        web::scope("/trans")
            .route("/create", web::post().to(create_trans))
            .route("/list", web::get().to(list))
    );
}
