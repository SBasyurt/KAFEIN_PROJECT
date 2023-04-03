[![Build Status](https://travis-ci.com/coma123/Spring-Boot-Blog-REST-API.svg?branch=development)](https://travis-ci.com/coma123/Spring-Boot-Blog-REST-API) [![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=coma123_Spring-Boot-Blog-REST-API&metric=alert_status)](https://sonarcloud.io/dashboard?id=coma123_Spring-Boot-Blog-REST-API) [![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/3706/badge)](https://bestpractices.coreinfrastructure.org/projects/3706)

# Spring Boot, MySQL, Spring Security, JWT, JPA, Rest API

Build Restful CRUD API for a blog using Spring Boot, Mysql, JPA and Hibernate.

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/coma123/Spring-Boot-Blog-REST-API.git
```

**2. Create Mysql database**
```bash
create database blogapi
```
- run `src/main/resources/blogapi.sql`

**3. Change mysql storename and password as per your installation**

+ open `src/main/resources/application.properties`
+ change `spring.datasource.storename` and `spring.datasource.password` as per your mysql installation

**4. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

## Explore Rest APIs

The app defines following CRUD APIs.

### Auth

| Method | Url | Decription | Sample Valid Request Body | 
| ------ | --- | ---------- | --------------------------- |
| POST   | /api/auth/signup | Sign up | [JSON](#signup) |
| POST   | /api/auth/signin | Log in | [JSON](#signin) |

### Stores

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/stores/me | Get logged in store profile | |
| GET    | /api/stores/{storename}/profile | Get store profile by storename | |
| GET    | /api/stores/{storename}/products | Get products created by store | |
| GET    | /api/stores/{storename}/albums | Get albums created by store | |
| GET    | /api/stores/checkStorenameAvailability | Check if storename is available to register | |
| GET    | /api/stores/checkEmailAvailability | Check if email is available to register | |
| POST   | /api/stores | Add store (Only for admins) | [JSON](#storecreate) |
| PUT    | /api/stores/{storename} | Update store (If profile belongs to logged in store or logged in store is admin) | [JSON](#storeupdate) |
| DELETE | /api/stores/{storename} | Delete store (For logged in store or admin) | |
| PUT    | /api/stores/{storename}/giveAdmin | Give admin role to store (only for admins) | |
| PUT    | /api/stores/{storename}/TakeAdmin | Take admin role from store (only for admins) | |
| PUT    | /api/stores/setOrUpdateInfo | Update store profile (If profile belongs to logged in store or logged in store is admin) | [JSON](#storeinfoupdate) |

### Products

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/products | Get all products | |
| GET    | /api/products/{id} | Get product by id | |
| POST   | /api/products | Create new product (By logged in store) | [JSON](#productcreate) |
| PUT    | /api/products/{id} | Update product (If product belongs to logged in store or logged in store is admin) | [JSON](#productupdate) |
| DELETE | /api/products/{id} | Delete product (If product belongs to logged in store or logged in store is admin) | |

### ProductLogs

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/products/{productId}/productLogs | Get all productLogs which belongs to product with id = productId | |
| GET    | /api/products/{productId}/productLogs/{id} | Get productLog by id if it belongs to product with id = productId | |
| POST   | /api/products/{productId}/productLogs | Create new productLog for product with id = productId (By logged in store) | [JSON](#productLogcreate) |
| PUT    | /api/products/{productId}/productLogs/{id} | Update productLog by id if it belongs to product with id = productId (If productLog belongs to logged in store or logged in store is admin) | [JSON](#productLogupdate) |
| DELETE | /api/products/{productId}/productLogs/{id} | Delete productLog by id if it belongs to product with id = productId (If productLog belongs to logged in store or logged in store is admin) | |

Test them using productman or any other rest client.

## Sample Valid JSON Request Bodys

##### <a id="signup">Sign Up -> /api/auth/signup</a>
```json
{
	"firstName": "Leanne",
	"lastName": "Graham",
	"storename": "leanne",
	"password": "password",
	"email": "leanne.graham@gmail.com"
}
```

##### <a id="signin">Log In -> /api/auth/signin</a>
```json
{
	"storenameOrEmail": "leanne",
	"password": "password"
}
```

##### <a id="storecreate">Create Store -> /api/stores</a>
```json
{
	"firstName": "Ervin",
	"lastName": "Howell",
	"storename": "ervin",
	"password": "password",
	"email": "ervin.howell@gmail.com",
	"address": {
		"street": "Victor Plains",
		"suite": "Suite 879",
		"city": "Wisokyburgh",
		"zipcode": "90566-7771",
		"geo": {
			"lat": "-43.9509",
			"lng": "-34.4618"
		}
	},
	"phone": "010-692-6593 x09125",
	"website": "http://erwinhowell.com",
	"company": {
		"name": "Deckow-Crist",
		"catchPhrase": "Proactive didactic contingency",
		"bs": "synergize scalable supply-chains"
	}
}
```

##### <a id="storeupdate">Update Store -> /api/stores/{storename}</a>
```json
{
	"firstName": "Ervin",
	"lastName": "Howell",
	"storename": "ervin",
	"password": "updatedpassword",
	"email": "ervin.howell@gmail.com",
	"address": {
		"street": "Victor Plains",
		"suite": "Suite 879",
		"city": "Wisokyburgh",
		"zipcode": "90566-7771",
		"geo": {
			"lat": "-43.9509",
			"lng": "-34.4618"
		}
	},
	"phone": "010-692-6593 x09125",
	"website": "http://erwinhowell.com",
	"company": {
		"name": "Deckow-Crist",
		"catchPhrase": "Proactive didactic contingency",
		"bs": "synergize scalable supply-chains"
	}
}
```

##### <a id="storeinfoupdate">Update Store Profile -> /api/stores/setOrUpdateInfo</a>
```json
{
	"street": "Douglas Extension",
	"suite": "Suite 847",
	"city": "McKenziehaven",
	"zipcode": "59590-4157",
	"companyName": "Romaguera-Jacobson",
	"catchPhrase": "Face to face bifurcated interface",
	"bs": "e-enable strategic applications",
	"website": "http://ramiro.info",
	"phone": "1-463-123-4447",
	"lat": "-68.6102",
	"lng": "-47.0653"
}
```

##### <a id="productcreate">Create Product -> /api/products</a>
```json
{
	"title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
	"body": "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto"
}
```

##### <a id="productupdate">Update Product -> /api/products/{id}</a>
```json
{
	"title": "UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED",
	"body": "UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED "
}
```

##### <a id="productLogcreate">Create ProductLog -> /api/products/{productId}/productLogs</a>
```json
{
	"body": "laudantium enim quasi est quidem magnam voluptate ipsam eos tempora quo necessitatibus dolor quam autem quasi reiciendis et nam sapiente accusantium"
}
```

##### <a id="productLogupdate">Update ProductLog -> /api/products/{productId}/productLogs/{id}</a>
```json
{
	"body": "UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED "
}
```
![segment](https://api.segment.io/v1/pixel/track?data=ewogICJ3cml0ZUtleSI6ICJwcDJuOTU4VU1NT21NR090MWJXS0JQd0tFNkcydW51OCIsCiAgInVzZXJJZCI6ICIxMjNibG9nYXBpMTIzIiwKICAiZXZlbnQiOiAiQmxvZ0FwaSB2aXNpdGVkIiwKICAicHJvcGVydGllcyI6IHsKICAgICJzdWJqZWN0IjogIkJsb2dBcGkgdmlzaXRlZCIsCiAgICAiZW1haWwiOiAiY29tcy5zcHVyc0BnbWFpbC5jb20iCiAgfQp9)
