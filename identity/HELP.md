
### GraphQL Request

For creating category.

`mutation{
createCategory(name:"sample"){
id,
name
}
}`

For creating product

`mutation{
createProduct(name:"chocolate1",price:4.00,stock:5,categoryId:1,userId:1){
id,
name,
stock,
category{
id,
name
},
price
}
}`

For Order creation mutation 

`mutation CreateOrderForProduct($orderReq: OrderInput!) {
createOrder(orderReq: $orderReq) {
orderId
id
orderProductList {
productId
quantity
id
}
}
}`

Variables 

`{
"orderReq": {
"userId": 1,
"orderProducts": [{"quantity": 1,"productId": 1},{"quantity": 3,"productId": 2}]
}
}`