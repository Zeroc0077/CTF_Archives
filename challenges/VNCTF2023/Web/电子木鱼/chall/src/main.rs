use actix_files::Files;
use actix_web::{
    error, get, post,
    web::{self, Json},
    App, Error, HttpResponse, HttpServer,
};
use once_cell::sync::Lazy;
use serde::{Deserialize, Serialize};
use std::sync::{Arc, Mutex};
use tera::{Context, Tera};

static GONGDE: Lazy<ThreadLocker<i32>> = Lazy::new(|| ThreadLocker::from(0));

#[derive(Debug, Clone, Default)]
struct ThreadLocker<T> {
    value: Arc<Mutex<T>>,
}

impl<T: Clone> ThreadLocker<T> {
    fn get(&self) -> T {
        let mutex = self.value.lock().unwrap();
        mutex.clone()
    }
    fn set(&self, val: T) {
        let mut mutex = self.value.lock().unwrap();
        *mutex = val;
    }
    fn from(val: T) -> ThreadLocker<T> {
        ThreadLocker::<T> {
            value: Arc::new(Mutex::new(val)),
        }
    }
}

#[derive(Serialize)]
struct APIResult {
    success: bool,
    message: &'static str,
}

#[derive(Deserialize)]
struct Info {
    name: String,
    quantity: i32,
}

#[derive(Debug, Copy, Clone, Serialize)]
struct Payload {
    name: &'static str,
    cost: i32,
}

const PAYLOADS: &[Payload] = &[
    Payload {
        name: "Cost",
        cost: 10,
    },
    Payload {
        name: "Loan",
        cost: -1_000,
    },
    Payload {
        name: "CCCCCost",
        cost: 500,
    },
    Payload {
        name: "Donate",
        cost: 1,
    },
    Payload {
        name: "Sleep",
        cost: 0,
    },
];

#[get("/")]
async fn index(tera: web::Data<Tera>) -> Result<HttpResponse, Error> {
    let mut context = Context::new();

    context.insert("gongde", &GONGDE.get());

    if GONGDE.get() > 1_000_000_000 {
        context.insert(
            "flag",
            &std::env::var("FLAG").unwrap_or_else(|_| "flag{test_flag}".to_string()),
        );
    }

    match tera.render("index.html", &context) {
        Ok(body) => Ok(HttpResponse::Ok().body(body)),
        Err(err) => Err(error::ErrorInternalServerError(err)),
    }
}

#[get("/reset")]
async fn reset() -> Json<APIResult> {
    GONGDE.set(0);
    web::Json(APIResult {
        success: true,
        message: "重开成功，继续挑战佛祖吧",
    })
}

#[post("/upgrade")]
async fn upgrade(body: web::Form<Info>) -> Json<APIResult> {
    if GONGDE.get() < 0 {
        return web::Json(APIResult {
            success: false,
            message: "功德都搞成负数了，佛祖对你很失望",
        });
    }

    if body.quantity <= 0 {
        return web::Json(APIResult {
            success: false,
            message: "佛祖面前都敢作弊，真不怕遭报应啊",
        });
    }

    if let Some(payload) = PAYLOADS.iter().find(|u| u.name == body.name) {
        let mut cost = payload.cost;

        if payload.name == "Donate" || payload.name == "Cost" {
            cost *= body.quantity;
        }

        if GONGDE.get() < cost as i32 {
            return web::Json(APIResult {
                success: false,
                message: "功德不足",
            });
        }

        if cost != 0 {
            GONGDE.set(GONGDE.get() - cost as i32);
        }

        if payload.name == "Cost" {
            return web::Json(APIResult {
                success: true,
                message: "小扣一手功德",
            });
        } else if payload.name == "CCCCCost" {
            return web::Json(APIResult {
                success: true,
                message: "功德都快扣没了，怎么睡得着的",
            });
        } else if payload.name == "Loan" {
            return web::Json(APIResult {
                success: true,
                message: "我向佛祖许愿，佛祖借我功德，快说谢谢佛祖",
            });
        } else if payload.name == "Donate" {
            return web::Json(APIResult {
                success: true,
                message: "好人有好报",
            });
        } else if payload.name == "Sleep" {
            return web::Json(APIResult {
                success: true,
                message: "这是什么？床，睡一下",
            });
        }
    }

    web::Json(APIResult {
        success: false,
        message: "禁止开摆",
    })
}

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    let port = std::env::var("PORT")
        .unwrap_or_else(|_| "2333".to_string())
        .parse()
        .expect("Invalid PORT");

    println!("Listening on 0.0.0.0:{}", port);

    HttpServer::new(move || {
        let tera = match Tera::new("src/templates/**/*.html") {
            Ok(t) => t,
            Err(e) => {
                println!("Error: {}", e);
                ::std::process::exit(1);
            }
        };
        App::new()
            .app_data(web::Data::new(tera))
            .service(Files::new("/asset", "src/templates/asset/").prefer_utf8(true))
            .service(index)
            .service(upgrade)
            .service(reset)
    })
    .bind(("0.0.0.0", port))?
    .run()
    .await
}
