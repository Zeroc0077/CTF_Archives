const puppeteer = require("puppeteer");
var url = decodeURIComponent(process.argv[2]);

const USERNAME = "admin";
const PASSWORD = "this_is_dummy_password_and_replaced_in_the_actual_challenge";
const SITE = "http://localhost";

const visit = async (url) => {
	let browser;
	try {
	    browser = await puppeteer.launch({
	        headless: true,
	        pipe: true,
	        args: [
	            "--no-sandbox"
	        ],
	        dumpio: true
	    });

	    let page = await browser.newPage();
	    await page.goto(SITE + "/login", { timeout: 3000, waitUntil: 'domcontentloaded' });

	    await page.type("input[name=username]", USERNAME);
	    await page.type("input[name=password]", PASSWORD);
	    await page.click("button[type=submit]");

	    await page.goto(url, { timeout: 3000, waitUntil: 'domcontentloaded' });

	    await browser.close();
	    browser = null;
	} catch (err) {
	    console.log(err);
	} finally {
	    if (browser) await browser.close();
	}
};

visit(url);