docker run --name pg17 -e POSTGRES_PASSWORD=root -p 5432:5432 -d postgres:17
\
criar o banco efit
\
rodar a aplicação
\
APIs -> http://localhost:8080/swagger-ui/index.html
\
primeiro deve ser feito o registro
\
http://localhost:8080/swagger-ui/index.html#/authentication-controller/register
\
se já ter usuário pode ser usado o
\
http://localhost:8080/swagger-ui/index.html#/authentication-controller/authenticate
\
em seguida colcar o access_token em Authorize. 