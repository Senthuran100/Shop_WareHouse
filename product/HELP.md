# Getting Started


Sample Category Creation Request

`curl --location 'http://localhost:8080/v1/api/category' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbk1vZCIsImlhdCI6MTcwMDg2NzkzOSwiZXhwIjoxNzAwOTU0MzM5fQ.02ExsF9bbtS8kDTifydSj2wxb__rTU9sIF9LZc1sNzW5TRdG3zRTnzKTNVrBK4Bxh4b8zMdjyh5YFACI3luTIA' \
--header 'Content-Type: application/json' \
--data '{
"name":"sample-category"
}'`

Sample Order Creation Request

`curl --location 'http://localhost:8080/v1/api/order' \
--header 'Content-Type: application/json' \
--data '{
"userId":1,
"orderProductList":[{"quantity":3,"productId":1}]
}'`