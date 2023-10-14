
# Product Services

Here are the CRUD (Create, Read, Update, Delete) commands to interact with API endpoints for Product Services.

## Create (POST)

To create a new product:

```shell
curl -X POST -H "Content-Type: application/json" -d '{
  "name": "New Product",
  "price": 19.99,
  "category": "Electronics",
  "description": "Description",
  "comment": "Comment"
}' http://localhost:8081/products
````

## Read (GET)

To retrieve all products:

```shell
curl -X GET http://localhost:8081/products
```

To retrieve a specific product:

```shell
curl -X GET http://localhost:8081/products/1
```

## Update (PUT or PATCH)

### Using PUT (Replace the entire resource):

```shell
curl -X PUT -H "Content-Type: application/json" -d '{
  "name": "Updated Product",
  "price": 24.99,
  "category": "Electronics",
  "description": "Updated Description",
  "comment": "Updated Comment"
}' http://localhost:8081/products/1
```

## Delete (DELETE)

To delete a product:

```shell
curl -X DELETE http://localhost:8081/products/1
```

