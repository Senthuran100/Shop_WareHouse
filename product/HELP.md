# Getting Started


Sample Category Creation Request

`curl --location 'http://localhost:8080/v1/api/category' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbk1vZCIsImlhdCI6MTcwMDg2NzkzOSwiZXhwIjoxNzAwOTU0MzM5fQ.02ExsF9bbtS8kDTifydSj2wxb__rTU9sIF9LZc1sNzW5TRdG3zRTnzKTNVrBK4Bxh4b8zMdjyh5YFACI3luTIA' \
--header 'Content-Type: application/json' \
--data '{
"name":"sample-category"
}'`

Sample Order Creation Request

`curl --location 'http://localhost:8082/v1/api/order' \
--header 'Content-Type: application/json' \
--data '{
"userId":1,
"orderProducts":[{"quantity":1,"productId":1},{"quantity":1,"productId":1},{"quantity":1,"productId":2}]
}'`

### Sample SQL Queries.

**Get the orders and users who bought the product biscuit1**

_select * from orders o <br/>
LEFT JOIN order_product op on o.id = op.order_id <br/>
LEFT JOIN products p on p.id = op.product_id <br/>
LEFT JOIN users u on u.id = o.user_id <br/>
where p.name LIKE 'biscuit1' <br/>_

**Find out the order ids which are related to the product biscuit1**

_select DISTINCT o.id  from orders o <br/>
LEFT JOIN order_product op on o.id = op.order_id <br/>
LEFT JOIN products p on p.id = op.product_id <br/>
LEFT JOIN users u on u.id = o.user_id <br/>
where p.name LIKE 'biscuit1' <br/>_

**Find out the roles associated with a user.**

_select r.name  from users u <br/>
LEFT JOIN user_roles ur ON ur.user_id = u.id <br/>
LEFT JOIN roles r ON  r.id = ur.role_id <br/>
WHERE u.email = 'abc123@gmail.com' <br/>_

**ElasticSearch Installation using Docker command**

`docker run -p 9200:9200 \
-e "discovery.type=single-node" \
-e "xpack.security.enabled=false" \
docker.elastic.co/elasticsearch/elasticsearch:8.8.1`


**View the Elastic Search Data.**

`http://127.0.0.1:9200/orderindex/_search?pretty`
